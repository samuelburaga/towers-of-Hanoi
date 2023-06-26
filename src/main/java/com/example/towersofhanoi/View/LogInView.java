package com.example.towersofhanoi.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LogInView extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/log_in.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.initStyle(StageStyle.UNDECORATED); // Hides the frame of the window
        primaryStage.setTitle("Log In");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
