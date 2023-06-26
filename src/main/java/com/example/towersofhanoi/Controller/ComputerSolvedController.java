package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Model.AutomaticGame;
import com.example.towersofhanoi.View.TutorialView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The ComputerSolvedController class is responsible for displaying the computer's solution statistics.
 * It manages the display of statistics and handles button actions.
 */
public class ComputerSolvedController {
    @FXML
    private Label congratulationsLabel, statisticsLabel;
    @FXML
    private Label disksLabel, movesLabel, goodMovesLabel, badMovesLabel, timeLabel, pointsLabel;
    @FXML
    private Label userDisksLabel, userMovesLabel, userGoodMovesLabel, userBadMovesLabel, userTimeLabel, userPointsLabel;
    @FXML
    private Button backButton, quitButton;
    private AutomaticGame statistics;

    /**
     * Constructs a new ComputerSolvedController.
     * Initializes the statistics object.
     */
    public ComputerSolvedController() {
        statistics = new AutomaticGame();
    }

    /**
     * Sets the statistics for the computer-solved game.
     * Updates the displayed statistics.
     *
     * @param automaticGame the AutomaticGame object containing the statistics
     */
    public void setStatistics(final AutomaticGame automaticGame) {
        this.statistics.numberOfDisks = automaticGame.numberOfDisks;
        this.statistics.numberOfMoves = automaticGame.numberOfMoves;
        this.statistics.numberOfGoodMoves = automaticGame.numberOfGoodMoves;
        this.statistics.numberOfBadMoves = automaticGame.numberOfBadMoves;
        this.statistics.time = automaticGame.time;
        this.statistics.points = automaticGame.points;
        updateStatistics();
    }

    /**
     * Updates the displayed statistics with the current values from the statistics object.
     */
    private void updateStatistics() {
        userDisksLabel.setText(Integer.toString(this.statistics.numberOfDisks));
        userMovesLabel.setText(Integer.toString(this.statistics.numberOfMoves));
        userGoodMovesLabel.setText(Integer.toString(this.statistics.numberOfGoodMoves));
        userBadMovesLabel.setText(Integer.toString(this.statistics.numberOfBadMoves));
        userTimeLabel.setText(String.valueOf(this.statistics.time));
        userPointsLabel.setText(Integer.toString(this.statistics.points));
    }

    /**
     * Handles the action when the back button is clicked.
     * Closes the current window and opens the tutorial view.
     *
     * @param e the action event
     * @throws IOException if an error occurs during the opening of the tutorial view
     */
    public void backButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
        TutorialView tutorialView = new TutorialView();
        tutorialView.start(new Stage());
    }

    /**
     * Handles the action when the quit button is clicked.
     * Closes the current window.
     *
     * @param e the action event
     */
    public void quitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
}