package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Model.PlayerGame;
import com.example.towersofhanoi.View.MenuView;
import com.example.towersofhanoi.View.PlayView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The UserSolvedController class handles the functionality of the user solved view in the Towers of Hanoi application.
 * It displays the congratulations message, player's game statistics, and provides options to go back to the play view or quit the game.
 */

public class UserSolvedController {

    @FXML
    private Label congratulationsLabel, statisticsLabel;
    @FXML
    private Label disksLabel, movesLabel, goodMovesLabel, badMovesLabel, timeLabel, pointsLabel;
    @FXML
    private Label userDisksLabel, userMovesLabel, userGoodMovesLabel, userBadMovesLabel, userTimeLabel, userPointsLabel;
    @FXML
    private Button backButton, quitButton;

    private PlayerGame statistics;

    /**
     * Constructs a new UserSolvedController object.
     * Initializes the PlayerGame statistics object.
     */
    public UserSolvedController() {
        statistics = new PlayerGame();
    }

    /**
     * Sets the player game statistics in the controller.
     * Updates the UI labels with the provided playerGame statistics.
     *
     * @param playerGame The PlayerGame object containing the player's statistics.
     */
    public void setStatistics(final PlayerGame playerGame) {
        this.statistics.numberOfDisks = playerGame.numberOfDisks;
        this.statistics.numberOfMoves = playerGame.numberOfMoves;
        this.statistics.numberOfGoodMoves = playerGame.numberOfGoodMoves;
        this.statistics.numberOfBadMoves = playerGame.numberOfBadMoves;
        this.statistics.time = playerGame.time;
        this.statistics.points = playerGame.points;
        updateStatistics();
    }

    /**
     * Updates the UI labels with the current player game statistics.
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
     * Handles the action event when the back button is clicked.
     * Hides the current stage and goes back to the play view.
     *
     * @param e The ActionEvent object representing the back button click event.
     * @throws IOException If an I/O error occurs while loading the play view.
     */
    public void backButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();

        PlayView playView = new PlayView();
        playView.start(new Stage());
    }

    /**
     * Handles the action event when the quit button is clicked.
     * Closes the current stage and quits the game.
     *
     * @param e The ActionEvent object representing the quit button click event.
     */
    public void quitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
}