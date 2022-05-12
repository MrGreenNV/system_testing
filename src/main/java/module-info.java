module com.example.system_testing {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.system_testing to javafx.fxml;
    exports com.example.system_testing;
}