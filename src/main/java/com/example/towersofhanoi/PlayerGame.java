package com.example.towersofhanoi;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.TimeUnit;

public class PlayerGame extends Game {
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
    }
    public boolean isGameOver() {
        byte disksOnLastRod = (byte) rodC.getChildren().size();
        gameOver = disksOnLastRod == Game.numberOfDisks ? true:false;
        System.out.println(gameOver);
        return gameOver;
    }
    public void runAnimation(char fromRod, char toRod) {
        this.fromRod = fromRod;
        this.toRod = toRod;
        Thread animationThread = new Thread(new PlayerGame.Animation());
        animationThread.start();
    }
    private class Animation implements Runnable {
        public Animation() {

        }
        @Override
        public void run() {
            moveDisk(fromRod, toRod);
        }
    }
    public void moveDisk(char fromRod, char toRod) {
        Platform.runLater(() -> {
            Pane fromPane = getRodPane(fromRod);
            Pane toPane = getRodPane(toRod);
            Rectangle disk = (Rectangle) fromPane.getChildren().remove(fromPane.getChildren().size() - 1);
            toPane.getChildren().add(disk);
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
            animateDiskMovement(disk, toPane);
        });
        moveButton.setStyle("-fx-background-color: #FA8163;");
    }
}
