package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Game;
import com.example.towersofhanoi.HelloApplication;
import com.example.towersofhanoi.Tutorial;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class TutorialController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Pane rodA, rodB, rodC;
    @FXML
    private Button AToBButton, AToCButton, BToAButton, BToCButton, CToAButton, CToBButton;
    @FXML
    private Button solveButton;
    private byte numberOfDisks;
    private String move;
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
        Game.setRods(rodA, rodB, rodC); // Pass references to the Game class
        Game.setButtons(AToBButton, AToCButton, BToAButton, BToCButton, CToAButton, CToBButton); // Pass references to the Game class
        Game.startTime = System.currentTimeMillis(); // Record the start time
        Game.automatic(() -> {
            Game.endTime = System.currentTimeMillis(); // Record the end time
            Game.duration = Game.endTime - Game.startTime;
            System.out.println("Tower of Hanoi solved in " + Game.duration + " milliseconds.");
            try {
                switchToSolvedScene();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    public void switchToSolvedScene() throws IOException {
        Stage stage = (Stage) solveButton.getScene().getWindow();
        Parent root = FXMLLoader.load(Tutorial.class.getResource("View/Solved.fxml"));
        Scene solvedScene = new Scene(root);
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5)); // Adjust the duration as needed
        pause.setOnFinished(event -> {
            stage.setScene(solvedScene);
            stage.show();
        });
        pause.play();
    }
}
