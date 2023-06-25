package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.*;
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
    private String defaultColor = "-fx-background-color: #333E41;"; // Set the default color
    private String clickedColor = "-fx-background-color: #0FB4BB;"; // Set the desired color
    public void changeFirstNameButtonOnAction(ActionEvent e) {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor); // Revert the color of the previously clicked label
        }
        changeFirstNameButton.setStyle(clickedColor); // Set the color for the newly clicked label
        previousClickedButton = changeFirstNameButton; // Update the previously clicked label
    }
    public void changeLastNameButtonOnAction(ActionEvent e) {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor); // Revert the color of the previously clicked label
        }
        changeLastNameButton.setStyle(clickedColor); // Set the color for the newly clicked label
        previousClickedButton = changeLastNameButton; // Update the previously clicked label
    }
    public void changeUsernameButtonOnAction(ActionEvent e) {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor); // Revert the color of the previously clicked label
        }
        changeUsernameButton.setStyle(clickedColor); // Set the color for the newly clicked label
        previousClickedButton = changeUsernameButton; // Update the previously clicked label
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
                DatabaseConnection mySQLConnection = new MySQLConnection();
                mySQLConnection.connect();
                mySQLConnection.updateUsername(User.username, usernameField.getText());
                User.username = usernameField.getText();
                // dialog.close();
            }
        };
        changeButton.setOnAction(changeButtonHandler);
        // Show the dialog box and wait for a response
        dialog.showAndWait();
    }
    public void changePasswordButtonOnAction(ActionEvent e) {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor); // Revert the color of the previously clicked label
        }
        changePasswordButton.setStyle(clickedColor); // Set the color for the newly clicked label
        previousClickedButton = changePasswordButton; // Update the previously clicked label
    }
    public void changeProfilePictureButtonnOnAction(ActionEvent e) {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor); // Revert the color of the previously clicked label
        }
        changeProfilePictureButton.setStyle(clickedColor); // Set the color for the newly clicked label
        previousClickedButton = changeProfilePictureButton; // Update the previously clicked label
    }
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
                DatabaseConnection mySQLConnection = new MySQLConnection();
                mySQLConnection.connect();
                mySQLConnection.deleteAccount(User.username);
                Node node = (Node) e.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.hide();
            }
        });
    }
}