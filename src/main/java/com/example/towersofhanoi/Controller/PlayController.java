package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Model.*;
import com.example.towersofhanoi.View.MenuView;
import com.example.towersofhanoi.View.PlayView;
import com.example.towersofhanoi.View.TutorialView;
import com.example.towersofhanoi.View.UserSolvedView;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.sql.Time;

/**
 * The PlayController class is responsible for controlling the gameplay screen.
 * It handles user interactions, manages the game logic, and updates the user interface accordingly.
 */
public class PlayController {
    @FXML
    private Pane rodA, rodB, rodC;
    @FXML
    private Button AToBButton, AToCButton, BToAButton, BToCButton, CToAButton, CToBButton;
    @FXML
    private Button quitButton;
    private String move;
    private PlayerGame playerGame;
    private DatabaseConnection mySQLConnection, mongoDBConnection;

    /**
     * Constructs a new PlayController instance.
     * Initializes the database connections and the player's game instance with the selected options.
     */
    public PlayController() {
        this.mySQLConnection = new MySQLConnection();
        this.mongoDBConnection = new MongoDBConnection();
        this.playerGame = new PlayerGame(OptionsController.numberOfDisks, OptionsController.moveAnimationSpeed);
    }

    /**
     * Draws the disks on the initial rods based on the player's game instance.
     */
    public void drawDisks() {
        // Code to draw the disks on the rods
    }

    /**
     * Connects the player's game instance to the user interface elements.
     */
    public void connectGameToUI() {
        // Code to connect the game instance to the UI elements
    }

    /**
     * Starts the game and initializes the game timer.
     */
    public void startGame() {
        // Code to start the game and initialize the game timer
    }

    /**
     * Handles the action when a move button is clicked.
     * Executes the move and updates the game state and UI accordingly.
     *
     * @param e the action event
     * @throws IOException if an error occurs
     */
    public void moveOptionOnAction(ActionEvent e) throws IOException {
        // Code to handle the move button action
    }

    /**
     * Inserts the game statistics into the database.
     */
    private void insertStatisticsInDatabase() {
        // Code to insert the game statistics into the database
    }

    /**
     * Switches to the solved scene after the game is completed.
     * Displays the user's statistics and transitions to the solved scene.
     */
    private void switchToSolvedScene() {
        // Code to switch to the solved scene and display the user's statistics
    }

    /**
     * Handles the action when the quit button is clicked.
     * Returns to the menu view.
     *
     * @param e the action event
     * @throws IOException if an error occurs during the opening of the menu view
     */
    public void quitButtonOnAction(ActionEvent e) throws IOException {
        // Code to handle the quit button action and return to the menu view
    }
}
