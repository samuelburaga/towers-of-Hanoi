package com.example.towersofhanoi.Controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TutorialController {
    @FXML
    private Pane rodA;
    private byte numberOfDisks;
    public void setNumberOfDisks(byte numberOfDisks) {
        this.numberOfDisks = numberOfDisks;
        createDisks();
    }

    private void createDisks() {
        rodA.getChildren().clear(); // Clear any existing disks
        double diskWidth = 172.0; // Adjust as needed
        double diskHeight = 40.0; // Adjust as needed
        double initialX = 0, initialY = rodA.getPrefHeight() - diskHeight; // Adjust as needed

        for (byte i = 0; i < numberOfDisks; i++) {
            Rectangle disk = new Rectangle(diskWidth - i * 20, diskHeight);
            disk.setFill(Color.RED); // Adjust as needed
            disk.setStroke(Color.BLACK); // Adjust as needed
            disk.setX(initialX + (i * 10));
            disk.setLayoutY(initialY - (i * diskHeight));
            System.out.println(disk.getLayoutY());
            rodA.getChildren().add(disk);
        }
    }


}
