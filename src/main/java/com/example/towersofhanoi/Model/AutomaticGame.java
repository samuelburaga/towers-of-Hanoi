package com.example.towersofhanoi.Model;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

/**
 * The AutomaticGame class represents an automatic game of the Towers of Hanoi. It extends the Game class
 * and provides additional functionality for running the game automatically using a recursive algorithm.
 */
public class AutomaticGame extends Game {

    /**
     * Default constructor for the AutomaticGame class.
     */
    public AutomaticGame() {

    }

    /**
     * Constructs an AutomaticGame with the specified number of disks and move animation speed.
     *
     * @param numberOfDisks       the number of disks in the game
     * @param moveAnimationSpeed  the speed of the move animation
     */
    public AutomaticGame(final byte numberOfDisks, final double moveAnimationSpeed) {
        this.numberOfDisks = numberOfDisks;
        this.moveAnimationSpeed = Duration.seconds(moveAnimationSpeed);
    }

    /**
     * Checks if the game is over by comparing the number of disks on the last rod with the total number of disks.
     *
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        byte disksOnLastRod = (byte) rodC.getChildren().size();
        gameOver = disksOnLastRod == this.numberOfDisks;
        return gameOver;
    }

    /**
     * Executes the recursive Towers of Hanoi algorithm to solve the game.
     *
     * @param numberOfDisks the number of disks to be moved
     * @param fromRod       the rod to move the disks from
     * @param toRod         the rod to move the disks to
     * @param auxRod        the auxiliary rod
     */
    public void recursiveHanoi(byte numberOfDisks, char fromRod, char toRod, char auxRod) {
        if (numberOfDisks == 0) {
            return;
        }
        recursiveHanoi((byte) (numberOfDisks - 1), fromRod, auxRod, toRod);
        moveDisk(fromRod, toRod);
        recursiveHanoi((byte) (numberOfDisks - 1), auxRod, toRod, fromRod);
    }

    /**
     * Runs the automatic game algorithm in a separate thread and invokes the onFinishCallback upon completion.
     *
     * @param onFinishCallback the callback to be executed when the game algorithm finishes
     */
    public void runAlgorithm(Runnable onFinishCallback) {
        animationThread = new Thread(AutomaticGame.this.new Animation(onFinishCallback));
        animationThread.start();
    }

    /**
     * The Animation class represents the animation thread for the automatic game algorithm.
     */
    private class Animation implements Runnable {
        private final Runnable onFinishCallback;

        /**
         * Constructs an Animation object with the specified onFinishCallback.
         *
         * @param onFinishCallback the callback to be executed when the animation finishes
         */
        public Animation(Runnable onFinishCallback) {
            this.onFinishCallback = onFinishCallback;
        }

        /**
         * Executes the automatic game algorithm and invokes the onFinishCallback upon completion.
         */
        @Override
        public void run() {
            recursiveHanoi(numberOfDisks, 'A', 'C', 'B');
            animationThread = null;
            Platform.runLater(onFinishCallback);
        }
    }

    /**
     * Moves a disk from the specified source rod to the specified destination rod.
     *
     * @param fromRod the source rod
     * @param toRod   the destination rod
     */
    public void moveDisk(char fromRod, char toRod) {
        Platform.runLater(() -> {
            Pane fromPane = getRodPane(fromRod);
            Pane toPane = getRodPane(toRod);
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
            moveButton.setStyle("-fx-background-color: #0FB4BB;");
            animateDiskMovement(fromPane, toPane);
        });

        try {
            TimeUnit.MILLISECONDS.sleep((long) (4 * moveAnimationSpeed.toMillis()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        moveButton.setStyle("-fx-background-color: #FA8163;");
    }
}