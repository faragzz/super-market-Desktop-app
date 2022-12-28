module com.example.software_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.software_project to javafx.fxml;
    exports com.example.software_project;
}