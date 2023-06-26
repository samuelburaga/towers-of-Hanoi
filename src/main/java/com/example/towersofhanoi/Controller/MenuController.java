package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Model.TutorialThread;
import com.example.towersofhanoi.Model.User;
import com.example.towersofhanoi.View.OptionsView;
import com.example.towersofhanoi.View.PlayView;
import com.example.towersofhanoi.View.SettingsView;
import com.example.towersofhanoi.View.StatisticsView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class MenuController {
    @FXML
    private Button playButton, tutorialButton, optionsButton, statisticsButton, settingsButton, quitButton;
    @FXML
    private Label usernameLabel;
    public void setUsername() {
        usernameLabel.setText(User.username); // show the username
    }
    public void playButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
        PlayView play = new PlayView();
        play.start(new Stage());
    } // play the game
    public void tutorialButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        Thread tutorialThread = new Thread(new TutorialThread(thisStage));
        tutorialThread.start();
    } // see the automatic game
    public void optionsButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
        Options options = new Options();
        options.start(new Stage());
    } // change game settings
    public void statisticsButtonOnAction(ActionEvent e) throws IOException, SQLException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
        StatisticsView statistics = new StatisticsView();
        statistics.start(new Stage());
    } // show some statistics
    public void settingsButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
        SettingsView settings = new SettingsView();
        settings.start(new Stage());
    } // change account information
    public void quitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    } // quit
}
