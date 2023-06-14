package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Game;
import com.example.towersofhanoi.Tutorial;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class PlayController {
    @FXML
    private Pane rodA, rodB, rodC;
    @FXML
    private Button AToBButton, AToCButton, BToAButton, BToCButton, CToAButton, CToBButton;
    private byte numberOfDisks;
    private String move;
    public void setNumberOfDisks(byte numberOfDisks) {
        this.numberOfDisks = numberOfDisks;
        createDisks();
    }
    public void setRods() {
        Game.setRods(rodA, rodB, rodC); // Pass references to the Game class
        Game.setButtons(AToBButton, AToCButton, BToAButton, BToCButton, CToAButton, CToBButton); // Pass references to the Game class
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
    public void moveOptionOnAction(ActionEvent e) {
        Button clickedButton = (Button) e.getSource();
        String clickedButtonId = clickedButton.getId();
        char fromRod = clickedButtonId.charAt(0);
        char toRod = clickedButtonId.charAt(3);
        if(Game.validMove(fromRod, toRod)) {
            switch (fromRod + "To" + toRod) {
                case "AToB":
                    if (AToBButton != null) {
                        Game.moveButton = AToBButton;
                    }
                    break;
                case "AToC":
                    if (AToCButton != null) {
                        Game.moveButton = AToCButton;
                    }
                    break;
                case "BToA":
                    if (BToAButton != null) {
                        Game.moveButton = BToAButton;
                    }

                    break;
                case "BToC":
                    if (BToCButton != null) {
                        Game.moveButton = BToCButton;
                    }
                    break;
                case "CToA":
                    if (CToAButton != null) {
                        Game.moveButton = CToAButton;
                    }
                    break;
                case "CToB":
                    if (CToBButton != null) {
                        Game.moveButton = CToBButton;
                    }
                    break;
                default:
                    // handle invalid move
                    break;
            }
            if(!Game.checkState()) {
                Game.moveDisk((byte) 1, fromRod, toRod);
            }
        }
        else {
            System.out.println("NO");
        }
    }
}
