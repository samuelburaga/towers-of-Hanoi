package com.example.towersofhanoi;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Game {
    public static byte disks = 4;
    private static Pane rodA, rodB, rodC; // Add references to the rod panes
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
            recursiveHanoi(disks, 'A', 'C', 'B');
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
            double diskWidth = disk.getWidth();
            disk.setWidth(diskWidth - (diskNumber * 12));
            disk.setX((toPane.getPrefWidth() - disk.getWidth()) / 2);
            toPane.getChildren().add(disk);
        });
    }

}
