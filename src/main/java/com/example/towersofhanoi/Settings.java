package com.example.towersofhanoi;

import com.example.towersofhanoi.Controller.TutorialController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLOutput;

public class Settings extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/Settings.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        primaryStage.setTitle("Settings");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}