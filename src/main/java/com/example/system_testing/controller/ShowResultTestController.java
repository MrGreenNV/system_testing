package com.example.system_testing.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.system_testing.auxiliary.ConstNameWindows;
import com.example.system_testing.auxiliary.ConstTables;
import com.example.system_testing.auxiliary.WorkWithScene;
import com.example.system_testing.database.DataBaseHandler;
import com.example.system_testing.essences.ResultTest;
import com.example.system_testing.essences.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;

public class ShowResultTestController {

    DataBaseHandler dbHandler = new DataBaseHandler();
    WorkWithScene ws = new WorkWithScene();

    String numberGroup;
    String nameTest;

    Test test;
    ResultTest resultTest = null;

    @FXML
    private TableColumn<?, ?> averageAssessment_column;

    @FXML
    private ComboBox<String> choiceGroup_comboBox;

    @FXML
    private ComboBox<String> choiceTest_comboBox;

    @FXML
    private Button goBack_button;

    @FXML
    private TableColumn<?, ?> nameTest_column;

    @FXML
    private TableColumn<?, ?> numGroup_column;

    @FXML
    private TableView<?> showResult_table;

    @FXML
    void initialize() {

        ObservableList<String> groupList = FXCollections.observableArrayList(choiceGroup());
        choiceGroup_comboBox.setItems(groupList);

        choiceGroup_comboBox.setOnAction(event -> {
            numberGroup = choiceGroup_comboBox.getSelectionModel().getSelectedItem();
            if (!(numberGroup.equals(""))) {
                choiceTest_comboBox.setDisable(false);
            }
        });

        ObservableList<String> testsList = FXCollections.observableArrayList(choiceTest());
        choiceTest_comboBox.setItems(testsList);

        choiceTest_comboBox.setOnAction(event -> {
            nameTest = choiceTest_comboBox.getSelectionModel().getSelectedItem();
            showResult();
        });

        goBack_button.setOnAction(event -> {
            goBack();
        });

    }

    private void goBack() {
        goBack_button.getScene().getWindow().hide();
        ws.getNewWindow(ConstNameWindows.WINDOW_ADMINISTRATOR_MENU);
    }

    private void showResult() {
        resultTest = dbHandler.showResult(numberGroup, nameTest);
        System.out.println(resultTest.getNumberGroup());
        System.out.println(resultTest.getNameTest());
        System.out.println(resultTest.getAverageAssessment());
    }

    private ArrayList<String> choiceTest() {
        return dbHandler.getTestList();
    }

    private ArrayList<String> choiceGroup() {
        return dbHandler.getGroupsList();
    }

}
