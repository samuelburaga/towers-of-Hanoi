package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Model.AutomaticGame;
import com.example.towersofhanoi.View.ComputerSolvedView;
import com.example.towersofhanoi.View.MenuView;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
import java.sql.Time;

/**
 * The TutorialController class handles the functionality of the automatic game in the Towers of Hanoi application.
 * It is responsible for drawing and animating the disks, solving the game automatically, and switching to the "computer solved" view.
 */

public class TutorialController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane rodA, rodB, rodC;

    @FXML
    private Button AToBButton, AToCButton, BToAButton, BToCButton, CToAButton, CToBButton;

    @FXML
    private Button solveButton;

    @FXML
    private Button quitButton;

    private AutomaticGame automaticGame;

    /**
     * Constructs a new TutorialController object.
     * Initializes the AutomaticGame object with the specified number of disks and move animation speed.
     */
    public TutorialController() {
        this.automaticGame = new AutomaticGame(OptionsController.numberOfDisks, OptionsController.moveAnimationSpeed);
    }

    /**
     * Handles the action event when the solve button is clicked.
     * Connects the game to the UI, starts the automatic game, and switches to the solved view when the game is solved.
     *
     * @param e The ActionEvent object representing the solve button click event.
     */
    public void solveButtonOnAction(ActionEvent e) {
        connectGameToUI();
        this.automaticGame.startTime = System.currentTimeMillis(); // Record the start time

        // Run the algorithm
        this.automaticGame.runAlgorithm(() -> {
            this.automaticGame.endTime = System.currentTimeMillis(); // Record the end time
            this.automaticGame.duration = (this.automaticGame.endTime - this.automaticGame.startTime) / 1000;
            this.automaticGame.gameOver = true;

            // Extract hours, minutes, and seconds from the duration
            int hours = (int) (automaticGame.duration / 3600);
            int minutes = (int) ((automaticGame.duration % 3600) / 60);
            int seconds = (int) (automaticGame.duration % 60);

            // Create a new `Time` object using the hours, minutes, and seconds
            this.automaticGame.time = new Time(hours, minutes, seconds);
            this.automaticGame.numberOfMoves = (int) (Math.pow(2, automaticGame.numberOfDisks) - 1);
            this.automaticGame.numberOfGoodMoves = automaticGame.numberOfMoves;
            this.automaticGame.numberOfBadMoves = 0;

            switchToSolvedScene();
        });
    }

    /**
     * Connects the game to the UI objects, such as rods and buttons.
     */
    public void connectGameToUI() {
        this.automaticGame.setRods(rodA, rodB, rodC);
        this.automaticGame.setButtons(AToBButton, AToCButton, BToAButton, BToCButton, CToAButton, CToBButton);
    }

    /**
     * Draws the disks on the screen.


     * Clears any existing disks and creates rectangles representing the disks on the appropriate rod.
     */
    public void drawDisks() {
        rodA.getChildren().clear(); // Clear any existing disks

        double diskWidth = 198.0, diskHeight = 60.0; // Initial dimensions
        double initialX = 0, initialY = rodA.getPrefHeight() - diskHeight; // Initial coordinates
        double arcWidth = 30.0, arcHeight = 30.0;

        // Draw the disks
        for (byte index = 0; index < this.automaticGame.getNumberOfDisks(); index++) {
            Rectangle disk = new Rectangle(diskWidth - (index * 12), diskHeight);
            disk.setLayoutX(initialX + (index * 6));
            disk.setLayoutY(initialY - (index * diskHeight));
            disk.setArcHeight(arcHeight);
            disk.setArcWidth(arcWidth);

            // Calculate the color based on the index
            double hue = 182.0;  // Base hue value
            double saturation = 0.92 - (index + 1) * (0.91 / this.automaticGame.getNumberOfDisks());  // Saturation value based on index
            double brightness = 0.73 + (index + 1) * (0.26 / this.automaticGame.getNumberOfDisks());  // Adjust brightness based on index
            Color diskColor = Color.hsb(hue, saturation, brightness);
            disk.setFill(diskColor);

            rodA.getChildren().add(disk);
        }
    }

    /**
     * Switches to the solved scene after a pause.
     * Loads the solved view FXML file, sets the statistics in the controller, and displays the scene.
     */
    private void switchToSolvedScene() {
        Stage stage = (Stage) rodA.getScene().getWindow();
        Parent root;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ComputerSolvedView.class.getResource("ComputerSolved.fxml"));
            root = fxmlLoader.load();
            ComputerSolvedController controller = fxmlLoader.getController();

            // Set the statistics in the controller
            controller.setStatistics(this.automaticGame);
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

    /**
     * Handles the action event when the quit button is clicked.
     * Hides the current stage and goes back to the menu view.
     *
     * @param e The ActionEvent object representing the quit button click event.
     * @throws IOException If an I/O error occurs while loading the menu view.
     */
    public void quitButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();

        MenuView menuView = new MenuView();
        menuView.start(new Stage());
    }
}