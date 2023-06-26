package com.example.towersofhanoi.Model;

import com.example.towersofhanoi.View.TutorialView;
import javafx.application.Platform;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/**
 * The TutorialThread class represents a thread that displays the automatic game (computer's solution).
 * It runs the tutorial view on the JavaFX application thread.
 */
public class TutorialThread implements Runnable {
    private Window ownerWindow;

    /**
     * Constructs a new TutorialThread object.
     * The owner window is not set initially.
     */
    public TutorialThread() {

    }

    /**
     * Constructs a new TutorialThread object with the specified owner window.
     *
     * @param ownerWindow the owner window for the tutorial view
     */
    public TutorialThread(Window ownerWindow) {
        this.ownerWindow = ownerWindow;
    }

    /**
     * Runs the tutorial view on the JavaFX application thread.
     */
    public void run() {
        Platform.runLater(() -> {
            TutorialView tutorial = new TutorialView();
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