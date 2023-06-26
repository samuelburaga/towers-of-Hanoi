package com.example.towersofhanoi;

import javafx.application.Platform;
import javafx.scene.layout.Pane;

import java.util.concurrent.TimeUnit;

public class AutomaticGame extends Game {
    public boolean isGameOver() {
        byte disksOnLastRod = (byte) rodC.getChildren().size();
        gameOver = disksOnLastRod == this.numberOfDisks ? true:false;
        return gameOver;
    }
    public void recursiveHanoi(byte numberOfDisks, char fromRod, char toRod, char auxRod) {
        if (numberOfDisks == 0) {
            return;
        }
        recursiveHanoi((byte) (numberOfDisks - 1), fromRod, auxRod, toRod);
        // System.out.println("Move disk " + numberOfDisks + " from rod " + fromRod + " to rod " + toRod);
        moveDisk(fromRod, toRod);
        recursiveHanoi((byte) (numberOfDisks - 1), auxRod, toRod, fromRod);
    }
    public void runAlgorithm(Runnable onFinishCallback) {
        animationThread = new Thread(AutomaticGame.this.new Animation(onFinishCallback));
        animationThread.start();
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
    }
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
                    // handle invalid move
                    break;
            }
            moveButton.setStyle("-fx-background-color: #0FB4BB;");
            // animateDiskMovement(disk, toPane);
            animateDiskMovement(fromPane, toPane);
        });
        try {
            TimeUnit.MILLISECONDS.sleep((long) (4 * moveAnimationSpeed.toMillis()));
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        moveButton.setStyle("-fx-background-color: #FA8163;");
    }
}
