package com.example.towersofhanoi;

import com.example.towersofhanoi.Controller.PlayController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Play extends Application {
    public static PlayerGame playerGame;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/Play.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        PlayController playController = fxmlLoader.getController();
        createGame();
        playController.drawDisks();
        playController.connectGameToUI();
        playController.startGame();
        primaryStage.setTitle("Play");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void createGame() {
        playerGame = new PlayerGame();
    }
}
