package com.example.towersofhanoi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Menu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("View/Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        primaryStage.setMaximized(true);
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
