package com.example.system_testing.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.system_testing.auxiliary.WorkWithScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdministratorMenuController {

    WorkWithScene ws = new WorkWithScene();

    @FXML
    private Button changeGroups_button;

    @FXML
    private Button changeDisciplines_button;

    @FXML
    private Button deleteUser_button;

    @FXML
    private Button exitFromSystem_button;

    @FXML
    private Button regStudent_button;

    @FXML
    private Button regTeacher_button;

    @FXML
    private Button showResultTest_button;

    @FXML
    void initialize() {

        regTeacher_button.setOnAction(event -> {
            regTeacher();
        });

        regStudent_button.setOnAction(event -> {
            regStudent();
        });

        deleteUser_button.setOnAction(event -> {
            deleteUser();
        });

        showResultTest_button.setOnAction(event -> {
            showResultTest();
        });

        changeDisciplines_button.setOnAction(event -> {
            changeDisciplines();
        });

        changeGroups_button.setOnAction(event -> {
            changeGroups();
        });

        exitFromSystem_button.setOnAction(event -> {
            exitFromSystem();
        });

    }

    private void regTeacher() {
        regTeacher_button.getScene().getWindow().hide();
        ws.getNewWindow("regTeacher.fxml");
    }

    private void regStudent() {
        regStudent_button.getScene().getWindow().hide();
        ws.getNewWindow("regStudent.fxml");
    }

    private void deleteUser() {
        deleteUser_button.getScene().getWindow().hide();
        ws.getNewWindow("deleteUser.fxml");
    }

    private void showResultTest(){
        showResultTest_button.getScene().getWindow().hide();
        ws.getNewWindow("showResultTest.fxml");
    }

    private void changeDisciplines() {
        changeDisciplines_button.getScene().getWindow().hide();
        ws.getNewWindow("changeDisciplines.fxml");
    }

    private void changeGroups() {
        changeGroups_button.getScene().getWindow().hide();
        ws.getNewWindow("changGroups.fxml");
    }

    private void exitFromSystem() {
        exitFromSystem_button.getScene().getWindow().hide();
        ws.getNewWindow("authentication.fxml");
    }

}
