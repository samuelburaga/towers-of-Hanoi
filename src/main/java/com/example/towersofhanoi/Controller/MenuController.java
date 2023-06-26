package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.*;
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
        Play play = new Play();
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
        Statistics statistics = new Statistics();
        statistics.start(new Stage());
    } // show some statistics
    public void settingsButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
        Settings settings = new Settings();
        settings.start(new Stage());
    } // change account information
    public void quitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    } // quit
}
