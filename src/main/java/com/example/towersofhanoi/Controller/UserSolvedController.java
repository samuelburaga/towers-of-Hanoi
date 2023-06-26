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
    public UserSolvedController() {
        statistics = new PlayerGame();
    }
    public void setStatistics(final PlayerGame playerGame) {
        this.statistics.numberOfDisks = playerGame.numberOfDisks;
        this.statistics.numberOfMoves = playerGame.numberOfMoves;
        this.statistics.numberOfGoodMoves = playerGame.numberOfGoodMoves;
        this.statistics.numberOfBadMoves = playerGame.numberOfBadMoves;
        this.statistics.time = playerGame.time;
        this.statistics.points = playerGame.points;
        updateStatistics();
    }
    private void updateStatistics() {
        userDisksLabel.setText(Integer.toString(this.statistics.numberOfDisks));
        userMovesLabel.setText(Integer.toString(this.statistics.numberOfMoves));
        userGoodMovesLabel.setText(Integer.toString(this.statistics.numberOfGoodMoves));
        userBadMovesLabel.setText(Integer.toString(this.statistics.numberOfBadMoves));
        userTimeLabel.setText(String.valueOf(this.statistics.time));
        userPointsLabel.setText(Integer.toString(this.statistics.points));
    }
    public void backButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
        PlayView playView = new PlayView();
        playView.start(new Stage());
    } // go back to the menu
    public void quitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    } // quit
}
