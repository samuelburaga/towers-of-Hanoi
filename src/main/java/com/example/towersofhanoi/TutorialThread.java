package com.example.towersofhanoi;

import javafx.application.Platform;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;


public class TutorialThread implements Runnable {
    private Window ownerWindow;
    public TutorialThread() {

    }
    public TutorialThread(Window ownerWindow) {
        this.ownerWindow = ownerWindow;
    }
    public void run() {
        Platform.runLater(() -> {
            Tutorial tutorial = new Tutorial();
            Stage tutorialStage = new Stage();
            tutorialStage.initOwner(ownerWindow);
            tutorialStage.initModality(Modality.NONE);
            try {
                tutorial.start(tutorialStage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}