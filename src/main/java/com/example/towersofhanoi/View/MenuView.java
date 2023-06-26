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

public class MenuView extends Application {
    public static boolean musicPlays = false;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        MenuController menuController = fxmlLoader.getController(); // get the controller
        menuController.setUsername(); // set the username
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
        backgroundMusic(); // play music
    }
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}