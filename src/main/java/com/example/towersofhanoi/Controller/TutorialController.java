package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Tutorial;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class TutorialController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane rodA, rodB, rodC;
    @FXML
    private Button AToBButton, AToCButton, BToAButton, BToCButton, CToAButton, CToBButton;
    @FXML
    private Button solveButton;
    private String move;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void solveButtonOnAction(ActionEvent e) {
        connectGameToUI();
        Tutorial.automaticGame.startTime = System.currentTimeMillis(); // Record the start time
        Tutorial.automaticGame.runAlgorithm(() -> {
            Tutorial.automaticGame.endTime = System.currentTimeMillis(); // Record the end time
            Tutorial.automaticGame.duration = Tutorial.automaticGame.endTime - Tutorial.automaticGame.startTime;
            Tutorial.automaticGame.gameOver = true;
            System.out.println("Towers of Hanoi was solved in " + Tutorial.automaticGame.duration + " milliseconds.");
            try {
                switchToSolvedScene();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    public void connectGameToUI() {
        Tutorial.automaticGame.setAnchorPane(anchorPane);
        Tutorial.automaticGame.setRods(rodA, rodB, rodC);
        Tutorial.automaticGame.setButtons(AToBButton, AToCButton, BToAButton, BToCButton, CToAButton, CToBButton);
    }
    public void drawDisks() {
        rodA.getChildren().clear(); // Clear any existing disks
        double diskWidth = 198.0, diskHeight = 60.0; // Adjust as needed
        double initialX = 0, initialY = rodA.getPrefHeight() - diskHeight; // Adjust as needed
        double arcWidth = 30.0, arcHeight = 30.0;
        for (byte index = 0; index < Tutorial.automaticGame.getNumberOfDisks(); index++) {
            Rectangle disk = new Rectangle(diskWidth - (index * 12), diskHeight);
            disk.setLayoutX(initialX + (index * 6));
            disk.setLayoutY(initialY - (index * diskHeight));
            disk.setArcHeight(arcHeight);
            disk.setArcWidth(arcWidth);
            disk.setFill(Color.valueOf("#0FB4BB")); // Adjust as needed
            // disk.setStroke(Color.WHITE); // Adjust as needed
            rodA.getChildren().add(disk);
        }
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
