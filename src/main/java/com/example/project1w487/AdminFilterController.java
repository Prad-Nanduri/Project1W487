package com.example.project1w487;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminFilterController implements Initializable {

    @FXML
    private Button filterButton;

    @FXML
    private TextField idFilter;
/*
    @FXML
    private TextField timeFilter;

    @FXML
    private TextField dateFilter;*/

    @FXML
    private TableColumn<AccessFilterModel, String> nameTableColumn;

    @FXML
    private TableColumn<AccessFilterModel, Integer> idTableColumn;

    @FXML
    private TableColumn<AccessFilterModel, String> accessTableColumn;

    @FXML
    private TableColumn<AccessFilterModel, Timestamp> timestampTableColumn;

    @FXML
    private TableView<AccessFilterModel> accessTableView;

    ObservableList<AccessFilterModel> accessFilterModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String accessviewQuery = "SELECT name, userID, Access, Date, Time FROM access_table";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(accessviewQuery);

            while (queryOutput.next()) {

                Integer queryUserID = queryOutput.getInt("userID");
                String queryName = queryOutput.getString("name");
                String queryAccess = queryOutput.getString("Access");
                Date queryDate = queryOutput.getDate("TimeStamp");
                Timestamp queryTime = queryOutput.getTimestamp("TimeStamp");

                accessFilterModelObservableList.add(new AccessFilterModel(queryName, queryUserID, queryAccess, queryTime));

            }

            nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            idTableColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
            accessTableColumn.setCellValueFactory(new PropertyValueFactory<>("Access"));
            timestampTableColumn.setCellValueFactory(new PropertyValueFactory<>("TimeStamp"));

            accessTableView.setItems(accessFilterModelObservableList);

            FilteredList<AccessFilterModel> filteredData = new FilteredList<>(accessFilterModelObservableList, b -> true);

            idFilter.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(accessFilterModel -> {
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }
                    String searchKeyword = newValue.toLowerCase();

                    if (accessFilterModel.getName().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (accessFilterModel.getAccessType().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (accessFilterModel.getUserID().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (accessFilterModel.getTimestamp().toString().indexOf(searchKeyword) > -1) {
                        return true;
                    } else
                        return false;
                });
            });

            SortedList<AccessFilterModel> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(accessTableView.comparatorProperty());

            accessTableView.setItems(sortedData);

        } catch (SQLException e) {
            Logger logger = Logger.getLogger(AdminFilterController.class.getName());
            logger.log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

    }
}
