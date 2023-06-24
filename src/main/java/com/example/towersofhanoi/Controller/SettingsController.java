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
    public void changeFirstNameButtonOnAction(ActionEvent e) {

    }
    public void changeLastNameButtonOnAction(ActionEvent e) {

    }
    public void changeUsernameButtonOnAction(ActionEvent e) {
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
                mySQLConnection.updateUsername(Users.username, usernameField.getText());
                Users.username = usernameField.getText();
                // dialog.close();
            }
        };
        changeButton.setOnAction(changeButtonHandler);
        // Show the dialog box and wait for a response
        dialog.showAndWait();
    }
    public void changePasswordButtonOnAction(ActionEvent e) {

    }
    public void changeProfilePictureButtonnOnAction(ActionEvent e) {

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
                mySQLConnection.deleteAccount(Users.username);
                Node node = (Node) e.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.hide();
            }
        });
    }
}
