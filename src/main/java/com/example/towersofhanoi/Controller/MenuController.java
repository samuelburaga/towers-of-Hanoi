package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    private Button playButton, tutorialButton, optionsButton, statisticsButton, settingsButton, quitButton;
    public void playButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        Play play = new Play();
        play.start(new Stage());
        // thisStage.hide();
    }
    public void tutorialButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        Thread tutorialThread = new Thread(new TutorialThread(thisStage));
        tutorialThread.start();
    }
    public void optionsButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        Options options = new Options();
        options.start(new Stage());
        // thisStage.hide();
    }
    public void statisticsButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        Statistics statistics = new Statistics();
        statistics.start(new Stage());
        // thisStage.hide();
    }
    public void settingsButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        Settings settings = new Settings();
        settings.start(new Stage());
       // thisStage.hide();
    }
    public void quitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

}
