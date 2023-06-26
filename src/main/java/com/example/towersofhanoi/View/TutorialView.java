package com.example.towersofhanoi.View;

import com.example.towersofhanoi.Controller.TutorialController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Samuel Buraga
 * @version 1.0
 */

/**
 * The TutorialView class is responsible for displaying the computer's solution of the Towers of Hanoi game.
 * It extends the JavaFX Application class and provides the necessary methods for initializing and showing the tutorial screen.
 */
public class TutorialView extends Application {

    /**
     * The start method is called when the application is launched. It initializes the tutorial screen and shows it on the primary stage.
     *
     * @param primaryStage the primary stage of the application
     * @throws IOException if an I/O error occurs while loading the FXML file
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Tutorial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        TutorialController tutorialController = fxmlLoader.getController(); // get the controller
        tutorialController.drawDisks();
        primaryStage.setTitle("Tutorial");
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