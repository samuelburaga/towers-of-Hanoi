package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Model.*;
import com.example.towersofhanoi.View.PlayView;
import com.example.towersofhanoi.View.TutorialView;
import com.example.towersofhanoi.View.UserSolvedView;
import javafx.animation.AnimationTimer;
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

import javax.swing.text.html.Option;
import java.io.IOException;
import java.sql.Time;

public class PlayController {
    @FXML
    private Pane rodA, rodB, rodC;
    @FXML
    private Button AToBButton, AToCButton, BToAButton, BToCButton, CToAButton, CToBButton;
    private String move;
    private PlayerGame playerGame;
    private DatabaseConnection mySQLConnection, mongoDBConnection;
    public PlayController() {
        this.mySQLConnection = new MySQLConnection();
        this.mongoDBConnection = new MongoDBConnection();
        this.playerGame = new PlayerGame(OptionsController.numberOfDisks, OptionsController.moveAnimationSpeed);
        // this.startGame();
    }
    public void drawDisks() {
        rodA.getChildren().clear(); // Clear any existing disks
        double diskWidth = 198.0, diskHeight = 60.0; // Adjust as needed
        double initialX = 0, initialY = rodA.getPrefHeight() - diskHeight; // Adjust as needed
        double arcWidth = 30.0, arcHeight = 30.0;
        for (byte index = 0; index < this.playerGame.getNumberOfDisks(); index++) {
            Rectangle disk = new Rectangle(diskWidth - (index * 12), diskHeight);
            disk.setX(initialX + (index * 6));
            disk.setLayoutY(initialY - (index * diskHeight));
            disk.setArcHeight(arcHeight);
            disk.setArcWidth(arcWidth);
            // Calculate the color based on the index
            double hue = 182.0;  // Base hue value
            double saturation = 0.92 - (index + 1) * (0.91 / this.playerGame.getNumberOfDisks());  // Saturation value based on index
            double brightness = 0.73 + (index + 1) * (0.26 / this.playerGame.getNumberOfDisks());  // Adjust brightness based on index
            Color diskColor = Color.hsb(hue, saturation, brightness);
            disk.setFill(diskColor);
            rodA.getChildren().add(disk);
        }
    }
    public void connectGameToUI() {
        this.playerGame.setRods(rodA, rodB, rodC);
        this.playerGame.setButtons(AToBButton, AToCButton, BToAButton, BToCButton, CToAButton, CToBButton);
    }
    public void startGame() {
        this.playerGame.startTime = System.currentTimeMillis();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (playerGame.isGameOver()) {
                    stop(); // Stop the timer when the game is over
                    playerGame.endTime = System.currentTimeMillis();
                    playerGame.duration = (playerGame.endTime - playerGame.startTime) / 1000;
                    // Extract hours, minutes, and seconds from the duration
                    int hours = (int) (playerGame.duration / 3600);
                    int minutes = (int) ((playerGame.duration % 3600) / 60);
                    int seconds = (int) (playerGame.duration % 60);
                    // Create a new `Time` object using the hours, minutes, and seconds
                    playerGame.time = new Time(hours, minutes, seconds);
                    playerGame.points = playerGame.numberOfGoodMoves * 10 - playerGame.numberOfBadMoves;
                    // insertStatisticsInDatabase();
                    switchToSolvedScene();
                }
            }
        };
        timer.start(); // Start the timer
    }
    public void moveOptionOnAction(ActionEvent e) throws IOException {
        Button clickedButton = (Button) e.getSource();
        String clickedButtonId = clickedButton.getId();
        char fromRod = clickedButtonId.charAt(0);
        char toRod = clickedButtonId.charAt(3);
        if (this.playerGame.isMoveValid(fromRod, toRod)) {
            this.playerGame.numberOfMoves++;
            this.playerGame.numberOfGoodMoves++;
            this.playerGame.runAnimation(fromRod, toRod);
        }
        else {
            this.playerGame.numberOfBadMoves++;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Move");
            alert.setHeaderText(null);
            alert.setContentText("This move is not allowed.");
            alert.showAndWait();
        }
    }
    private void insertStatisticsInDatabase() {
        mySQLConnection.connect();
        String query = "INSERT INTO statistics (users_user_id, disks, points, time) VALUES (?, ?, ?, ?)";
        String[] variables = new String[4];
        variables[0] = Integer.toString(User.user_id);
        variables[1] = Byte.toString(this.playerGame.getNumberOfDisks());
        variables[2] = Integer.toString(this.playerGame.points);
        variables[3] = playerGame.time.toString();
        ((MySQLConnection) mySQLConnection).executeUpdateWithVariables(query, variables);
    }
    private void switchToSolvedScene() {
        Stage stage = (Stage) rodA.getScene().getWindow();
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(UserSolvedView.class.getResource("UserSolved.fxml"));
            root = fxmlLoader.load();
            UserSolvedController controller = fxmlLoader.getController();
            // Set the statistics in the controller
            controller.setStatistics(this.playerGame);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene solvedScene = new Scene(root);
        PauseTransition pause = new PauseTransition(Duration.seconds(1)); // Let the animation finish
        pause.setOnFinished(event -> {
            stage.setScene(solvedScene);
            stage.show();
        });
        pause.play();
    }

}
