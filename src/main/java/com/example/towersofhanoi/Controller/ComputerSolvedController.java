package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Model.AutomaticGame;
import com.example.towersofhanoi.Model.PlayerGame;
import com.example.towersofhanoi.View.MenuView;
import com.example.towersofhanoi.View.PlayView;
import com.example.towersofhanoi.View.TutorialView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

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
    public ComputerSolvedController() {
        statistics = new AutomaticGame();
    }
    public void setStatistics(final AutomaticGame automaticGame) {
        this.statistics.numberOfDisks = automaticGame.numberOfDisks;
        this.statistics.numberOfMoves = automaticGame.numberOfMoves;
        this.statistics.numberOfGoodMoves = automaticGame.numberOfGoodMoves;
        this.statistics.numberOfBadMoves = automaticGame.numberOfBadMoves;
        this.statistics.time = automaticGame.time;
        this.statistics.points = automaticGame.points;
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
        TutorialView tutorialView = new TutorialView();
        tutorialView.start(new Stage());
    } // go back to the menu
    public void quitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    } // quit
}
