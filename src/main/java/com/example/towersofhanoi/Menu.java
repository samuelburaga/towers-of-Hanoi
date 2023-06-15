package com.example.towersofhanoi;

import com.example.towersofhanoi.Controller.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.sound.sampled.*;

import java.io.File;
import java.io.IOException;

public class Menu extends Application {
    public static boolean musicPlays = false;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
        public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

        MenuController menuController = fxmlLoader.getController();
        menuController.setUsername();

        primaryStage.setMaximized(true);
        primaryStage.setTitle("Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
        backgroundMusic();
    }
    public static void backgroundMusic() {
        String filePath = "D:/ULBS/Anul II/Semestrul II/Modulul 2/Metode avansate de programare/Project/towers-of-Hanoi/src/resources/Stay Retro.wav";
        try {
            File music = new File(filePath);
            if(music.exists() && musicPlays == false) {
                AudioInputStream ais = AudioSystem.getAudioInputStream(music);
                Clip clip = AudioSystem.getClip();
                clip.open(ais);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                musicPlays = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}