package com.example.towersofhanoi.Model;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.sql.Time;

/**
 * The Game class is an abstract class that represents a Towers of Hanoi game.
 */
public abstract class Game {
    public byte numberOfDisks = 2;
    public int numberOfMoves = 0, numberOfGoodMoves = 0, numberOfBadMoves = 0;
    public static boolean gameOver = false;
    public long startTime, endTime, duration;
    public Time time;
    public int points = 0;
    protected Duration moveAnimationSpeed = Duration.seconds(0.3); // move animation speed
    protected Thread animationThread;
    protected Pane rodA, rodB, rodC; // Add references to the rod panes
    public Button moveButton;
    protected Button AToBButton, AToCButton, BToAButton, BToCButton, CToAButton, CToBButton; // Add references to the move buttons

    /**
     * Returns the number of disks in the game.
     *
     * @return the number of disks
     */
    public byte getNumberOfDisks() {
        return numberOfDisks;
    }

    /**
     * Sets the references to the rod panes.
     *
     * @param A the pane for rod A
     * @param B the pane for rod B
     * @param C the pane for rod C
     */
    public void setRods(Pane A, Pane B, Pane C) {
        rodA = A;
        rodB = B;
        rodC = C;
    }

    /**
     * Sets the references to the move buttons.
     *
     * @param AB the button for moving from rod A to rod B
     * @param AC the button for moving from rod A to rod C
     * @param BA the button for moving from rod B to rod A
     * @param BC the button for moving from rod B to rod C
     * @param CA the button for moving from rod C to rod A
     * @param CB the button for moving from rod C to rod B
     */
    public void setButtons(Button AB, Button AC, Button BA, Button BC, Button CA, Button CB) {
        AToBButton = AB;
        AToCButton = AC;
        BToAButton = BA;
        BToCButton = BC;
        CToAButton = CA;
        CToBButton = CB;
    }

    /**
     * Checks if the game is over.
     *
     * @return true if the game is over, false otherwise
     */
    public abstract boolean isGameOver();

    /**
     * Returns the rod pane that matches the given rod character.
     *
     * @param rod the rod character
     * @return the rod pane
     * @throws IllegalArgumentException if the rod character is invalid
     */
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

    /**
     * Moves a disk from the specified source rod to the specified target rod.
     *
     * @param fromRod the source rod
     * @param toRod   the target rod
     */
    public abstract void moveDisk(char fromRod, char toRod);

    /**
     * Animates the movement of a disk from one pane to another.
     *
     * @param fromPane the source pane
     * @param toPane   the target pane
     */
    protected void animateDiskMovement(Pane fromPane, Pane toPane) {
        Rectangle disk = (Rectangle) fromPane.getChildren().get(fromPane.getChildren().size() - 1); // get the disk at the top
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