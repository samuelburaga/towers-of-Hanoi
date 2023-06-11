//package com.example.towersofhanoi;
//
//import javafx.animation.PauseTransition;
//import javafx.animation.TranslateTransition;
//import javafx.application.Platform;
//import javafx.scene.layout.Pane;
//import javafx.scene.shape.Rectangle;
//import javafx.util.Duration;
//
//import java.util.concurrent.TimeUnit;
//
//public class Game {
//    public static byte disks = 2;
//    private static Pane rodA, rodB, rodC; // Add references to the rod panes
//    private static Duration moveDelay = Duration.seconds(2);
//
//    public static void setRods(Pane a, Pane b, Pane c) {
//        rodA = a;
//        rodB = b;
//        rodC = c;
//    }
//
//    private static Pane getRodPane(char rod) {
//        switch (rod) {
//            case 'A':
//                return rodA;
//            case 'B':
//                return rodB;
//            case 'C':
//                return rodC;
//            default:
//                throw new IllegalArgumentException("Invalid rod: " + rod);
//        }
//    }
//
//    public static void automatic() {
//        recursiveHanoi(disks, 'A', 'C', 'B');
//    }
//
//    public static void recursiveHanoi(byte numberOfDisks, char fromRod, char toRod, char auxRod) {
//        if (numberOfDisks == 0) {
//            return;
//        }
//        recursiveHanoi((byte) (numberOfDisks - 1), fromRod, auxRod, toRod);
//        System.out.println("Move disk " + numberOfDisks + " from rod " + fromRod + " to rod " + toRod);
//        moveDisk(numberOfDisks, fromRod, toRod);
//        recursiveHanoi((byte) (numberOfDisks - 1), auxRod, toRod, fromRod);
//    }
//
//
//    private static void moveDisk(byte diskNumber, char fromRod, char toRod) {
//        Platform.runLater(() -> {
//            Pane fromPane = getRodPane(fromRod);
//            Pane toPane = getRodPane(toRod);
//            Rectangle disk = (Rectangle) fromPane.getChildren().remove(fromPane.getChildren().size() - 1);
//            double diskWidth = disk.getWidth();
//            disk.setWidth(diskWidth - (diskNumber * 12));
//            disk.setX((toPane.getPrefWidth() - disk.getWidth()) / 2);
//            toPane.getChildren().add(disk);
//        });
//    }
//}
package com.example.towersofhanoi;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

public class Game {
    public static byte disks = 2;
    private static Pane rodA, rodB, rodC; // Add references to the rod panes
    private static Duration moveDelay = Duration.seconds(2);

    public static void setRods(Pane a, Pane b, Pane c) {
        rodA = a;
        rodB = b;
        rodC = c;
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

    public static void automatic() {
        Thread animationThread = new Thread(() -> recursiveHanoi(disks, 'A', 'C', 'B'));
        animationThread.start();
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


    private static void moveDisk(byte diskNumber, char fromRod, char toRod) {
        Platform.runLater(() -> {
            Pane fromPane = getRodPane(fromRod);
            Pane toPane = getRodPane(toRod);
            Rectangle disk = (Rectangle) fromPane.getChildren().remove(fromPane.getChildren().size() - 1);
            toPane.getChildren().add(disk);
            animateDiskMovement(disk, toPane);
        });

        try {
            TimeUnit.MILLISECONDS.sleep((long) moveDelay.toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void animateDiskMovement(Rectangle disk, Pane toPane) {
        double initialY = disk.getY();
        double targetY = toPane.getPrefHeight() - (toPane.getChildren().size() * disk.getHeight());

        disk.setTranslateY(initialY - targetY);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), disk);
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
