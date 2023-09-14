package com.example.project1w487;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class UserController implements Initializable {
    ObservableList<String> userChoiceList = FXCollections.observableArrayList("In", "Out");

    @FXML
    private ChoiceBox<String> userChoiceBox;
    @FXML
    private Label usernameLabel;
    private String name;
    private String accessType;

    @FXML
    private void initialize () {
        userChoiceBox.setItems(userChoiceList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void userAccessButton(ActionEvent event) {
        String selectedChoice = userChoiceBox.getValue();

        if (selectedChoice != null) {
            if (userChoiceBox.equals("In")) {
                usernameLabel.setText("Entered!");

                // Database operation for "In"
                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connectDB = connectNow.getConnection();

                String accessQuery = "INSERT INTO access_table (name, Access, TimeStamp) VALUES (?, ?, ?)";

                //usernameLabel.setText("Please enter a valid ID");
            } else if (selectedChoice.equals("Out")) {
                usernameLabel.setText("Exited");

                // Database operation for "Out"
                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connectDB = connectNow.getConnection();

                String accessQuery = "INSERT INTO access_table (name, Access, TimeStamp) VALUES (?, ?, ?)";

                try (PreparedStatement preparedStatement = connectDB.prepareStatement(accessQuery)) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, accessType);
                    preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

                    int rowsInserted = preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            } else {
                usernameLabel.setText("Please select an option");
            }

        }

    }
}


