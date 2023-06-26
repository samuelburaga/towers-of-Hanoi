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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The MenuController class is responsible for controlling the main menu screen.
 * It handles user interactions and navigation to different views such as the game, tutorial, options, statistics, and settings.
 */
public class MenuController {
    @FXML
    private Button playButton, tutorialButton, optionsButton, statisticsButton, settingsButton, quitButton;
    @FXML
    private Label usernameLabel;

    /**
     * Constructs a new MenuController.
     */
    public MenuController() {
    }

    /**
     * Sets the username label to display the current user's username.
     */
    public void setUsername() {
        usernameLabel.setText(User.username);
    }

    /**
     * Handles the action when the play button is clicked.
     * Opens the play view and makes it possible to be opened at the same time with the tutorial window.
     *
     * @param e the action event
     * @throws IOException if an error occurs during the opening of the play view
     */
    public void playButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        PlayView playView = new PlayView();
        Stage playerGameStage = new Stage();
        playerGameStage.initModality(Modality.NONE); // or Modality.WINDOW_MODAL
        playerGameStage.initOwner(thisStage);
        try {
            playView.start(playerGameStage);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /**
     * Handles the action when the tutorial button is clicked.
     * Hides the current window and starts a tutorial thread to display the tutorial view.
     *
     * @param e the action event
     * @throws IOException if an error occurs during the opening of the tutorial view
     */
    public void tutorialButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        Thread tutorialThread = new Thread(new TutorialThread(thisStage));
        tutorialThread.start();
    }
    /**
     * Handles the action when the options button is clicked.
     * Hides the current window and opens the options view.
     *
     * @param e the action event
     * @throws IOException if an error occurs during the opening of the options view
     */
    public void optionsButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        OptionsView optionsView = new OptionsView();
        Stage optionsStage = new Stage();
        optionsStage.initModality(Modality.NONE); // or Modality.WINDOW_MODAL
        optionsStage.initOwner(thisStage);
        try {
            optionsView.start(thisStage);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /**
     * Handles the action when the statistics button is clicked.
     * Hides the current window and opens the statistics view.
     *
     * @param e the action event
     * @throws IOException  if an error occurs during the opening of the statistics view
     * @throws SQLException if an error occurs during the database operations
     */
    public void statisticsButtonOnAction(ActionEvent e) throws IOException, SQLException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
        StatisticsView statistics = new StatisticsView();
        statistics.start(new Stage());
    }

    /**
     * Handles the action when the settings button is clicked.
     * Hides the current window and opens the settings view.
     *
     * @param e the action event
     * @throws IOException if an error occurs during the opening of the settings view
     */
    public void settingsButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
        SettingsView settings = new SettingsView();
        settings.start(new Stage());
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

