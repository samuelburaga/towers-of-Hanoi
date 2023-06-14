package com.example.towersofhanoi;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

public class Game {
    public static boolean gameOver = false;
    public static byte disks = 2;
    public static long startTime, endTime, duration;
    public static Button moveButton;
    private static Button AToBButton, AToCButton, BToAButton, BToCButton, CToAButton, CToBButton;
    private static Pane rodA, rodB, rodC; // Add references to the rod panes
    private static Duration moveDelay = Duration.seconds(1.5);
    private static Thread animationThread;
    public static boolean validMove(char fromRod, char toRod) {
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


    //    public static boolean validMove(char fromRod, char toRod) {
//        Pane fromPane = getRodPane(fromRod);
//        Pane toPane = getRodPane(toRod);
//        if (fromPane.getChildren().isEmpty()) {
//            return false; // "from" rod is empty, move is not valid
//        }
//
//        if (toPane.getChildren().isEmpty()) {
//            return true; // "to" rod is empty, move is valid
//        }
//
//        Rectangle topDiskFrom = (Rectangle) fromPane.getChildren().get(fromPane.getChildren().size() - 1);
//        Rectangle topDiskTo = (Rectangle) toPane.getChildren().get(toPane.getChildren().size() - 1);
//
//        double sizeFrom = topDiskFrom.getWidth();
//        double sizeTo = topDiskTo.getWidth();
//
//        return sizeFrom < sizeTo; // Move is valid if the disk on "from" rod is smaller than the one on "to" rod
//    }
    public static boolean checkState() {
       // return gameOver;
        byte disksOnLastRod = (byte) rodC.getChildren().size();
        gameOver = disksOnLastRod == Game.disks;
        return gameOver;
    }


    public static void setRods(Pane a, Pane b, Pane c) {
        rodA = a;
        rodB = b;
        rodC = c;
    }
    public static void setButtons(Button AB, Button AC, Button BA, Button BC, Button CA, Button CB) {
        AToBButton = AB;
        AToCButton = AC;
        BToAButton = BA;
        BToCButton = BC;
        CToAButton = CA;
        CToBButton = CB;
    }
    private static Pane getRodPane(char rod) {
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
    public static void play() {

    }
    public static void automatic(Runnable onFinishCallback) {
        animationThread = new Thread(new AnimationRunnable(onFinishCallback));
        animationThread.start();
    }
    private static class AnimationRunnable implements Runnable {
        private final Runnable onFinishCallback;
        public AnimationRunnable(Runnable onFinishCallback) {
            this.onFinishCallback = onFinishCallback;
        }
        @Override
        public void run() {
            recursiveHanoi(disks, 'A', 'C', 'B');
            animationThread = null; // Set the animation thread to null after completion
            Platform.runLater(onFinishCallback);
        }
    }
    public static void recursiveHanoi(byte numberOfDisks, char fromRod, char toRod, char auxRod) {
        if (numberOfDisks == 0) {
            return;
        }
        recursiveHanoi((byte) (numberOfDisks - 1), fromRod, auxRod, toRod);
        System.out.println("Move disk " + numberOfDisks + " from rod " + fromRod + " to rod " + toRod);
        moveDisk(numberOfDisks, fromRod, toRod);
        recursiveHanoi((byte) (numberOfDisks - 1), auxRod, toRod, fromRod);
    }
    public static void moveDisk(byte diskNumber, char fromRod, char toRod) {
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
        try {
            TimeUnit.MILLISECONDS.sleep((long) moveDelay.toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        moveButton.setStyle("-fx-background-color: #FA8163;");
    }
    private static void animateDiskMovement(Rectangle disk, Pane toPane) {
        double initialY = disk.getY();
        double targetY = toPane.getPrefHeight() - (toPane.getChildren().size() * disk.getHeight());

        disk.setTranslateY(initialY - targetY);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(1.5), disk);
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

