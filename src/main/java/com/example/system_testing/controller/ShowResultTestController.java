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
import javafx.event.ActionEvent;
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
    private Button showResult_button;

    @FXML
    void pushShowResult(ActionEvent event) {
        showResult();

        choiceTest_comboBox.setDisable(true);
        showResult_button.setDisable(true);
    }

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
            showResult_button.setDisable(false);
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

//        resultTest = dbHandler.showResult(numberGroup, nameTest);
//        System.out.println(resultTest.getNumberGroup());
//        System.out.println(resultTest.getNameTest());
//        System.out.println(resultTest.getAverageAssessment());

        ArrayList<Integer> listStudentID;
        double averageAssessment = 0;
        int assessment;
        int testID;
        int groupID;
        int sumAssessment = 0;
        int countStudentsPassTest = 0;

        groupID = dbHandler.getGroupID(numberGroup);

        testID = dbHandler.getTestID(nameTest);
        listStudentID = dbHandler.getListStudentID(groupID);

        for (int studentID: listStudentID
             ) {
            assessment = dbHandler.getAssessmentBehindTest(studentID, testID);
            if (assessment >= 0) {
                System.out.println("студент: " + studentID + " сдал тест: " + dbHandler.getNameTest(testID) +
                        " на оценку: " + assessment);
                countStudentsPassTest++;
                sumAssessment += assessment;
            }
        }

        if (sumAssessment != 0 && countStudentsPassTest != 0) {

            averageAssessment = (double) sumAssessment / (double) countStudentsPassTest;

            ResultTest resultTest = new ResultTest(numberGroup, nameTest, averageAssessment);

            System.out.println(resultTest.getNumberGroup());
            System.out.println(resultTest.getNameTest());
            System.out.println(resultTest.getAverageAssessment());
        }



    }

    private ArrayList<String> choiceTest() {
        return dbHandler.getTestList();
    }

    private ArrayList<String> choiceGroup() {
        return dbHandler.getGroupsList();
    }

}
