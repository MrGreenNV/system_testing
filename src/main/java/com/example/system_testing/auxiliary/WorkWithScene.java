package com.example.system_testing.auxiliary;

import com.example.system_testing.controller.CreateNewTestController;
import com.example.system_testing.controller.CreateQuestionController;
import com.example.system_testing.controller.TeacherMenuController;
import com.example.system_testing.essences.Test;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class WorkWithScene {

    public void getNewWindow (String window) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(ConstTables.URL_PACKAGE + window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void getNewWindow (String window, Test test) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(ConstTables.URL_PACKAGE + window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        CreateQuestionController createQuestionController = loader.getController();
        createQuestionController.setNameTest(test);

        stage.show();
    }

    public void getNewWindow (String window, int id) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(ConstTables.URL_PACKAGE + window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        TeacherMenuController teacherMenuController = loader.getController();
        teacherMenuController.setUserID(id);

        stage.show();
    }

    public void getNewWindow (String window, ArrayList<String> list) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(ConstTables.URL_PACKAGE + window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        CreateNewTestController createNewTestController = loader.getController();
        createNewTestController.setDisciplinesList(list);

        stage.show();
    }

}
