package com.example.towersofhanoi;

import com.example.towersofhanoi.Controller.TutorialController;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.min;

public abstract class Game {
    public static byte DISKS = 2;
    protected byte numberOfDisks = 10;
    private static long numberOfMoves = 0;
    public static boolean gameOver = false;
    public long startTime, endTime, duration;
    public int score;
    public static Duration moveAnimationSpeed = Duration.seconds(0.1);
    protected Thread animationThread;
    protected static AnchorPane anchorPane;
    protected Pane rodA, rodB, rodC; // Add references to the rod panes
    public Button moveButton;
    protected Button AToBButton, AToCButton, BToAButton, BToCButton, CToAButton, CToBButton;

    public byte getNumberOfDisks() {
        return numberOfDisks;
    }

    public Game() {

    }

    public void setRods(Pane A, Pane B, Pane C) {
        rodA = A;
        rodB = B;
        rodC = C;
    }
    public void setAnchorPane(AnchorPane anchorPane) {
        Game.anchorPane = anchorPane;
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
    protected void animateDiskMovement(Pane fromPane, Pane toPane) {
        Rectangle disk = (Rectangle) fromPane.getChildren().get(fromPane.getChildren().size() - 1); // Assuming the top disk is the first child
        double diskHeight = disk.getHeight();
        int index = fromPane.getChildren().indexOf(disk) + 1; // Index of the disk (starting from 1)
        Bounds fromBounds = fromPane.localToScene(fromPane.getBoundsInLocal());
        Bounds toBounds = toPane.localToScene(toPane.getBoundsInLocal());
        double fromX = fromBounds.getMinX() + disk.getWidth() / 2;
        double toX = toBounds.getMinX() + disk.getWidth() / 2;
        // Set the z-index of the disk's parent pane to be in front of the other rods
        fromPane.toFront();
        // Animation for moving the disk up to the top of the source pane
        TranslateTransition upTransition = new TranslateTransition(moveAnimationSpeed, disk);
        upTransition.setToY(-fromPane.getHeight() + (index * diskHeight));
        // Animation for moving the disk horizontally
        TranslateTransition horizontalTransition = new TranslateTransition(moveAnimationSpeed, disk);
        horizontalTransition.setToX(toX - fromX);
        // Animation for moving the disk down
        TranslateTransition downTransition = new TranslateTransition(moveAnimationSpeed, disk);
        downTransition.setToY(0);
        // Combine the animations into a sequential transition
        SequentialTransition sequence = new SequentialTransition(upTransition, horizontalTransition, downTransition);
        sequence.setOnFinished(event -> {
            fromPane.getChildren().remove(disk); // Remove the disk from the source pane
            toPane.getChildren().add(disk); // Add the disk to the target pane at the top
            disk.setTranslateX(0); // Reset the disk's horizontal translation
            disk.setTranslateY(0); // Reset the disk's vertical translation
        });
        sequence.play();
    }
}
