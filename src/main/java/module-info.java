module com.example.project1w487 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project1w487 to javafx.fxml;
    exports com.example.project1w487;
}