package com.example.system_testing.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.system_testing.auxiliary.ConstWindows;
import com.example.system_testing.auxiliary.WorkWithScene;
import com.example.system_testing.database.DataBaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class ChangeDisciplinesController {

    WorkWithScene ws = new WorkWithScene();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addDiscipline_button;

    @FXML
    private ComboBox<String> choiceDiscipline_comboBox;

    @FXML
    private Button deleteDiscipline_button;

    @FXML
    private Button goBack_button;

    @FXML
    private TextField nameDiscipline_textField;

    @FXML
    void initialize() {

        ObservableList<String> disciplinesList = FXCollections.observableArrayList(choiceDisciplines());
        choiceDiscipline_comboBox.setItems(disciplinesList);

        addDiscipline_button.setOnAction(event -> {

        });

        goBack_button.setOnAction(event -> {
            goBack();
        });

        deleteDiscipline_button.setOnAction(event -> {

        });

    }

    private void goBack() {
        goBack_button.getScene().getWindow().hide();
        ws.getNewWindow(ConstWindows.WINDOW_ADMINISTRATOR_MENU);
    }

    private ArrayList<String> choiceDisciplines() {
        DataBaseHandler dbHandler = new DataBaseHandler();
        return dbHandler.getDisciplinesList();
    }

}

