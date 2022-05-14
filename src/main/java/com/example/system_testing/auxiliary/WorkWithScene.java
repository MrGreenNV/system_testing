package com.example.system_testing.auxiliary;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WorkWithScene {

    public void getNewWindow (String window) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Const.URL_PACKAGE + window));

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
}
