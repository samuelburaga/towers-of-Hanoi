package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Model.DatabaseConnection;
import com.example.towersofhanoi.Model.MongoDBConnection;
import com.example.towersofhanoi.Model.MySQLConnection;
import com.example.towersofhanoi.Model.User;
import com.example.towersofhanoi.View.MenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The SettingsController class is responsible for controlling the user's account information.
 */

public class SettingsController {
    @FXML
    private Button changeFirstNameButton, changeLastNameButton, changeUsernameButton, changePasswordButton, changeProfilePictureButton;
    @FXML
    private Button deleteAccountButton;
    @FXML
    private Button previousClickedButton;
    @FXML
    private Button backButton, quitButton;
    private String defaultColor = "-fx-background-color: #333E41;";
    private String clickedColor = "-fx-background-color: #0FB4BB;";
    private DatabaseConnection mySQLConnection, mongoDBConnection;

    /**
     * Constructs a new SettingsController object.
     * Initializes the database model objects.
     */
    public SettingsController() {
        mySQLConnection = new MySQLConnection();
        mongoDBConnection = new MongoDBConnection();
    }

    /**
     * Handles the action when the change first name button is clicked.
     * Changes the color of the button and updates the previously clicked button.
     *
     * @param e the action event
     */
    public void changeFirstNameButtonOnAction(ActionEvent e) {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor);
        }
        changeFirstNameButton.setStyle(clickedColor);
        previousClickedButton = changeFirstNameButton;
    }

    /**
     * Handles the action when the change last name button is clicked.
     * Changes the color of the button and updates the previously clicked button.
     *
     * @param e the action event
     */
    public void changeLastNameButtonOnAction(ActionEvent e) {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor);
        }
        changeLastNameButton.setStyle(clickedColor);
        previousClickedButton = changeLastNameButton;
    }

    /**
     * Handles the action when the change username button is clicked.
     * Changes the color of the button, opens a dialog box to input the new username,
     * updates the username in the database, and updates the user's username.
     *
     * @param e the action event
     */
    public void changeUsernameButtonOnAction(ActionEvent e) {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor);
        }
        changeUsernameButton.setStyle(clickedColor);
        previousClickedButton = changeUsernameButton;
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Change Username");
        TextField usernameField = new TextField();
        Button changeButton = new Button("Change Username");
        changeButton.setId("changeButton");
        Label label = new Label("Input your new username:");
        VBox content = new VBox(label, usernameField, changeButton);
        content.setSpacing(10);
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        EventHandler<ActionEvent> changeButtonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = usernameField.getText();
                mySQLConnection.connect();
                mySQLConnection.updateUsername(User.username, usernameField.getText());
                User.username = usernameField.getText();
            }
        };
        changeButton.setOnAction(changeButtonHandler);

        dialog.showAndWait();
    }

    /**
     * Handles the action when the change password button is clicked.
     * Changes the color of the button and updates the previously clicked button.
     *
     * @param e the action event
     */
    public void changePasswordButtonOnAction(ActionEvent e) {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor);
        }
        changePasswordButton.setStyle(clickedColor);
        previousClickedButton = changePasswordButton;
    }

    /**
     * Handles the action when the change profile picture button is clicked.
     * Changes the color of the button and updates the previously clicked button.
     *
     * @param e the action event
     */
    public void changeProfilePictureButtonnOnAction(ActionEvent e) {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor);
        }
        changeProfilePictureButton.setStyle(clickedColor);
        previousClickedButton = changeProfilePictureButton;
    }

    /**
     * Handles the action when the delete account button is clicked.
     * Displays a confirmation alert and deletes the account if the user confirms.
     * Closes the current window after deletion.
     *
     * @param e the action event
     */
    public void deleteAccountButtonOnAction(ActionEvent e) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Delete Account");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete your account?");
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());
        confirmationAlert.getButtonTypes().setAll(yesButton, cancelButton);
        confirmationAlert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == yesButton) {
                mySQLConnection.connect();
                mySQLConnection.deleteAccount(User.username);
                Node node = (Node) e.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.hide();
            }
        });
    }
    /**
     * Handles the action event when the back button is clicked.
     * Hides the current stage and goes back to the menu view.
     *
     * @param e The ActionEvent object representing the back button click event.
     * @throws IOException If an I/O error occurs while loading the play view.
     */
    public void backButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
        MenuView menu = new MenuView();
        menu.start(new Stage());
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