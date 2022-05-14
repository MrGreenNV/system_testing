package com.example.system_testing.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.system_testing.auxiliary.ConstNameWindows;
import com.example.system_testing.auxiliary.WorkWithScene;
import com.example.system_testing.database.DataBaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
            addDisciplineToBD();
            addDiscipline_button.getScene().getWindow().hide();
            ws.getNewWindow(ConstNameWindows.WINDOW_ADMINISTRATOR_MENU);
        });

        goBack_button.setOnAction(event -> {
            goBack();
        });

        deleteDiscipline_button.setOnAction(event -> {
            delete();
            deleteDiscipline_button.getScene().getWindow().hide();
            ws.getNewWindow(ConstNameWindows.WINDOW_ADMINISTRATOR_MENU);
        });

    }

    private void delete() {
        DataBaseHandler dbHandler = new DataBaseHandler();
        String discipline = choiceDiscipline_comboBox.getSelectionModel().getSelectedItem();
        dbHandler.deleteDiscipline(discipline);

    }

    private void addDisciplineToBD() {
        DataBaseHandler dbHandler = new DataBaseHandler();
        String discipline = nameDiscipline_textField.getText();
        dbHandler.addDiscipline(discipline);
    }

    private void goBack() {
        goBack_button.getScene().getWindow().hide();
        ws.getNewWindow(ConstNameWindows.WINDOW_ADMINISTRATOR_MENU);
    }

    private ArrayList<String> choiceDisciplines() {
        DataBaseHandler dbHandler = new DataBaseHandler();
        return dbHandler.getDisciplinesList();
    }

}

