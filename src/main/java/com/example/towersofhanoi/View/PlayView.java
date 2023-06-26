package com.example.towersofhanoi.View;

import com.example.towersofhanoi.Controller.PlayController;
import com.example.towersofhanoi.Model.PlayerGame;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayView extends Application {
    public static PlayerGame playerGame;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/Play.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        PlayController playController = fxmlLoader.getController(); // get the controller
        createGame(); // create the game
        playController.drawDisks(); // draw the disks
        playController.connectGameToUI(); // connect the game to the UI objects
        playController.startGame(); // start the game
        primaryStage.setTitle("Play");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void createGame() {
        playerGame = new PlayerGame();
    }
}
