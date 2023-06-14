package com.example.towersofhanoi;

import com.example.towersofhanoi.Controller.TutorialController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Tutorial extends Application {
    public static AutomaticGame automaticGame;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/Tutorial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        TutorialController tutorialController = fxmlLoader.getController();
        tutorialController.drawDisks();
        primaryStage.setTitle("Tutorial");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void createGame() {
        automaticGame = new AutomaticGame();
    }
}
