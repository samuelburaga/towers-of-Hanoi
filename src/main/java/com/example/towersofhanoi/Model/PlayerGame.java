package com.example.towersofhanoi.Model;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

public class PlayerGame extends Game {
    public PlayerGame() {

    }
    public PlayerGame(final byte numberOfDisks, final double moveAnimationSpeed) {
        this.numberOfDisks = numberOfDisks;
        this.moveAnimationSpeed = Duration.seconds(moveAnimationSpeed);
    }
    private char fromRod, toRod;
    public boolean isMoveValid(char fromRod, char toRod) {
        Pane fromPane = getRodPane(fromRod);
        Pane toPane = getRodPane(toRod);
        if (fromPane == null || toPane == null) {
            return false; // Invalid move if either rod is null
        }
        if (fromPane.getChildren().isEmpty()) {
            return false; // "from" rod is empty, move is not valid
        }
        // Get the top disk on the "to" rod, if it exists
        Rectangle topDisk = toPane.getChildren().isEmpty() ? null : (Rectangle) toPane.getChildren().get(toPane.getChildren().size() - 1);
        Rectangle movingDisk = (Rectangle) fromPane.getChildren().get(fromPane.getChildren().size() - 1);
        if (topDisk != null && movingDisk.getWidth() >= topDisk.getWidth()) {
            return false; // Moving disk is larger or equal to the top disk on the "to" rod, move is not valid
        }
        return true; // Move is valid
    } // check if the move is valid
    public boolean isGameOver() {
        byte disksOnLastRod = (byte) rodC.getChildren().size();
        gameOver = disksOnLastRod == this.getNumberOfDisks() ? true:false;
        return gameOver;
    }
    public void runAnimation(char fromRod, char toRod) {
        this.fromRod = fromRod;
        this.toRod = toRod;
        Thread animationThread = new Thread(new PlayerGame.Animation()); // create the thread
        animationThread.start(); // start the thread
    }
    private class Animation implements Runnable {
        public Animation() {

        }
        @Override
        public void run() {
            moveDisk(fromRod, toRod);
        }
    }  // thread class
    public void moveDisk(char fromRod, char toRod) {
        Platform.runLater(() -> {
            Pane fromPane = getRodPane(fromRod);
            Pane toPane = getRodPane(toRod);
            // get the moveButton
            switch (fromRod + "To" + toRod) {
                case "AToB":
                    if (AToBButton != null) {
                        moveButton = AToBButton;
                    }
                    break;
                case "AToC":
                    if (AToCButton != null) {
                        moveButton = AToCButton;
                    }
                    break;
                case "BToA":
                    if (BToAButton != null) {
                        moveButton = BToAButton;
                    }

                    break;
                case "BToC":
                    if (BToCButton != null) {
                        moveButton = BToCButton;
                    }
                    break;
                case "CToA":
                    if (CToAButton != null) {
                        moveButton = CToAButton;
                    }
                    break;
                case "CToB":
                    if (CToBButton != null) {
                        moveButton = CToBButton;
                    }
                    break;
                default:
                    break;
            }
            moveButton.setStyle("-fx-background-color: #0FB4BB;"); // Set the color for the newly clicked button
            animateDiskMovement(fromPane, toPane); // run the animation
        });
        // synchronize the execution of the code and allow the animation to finish before setting the button's style to #FA8163
        try {
            TimeUnit.MILLISECONDS.sleep((long) (4 * moveAnimationSpeed.toMillis()));
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        moveButton.setStyle("-fx-background-color: #FA8163;");
    }
}
