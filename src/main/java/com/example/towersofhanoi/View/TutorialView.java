package com.example.towersofhanoi.View;

import com.example.towersofhanoi.Model.AutomaticGame;
import com.example.towersofhanoi.Controller.TutorialController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TutorialView extends Application {
    public static AutomaticGame automaticGame;
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Tutorial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        createGame(); // create the game
        TutorialController tutorialController = fxmlLoader.getController(); // get the controller
        tutorialController.drawDisks();
        primaryStage.setTitle("Tutorial");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    public static void createGame() {automaticGame = new AutomaticGame();}
}