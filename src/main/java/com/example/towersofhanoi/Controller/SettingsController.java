package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Model.DatabaseConnection;
import com.example.towersofhanoi.Model.MongoDBConnection;
import com.example.towersofhanoi.Model.MySQLConnection;
import com.example.towersofhanoi.Model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsController {
    @FXML
    private Button changeFirstNameButton, changeLastNameButton, changeUsernameButton, changePasswordButton, changeProfilePictureButton;
    @FXML
    private Button deleteAccountButton;
    @FXML
    private Button previousClickedButton;
    private String defaultColor = "-fx-background-color: #333E41;";
    private String clickedColor = "-fx-background-color: #0FB4BB;";
    private DatabaseConnection mySQLConnection, mongoDBConnection;
    public SettingsController() {
        // create database model objects
        mySQLConnection = new MySQLConnection();
        mongoDBConnection = new MongoDBConnection();
    }
    public void changeFirstNameButtonOnAction(ActionEvent e) {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor); // Revert the color of the previously clicked button
        }
        changeFirstNameButton.setStyle(clickedColor); // Set the color for the newly clicked button
        previousClickedButton = changeFirstNameButton; // Update the previously clicked button
    } // change the first name
    public void changeLastNameButtonOnAction(ActionEvent e) {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor); // Revert the color of the previously clicked button
        }
        changeLastNameButton.setStyle(clickedColor); // Set the color for the newly clicked button
        previousClickedButton = changeLastNameButton; // Update the previously clicked button
    } // change the last name
    public void changeUsernameButtonOnAction(ActionEvent e) {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor); // Revert the color of the previously clicked button
        }
        changeUsernameButton.setStyle(clickedColor); // Set the color for the newly clicked button
        previousClickedButton = changeUsernameButton; // Update the previously clicked button
        // Create the dialog box
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Change Username");
        // Set the dialog content
        TextField usernameField = new TextField();
        Button changeButton = new Button("Change Username");
        changeButton.setId("changeButton");
        Label label = new Label("Input your new username:");
        VBox content = new VBox(label, usernameField, changeButton);
        content.setSpacing(10); // Set spacing between elements
        dialog.getDialogPane().setContent(content);
        // Add a button to the dialog box
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        // Handle the event when the change button is clicked
        EventHandler<ActionEvent> changeButtonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = usernameField.getText();
                mySQLConnection.connect(); // connect to the database
                mySQLConnection.updateUsername(User.username, usernameField.getText());
                User.username = usernameField.getText();
                // dialog.close();
            }
        };
        changeButton.setOnAction(changeButtonHandler);
        // Show the dialog box and wait for a response
        dialog.showAndWait();
    } // change the username
    public void changePasswordButtonOnAction(ActionEvent e) {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor); // Revert the color of the previously clicked button
        }
        changePasswordButton.setStyle(clickedColor); // Set the color for the newly clicked button
        previousClickedButton = changePasswordButton; // Update the previously clicked button
    } // change the password
    public void changeProfilePictureButtonnOnAction(ActionEvent e) {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor); // Revert the color of the previously clicked button
        }
        changeProfilePictureButton.setStyle(clickedColor); // Set the color for the newly clicked button
        previousClickedButton = changeProfilePictureButton; // Update the previously clicked button
    } // change the profile picture
    public void  deleteAccountButtonOnAction(ActionEvent e) {
        // Create the confirmation alert
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Delete Account");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete your account?");
        // Customize the alert buttons
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());
        confirmationAlert.getButtonTypes().setAll(yesButton, cancelButton);
        // Show the confirmation alert and wait for a response
        confirmationAlert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == yesButton) {
                // Perform account deletion
                mySQLConnection.connect(); // connect to the database
                mySQLConnection.deleteAccount(User.username);
                Node node = (Node) e.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.hide();
            }
        });
    } // delete the account
}