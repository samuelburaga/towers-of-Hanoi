package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.DatabaseConnection;
import com.example.towersofhanoi.Settings;
import com.example.towersofhanoi.Users;
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
                DatabaseConnection databaseConnection = new DatabaseConnection();
                databaseConnection.connect();
                databaseConnection.Statement();
                String query = "UPDATE " + databaseConnection.tables[0] + " SET username = ? WHERE username = ?";
                String[] variables = new String[2];
                variables[0] = username;
                variables[1] = Users.username;
                databaseConnection.executeUpdateWithVariables(query, variables);
                Users.username = username;
                System.out.println("New username: " + username);
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

}
