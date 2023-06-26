package com.example.towersofhanoi.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The OptionsView class is responsible for displaying the game's options screen of the Towers of Hanoi game.
 * It extends the JavaFX Application class and provides the necessary methods for initializing and showing the game's options screen.
 */
public class OptionsView extends Application {

    /**
     * The start method is called when the application is launched. It initializes the options screen and shows it on the primary stage.
     *
     * @param primaryStage the primary stage of the application
     * @throws IOException if an I/O error occurs while loading the FXML file
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Options.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Options");
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