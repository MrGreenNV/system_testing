package com.example.system_testing;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthenticationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSigIn_button;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    void initialize() {
        assert authSigIn_button != null : "fx:id=\"authSigIn_button\" was not injected: check your FXML file 'authentication.fxml'.";
        assert login_field != null : "fx:id=\"login_field\" was not injected: check your FXML file 'authentication.fxml'.";
        assert password_field != null : "fx:id=\"password_field\" was not injected: check your FXML file 'authentication.fxml'.";

    }

}
