package com.example.system_testing.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.system_testing.auxiliary.Const;
import com.example.system_testing.auxiliary.Shake;
import com.example.system_testing.auxiliary.WorkWithScene;
import com.example.system_testing.database.DataBaseHandler;
import com.example.system_testing.essences.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthenticationController {

    WorkWithScene ws = new WorkWithScene();

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

        authSigIn_button.setOnAction(event -> {
            String login = login_field.getText();
            String password = password_field.getText();

            if (!login.equals("") && !password.equals("")) {
                loginUser(login, password);
            } else {
                Shake userLoginAnim = new Shake(login_field);
                Shake userPassAnim = new Shake(password_field);
                userLoginAnim.playAnim();
                userPassAnim.playAnim();
            }
        });

    }

    private void loginUser(String login, String password) {

        DataBaseHandler dbHandler = new DataBaseHandler();
        ResultSet resultSet = null;
        int counter = 0;

        User user = new User();
        user.setUserLogin(login);
        user.setUserPassword(password);

        resultSet = dbHandler.getUser(user);

        while (true) {
            try {
                if (!resultSet.next()) {
                    break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (resultSet.getString(Const.USERS_ROLE).equalsIgnoreCase("administrator")) {
                    authSigIn_button.getScene().getWindow().hide();
                    counter++;
                    ws.getNewWindow("administratorMenu.fxml");
                } else if (resultSet.getString(Const.USERS_ROLE).equalsIgnoreCase("teacher")) {
                    authSigIn_button.getScene().getWindow().hide();
                    counter++;
                    ws.getNewWindow("teacherMenu.fxml");
                } else if (resultSet.getString(Const.USERS_ROLE).equalsIgnoreCase("student")) {
                    authSigIn_button.getScene().getWindow().hide();
                    counter++;
                    ws.getNewWindow("studentMenu.fxml");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (counter == 0) {
            Shake userLoginAnim = new Shake(login_field);
            Shake userPassAnim = new Shake(password_field);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
        }
    }

}
