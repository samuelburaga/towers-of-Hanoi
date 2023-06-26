package com.example.towersofhanoi;

import javafx.application.Platform;
import javafx.scene.layout.Pane;

import java.util.concurrent.TimeUnit;

public class AutomaticGame extends Game {
    public boolean isGameOver() {
        byte disksOnLastRod = (byte) rodC.getChildren().size();
        gameOver = disksOnLastRod == this.numberOfDisks ? true:false;
        return gameOver;
    } // check if the game is over
    public void recursiveHanoi(byte numberOfDisks, char fromRod, char toRod, char auxRod) {
        if (numberOfDisks == 0) {
            return;
        }
        recursiveHanoi((byte) (numberOfDisks - 1), fromRod, auxRod, toRod);
        // System.out.println("Move disk " + numberOfDisks + " from rod " + fromRod + " to rod " + toRod);
        moveDisk(fromRod, toRod);
        recursiveHanoi((byte) (numberOfDisks - 1), auxRod, toRod, fromRod);
    } // Towers of Hanoi recursive algorithm
    public void runAlgorithm(Runnable onFinishCallback) {
        animationThread = new Thread(AutomaticGame.this.new Animation(onFinishCallback)); // create the thread
        animationThread.start(); // start the thread
    }
    private class Animation implements Runnable {
        private final Runnable onFinishCallback;
        public Animation(Runnable onFinishCallback) {
            this.onFinishCallback = onFinishCallback;
        }
        @Override
        public void run() {
            recursiveHanoi(numberOfDisks, 'A', 'C', 'B');
            animationThread = null; // Set the animation thread to null after completion
            Platform.runLater(onFinishCallback);
        }
    } // thread class
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
        moveButton.setStyle("-fx-background-color: #FA8163;"); // Revert the color of the previously clicked label
    }
}
