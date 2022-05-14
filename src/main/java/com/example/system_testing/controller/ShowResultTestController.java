package com.example.system_testing.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ShowResultTestController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ImageButtonHome;

    @FXML
    private TableColumn<?, ?> averageAssessment_column;

    @FXML
    private MenuButton choiceGroup_menuButton;

    @FXML
    private MenuButton choiceTest_menuButton;

    @FXML
    private Button goBack_buton;

    @FXML
    private TableColumn<?, ?> nameTest_column;

    @FXML
    private TableColumn<?, ?> numGroup_column;

    @FXML
    private TableView<?> showResult_table;

    @FXML
    void initialize() {

    }

}

