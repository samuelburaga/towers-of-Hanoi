package com.example.towersofhanoi;

import com.example.towersofhanoi.Controller.TutorialController;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

public abstract class Game {
    public static byte numberOfDisks = 2;
    private static long numberOfMoves = 0;
    public static boolean gameOver = false;
    public long startTime, endTime, duration;
    protected Duration moveDelay = Duration.seconds(1.5);
    protected Thread animationThread;
    protected Pane rodA, rodB, rodC; // Add references to the rod panes
    public Button moveButton;
    protected Button AToBButton, AToCButton, BToAButton, BToCButton, CToAButton, CToBButton;
    public Game() {

    }
    public Game(final byte disks) {
        numberOfDisks = disks;
    }
    public void setRods(Pane A, Pane B, Pane C) {
        rodA = A;
        rodB = B;
        rodC = C;
    }
    public void setButtons(Button AB, Button AC, Button BA, Button BC, Button CA, Button CB) {
        AToBButton = AB;
        AToCButton = AC;
        BToAButton = BA;
        BToCButton = BC;
        CToAButton = CA;
        CToBButton = CB;
    }
    public abstract boolean isGameOver();
    protected Pane getRodPane(char rod) {
        switch (rod) {
            case 'A':
                return rodA;
            case 'B':
                return rodB;
            case 'C':
                return rodC;
            default:
                throw new IllegalArgumentException("Invalid rod: " + rod);
        }
    }
    public abstract void moveDisk(char fromRod, char toRod);
    protected void animateDiskMovement(Rectangle disk, Pane toPane) {
        double initialY = disk.getY();
        double targetY = toPane.getPrefHeight() - (toPane.getChildren().size() * disk.getHeight());
        disk.setTranslateY(initialY - targetY);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(moveDelay.toSeconds()), disk);
        transition.setToY(0);
        transition.setOnFinished(event -> {
            disk.setTranslateX(0);
            disk.setTranslateY(0);
            disk.setX((toPane.getPrefWidth() - disk.getWidth()) / 2);
            disk.setY(targetY);
            // toPane.getChildren().add(disk);
        });
        transition.play();
        // Add the following line outside the event handler
        transition.setOnFinished(event -> toPane.getChildren().add(disk));
    }
}

