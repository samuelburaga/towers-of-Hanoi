package com.example.towersofhanoi.View;

import com.example.towersofhanoi.Controller.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.sound.sampled.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Samuel Buraga
 * @version 1.0
 */

/**
 * The MenuView class is responsible for displaying the menu screen of the Towers of Hanoi game.
 * It extends the JavaFX Application class and provides the necessary methods for initializing and showing the menu screen.
 */
public class MenuView extends Application {

    /**
     * Indicates whether the background music is currently playing.
     */
    public static boolean musicPlays = false;

    /**
     * The start method is called when the application is launched. It initializes the menu screen and shows it on the primary stage.
     *
     * @param primaryStage the primary stage of the application
     * @throws IOException if an I/O error occurs while loading the FXML file
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        MenuController menuController = fxmlLoader.getController(); // get the controller
        menuController.setUsername(); // set the username
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
        backgroundMusic(); // play music
    }

    /**
     * The main method is the entry point of the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Plays the background music.
     * The music file is located at "/sounds/Stay Retro.wav" relative to the "res" folder.
     */
    public static void backgroundMusic() {
        String filePath = "/sounds/Stay Retro.wav"; // Path relative to the "res" folder
        // play the music
        try {
            URL resourceUrl = MenuView.class.getResource(filePath);
            if (resourceUrl != null && !musicPlays) {
                File music = new File(resourceUrl.toURI());
                if (music.exists()) {
                    AudioInputStream ais = AudioSystem.getAudioInputStream(music);
                    Clip clip = AudioSystem.getClip();
                    clip.open(ais);
                    clip.start();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    musicPlays = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
