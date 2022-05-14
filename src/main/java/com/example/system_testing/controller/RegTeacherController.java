package com.example.system_testing.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.system_testing.database.DataBaseHandler;
import com.example.system_testing.essences.Teacher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RegTeacherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ImageButtonHome;

    @FXML
    private TextField discipline_teacher_field;

    @FXML
    private TextField fio_teacher_field;

    @FXML
    private TextField login_teacher_field;

    @FXML
    private TextField password_teacher_field;

    @FXML
    private Button regTeacherInSystem_button;

    @FXML
    void initialize() {
        regTeacherInSystem_button.setOnAction(event -> {
            signUpNewTeacher();
        });

    }

    private void signUpNewTeacher() {
        DataBaseHandler dbHandler = new DataBaseHandler();

        String fio = fio_teacher_field.getText();
        String discipline = discipline_teacher_field.getText();
        String login = login_teacher_field.getText();
        String password = password_teacher_field.getText();

        //Teacher teacher = new Teacher(fio, )
    }

}
