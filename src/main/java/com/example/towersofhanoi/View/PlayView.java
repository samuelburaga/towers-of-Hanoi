package com.example.towersofhanoi.View;

import com.example.towersofhanoi.Controller.PlayController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The PlayView class is responsible for displaying the play screen of the Towers of Hanoi game.
 * It extends the JavaFX Application class and provides the necessary methods for initializing and showing the play screen.
 */
public class PlayView extends Application {

    /**
     * The start method is called when the application is launched. It initializes the play screen and shows it on the primary stage.
     *
     * @param primaryStage the primary stage of the application
     * @throws IOException if an I/O error occurs while loading the FXML file
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Play.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        PlayController playController = fxmlLoader.getController(); // get the controller
        playController.drawDisks(); // draw the disks
        playController.connectGameToUI(); // connect the game to the UI objects
        playController.startGame(); // start the game
        primaryStage.setTitle("Play");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main method is the entry point of the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}