package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.*;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Time;
public class PlayController {
    @FXML
    private Pane rodA, rodB, rodC;
    @FXML
    private Button AToBButton, AToCButton, BToAButton, BToCButton, CToAButton, CToBButton;
    private String move;
    public void drawDisks() {
        rodA.getChildren().clear(); // Clear any existing disks
        double diskWidth = 198.0, diskHeight = 60.0; // Adjust as needed
        double initialX = 0, initialY = rodA.getPrefHeight() - diskHeight; // Adjust as needed
        double arcWidth = 30.0, arcHeight = 30.0;
        for (byte i = 0; i < Game.numberOfDisks; i++) {
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
    public void connectGameToUI() {
        Play.playerGame.setRods(rodA, rodB, rodC);
        Play.playerGame.setButtons(AToBButton, AToCButton, BToAButton, BToCButton, CToAButton, CToBButton);
    }
    public void startGame() {
        Play.playerGame.startTime = System.currentTimeMillis();
    }
    public void moveOptionOnAction(ActionEvent e) throws IOException {
        Button clickedButton = (Button) e.getSource();
        String clickedButtonId = clickedButton.getId();
        char fromRod = clickedButtonId.charAt(0);
        char toRod = clickedButtonId.charAt(3);
        if(Play.playerGame.isGameOver() == false) {
            if(Play.playerGame.isMoveValid(fromRod, toRod)) {
                Play.playerGame.runAnimation(fromRod, toRod);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Move");
                alert.setHeaderText(null);
                alert.setContentText("This move is not allowed.");
                alert.showAndWait();
            }
        }
        else {

            Play.playerGame.endTime = System.currentTimeMillis();
            Play.playerGame.duration = Play.playerGame.endTime - Play.playerGame.startTime;
            Play.playerGame.score = (int) (100 / (Play.playerGame.duration / 1000));


            Time time = new Time(Play.playerGame.duration);
            DatabaseConnection databaseConnection = new DatabaseConnection();
            databaseConnection.connect();
            databaseConnection.Statement();
            String query = "INSERT INTO statistics (user_id, disks, points, time) VALUES (?, ?, ?, time)";
            String[] variables = new String[4];
            variables[0] = Integer.toString(Users.user_id);
            variables[1] = Byte.toString(Game.numberOfDisks);
            variables[2] = Integer.toString(Play.playerGame.score);
            variables[3] = time.toString();
            databaseConnection.executeUpdateWithVariables(query, variables);

            Stage stage = (Stage) clickedButton.getScene().getWindow();
            Parent root = FXMLLoader.load(Tutorial.class.getResource("View/Solved.fxml"));
            Scene solvedScene = new Scene(root);
            stage.setScene(solvedScene);
            stage.show();
        }
    }
}
