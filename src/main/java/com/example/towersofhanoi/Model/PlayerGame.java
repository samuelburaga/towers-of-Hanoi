package com.example.towersofhanoi.Model;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

/**
 * The PlayerGame class represents a game of Towers of Hanoi played by a player.
 * It extends the Game class and adds functionality specific to player-controlled moves and animations.
 */
public class PlayerGame extends Game {

    /**
     * Constructs a new PlayerGame object with default values.
     */
    public PlayerGame() {

    }

    /**
     * Constructs a new PlayerGame object with the specified number of disks and move animation speed.
     *
     * @param numberOfDisks      the number of disks in the game
     * @param moveAnimationSpeed the speed of the move animation in seconds
     */
    public PlayerGame(final byte numberOfDisks, final double moveAnimationSpeed) {
        this.numberOfDisks = numberOfDisks;
        this.moveAnimationSpeed = Duration.seconds(moveAnimationSpeed);
    }

    private char fromRod, toRod;

    /**
     * Checks if a move from one rod to another is valid.
     *
     * @param fromRod the source rod
     * @param toRod   the destination rod
     * @return true if the move is valid, false otherwise
     */
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
    }

    /**
     * Checks if the game is over (i.e., all disks are on the last rod).
     *
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        byte disksOnLastRod = (byte) rodC.getChildren().size();
        gameOver = disksOnLastRod == this.getNumberOfDisks() ? true : false;
        return gameOver;
    }

    /**
     * Runs the animation for moving a disk from one rod to another.
     *
     * @param fromRod the source rod
     * @param toRod   the destination rod
     */
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
    }

    /**
     * Moves a disk from one rod to another and animates the movement.
     *
     * @param fromRod the source rod
     * @param toRod   the destination rod
     */
    public void moveDisk(char fromRod, char toRod) {
        Platform.runLater(() -> {
            Pane fromPane = getRodPane(fromRod);
            Pane toPane = getRodPane(toRod);

            // Get the moveButton
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

        // Synchronize the execution of the code and allow the animation to finish before setting the button's style to #FA8163
        try {
            TimeUnit.MILLISECONDS.sleep((long) (4 * moveAnimationSpeed.toMillis()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        moveButton.setStyle("-fx-background-color: #FA8163;");
    }
}
