package com.example.towersofhanoi;

import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;

public class TutorialThread extends Thread{
    public TutorialThread() {
    }
    public void run() {
        Platform.runLater(() -> {
            Tutorial tutorial = new Tutorial();
            try {
                tutorial.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
