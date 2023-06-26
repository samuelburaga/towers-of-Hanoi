package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Model.PlayerGame;
import com.example.towersofhanoi.View.PlayView;
import com.example.towersofhanoi.View.TutorialView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.swing.text.html.Option;
import java.io.IOException;
public class PlayController {
    @FXML
    private Pane rodA, rodB, rodC;
    @FXML
    private Button AToBButton, AToCButton, BToAButton, BToCButton, CToAButton, CToBButton;
    private String move;
    private PlayerGame playerGame;
    public PlayController() {
        this.playerGame = new PlayerGame(OptionsController.numberOfDisks, OptionsController.moveAnimationSpeed);
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
    }
    public void moveOptionOnAction(ActionEvent e) throws IOException {
        Button clickedButton = (Button) e.getSource();
        String clickedButtonId = clickedButton.getId();
        char fromRod = clickedButtonId.charAt(0);
        char toRod = clickedButtonId.charAt(3);
       // if(Play.playerGame.isGameOver() == false) {
            if(this.playerGame.isMoveValid(fromRod, toRod)) {
                this.playerGame.runAnimation(fromRod, toRod);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Move");
                alert.setHeaderText(null);
                alert.setContentText("This move is not allowed.");
                alert.showAndWait();
            }
    //    }
//        else {
//
//            Play.playerGame.endTime = System.currentTimeMillis();
//            Play.playerGame.duration = Play.playerGame.endTime - Play.playerGame.startTime;
//            Play.playerGame.score = (int) (100 / (Play.playerGame.duration / 1000));
//            Time time = new Time(Play.playerGame.duration);
//            DatabaseConnection mySQLConnection = new MySQLConnection();
//            mySQLConnection.connect();
//            String query = "INSERT INTO statistics (user_id, disks, points, time) VALUES (?, ?, ?, time)";
//            String[] variables = new String[4];
//            variables[0] = Integer.toString(User.user_id);
//            variables[1] = Byte.toString(Play.playerGame.getNumberOfDisks());
//            variables[2] = Integer.toString(Play.playerGame.score);
//            variables[3] = time.toString();
//            ((MySQLConnection) mySQLConnection).executeUpdateWithVariables(query, variables);
//            Stage stage = (Stage) clickedButton.getScene().getWindow();
//            Parent root = FXMLLoader.load(Tutorial.class.getResource("View/Solved.fxml"));
//            Scene solvedScene = new Scene(root);
//            stage.setScene(solvedScene);
//            stage.show();
//        }
    }
}
