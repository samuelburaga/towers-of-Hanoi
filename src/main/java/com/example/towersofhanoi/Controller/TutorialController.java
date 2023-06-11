package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TutorialController {
    @FXML
    private Pane rodA, rodB, rodC;
    @FXML
    private Button solveButton;
    private byte numberOfDisks;
    public void setNumberOfDisks(byte numberOfDisks) {
        this.numberOfDisks = numberOfDisks;
        createDisks();
    }

    private void createDisks() {
        rodA.getChildren().clear(); // Clear any existing disks
        double diskWidth = 198.0; // Adjust as needed
        double diskHeight = 60.0; // Adjust as needed
        double initialX = 0, initialY = rodA.getPrefHeight() - diskHeight; // Adjust as needed
        double arcWidth = 30.0;
        double arcHeight = 30.0;
        for (byte i = 0; i < numberOfDisks; i++) {
            Rectangle disk = new Rectangle(diskWidth - (i * 12), diskHeight);
            disk.setX(initialX + (i * 6));
            disk.setLayoutY(initialY - (i * diskHeight));
            disk.setArcHeight(arcHeight);
            disk.setArcWidth(arcWidth);
            disk.setFill(Color.valueOf("#0FB4BB")); // Adjust as needed
            disk.setStroke(Color.WHITE); // Adjust as needed
            rodA.getChildren().add(disk);
        }
    }
    public void solveButtonOnAction(ActionEvent e) {
        Game.automatic();
    }
}
