package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.View.TutorialView;
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
    public void solveButtonOnAction(ActionEvent e) {
        connectGameToUI();
        TutorialView.automaticGame.startTime = System.currentTimeMillis(); // Record the start time
        // run the algorithm
        TutorialView.automaticGame.runAlgorithm(() -> {
            TutorialView.automaticGame.endTime = System.currentTimeMillis(); // Record the end time
            TutorialView.automaticGame.duration = TutorialView.automaticGame.endTime - TutorialView.automaticGame.startTime;
            TutorialView.automaticGame.gameOver = true;
            System.out.println("Towers of Hanoi was solved in " + TutorialView.automaticGame.duration + " milliseconds.");
            try {
                switchToSolvedScene();
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    } // start the automatic game
    public void connectGameToUI() {
        TutorialView.automaticGame.setRods(rodA, rodB, rodC);
        TutorialView.automaticGame.setButtons(AToBButton, AToCButton, BToAButton, BToCButton, CToAButton, CToBButton);
    } // connect the game to the UI objects
    public void drawDisks() {
        rodA.getChildren().clear(); // Clear any existing disks
        double diskWidth = 198.0, diskHeight = 60.0; // initial dimenstions
        double initialX = 0, initialY = rodA.getPrefHeight() - diskHeight; // initial coordinates
        double arcWidth = 30.0, arcHeight = 30.0;
        // draw the disks
        for (byte index = 0; index < TutorialView.automaticGame.getNumberOfDisks(); index++) {
            Rectangle disk = new Rectangle(diskWidth - (index * 12), diskHeight);
            disk.setLayoutX(initialX + (index * 6));
            disk.setLayoutY(initialY - (index * diskHeight));
            disk.setArcHeight(arcHeight);
            disk.setArcWidth(arcWidth);
            disk.setFill(Color.valueOf("#0FB4BB"));
            rodA.getChildren().add(disk);
        }
    } // draw the disks on the screen
    public void switchToSolvedScene() throws IOException {
        Stage stage = (Stage) solveButton.getScene().getWindow();
        Parent root = FXMLLoader.load(TutorialView.class.getResource("View/Solved.fxml"));
        Scene solvedScene = new Scene(root);
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5)); // Let the animation finish
        pause.setOnFinished(event -> {
            stage.setScene(solvedScene);
            stage.show();
        });
        pause.play();
    } // show this solution's statistics
}
