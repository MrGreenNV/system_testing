module com.example.system_testing {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.system_testing to javafx.fxml;
    exports com.example.system_testing;
}