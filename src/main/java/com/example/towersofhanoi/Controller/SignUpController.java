package com.example.towersofhanoi.Controller;
import com.example.towersofhanoi.Model.DatabaseConnection;
import com.example.towersofhanoi.Model.MongoDBConnection;
import com.example.towersofhanoi.Model.MySQLConnection;
import com.example.towersofhanoi.Model.User;
import com.example.towersofhanoi.View.MenuView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The SignUpController class handles the functionality of the sign-up view in the Towers of Hanoi application.
 * It is responsible for connecting to the database, validating user input, and creating a new user account.
 */
public class SignUpController {
    @FXML
    private TextField firstNameTextField, lastNameTextField, usernameTextField, passwordField;

    @FXML
    private Button signUpButton;

    private DatabaseConnection mySQLConnection, mongoDBConnection;

    /**
     * Constructs a new SignUpController object.
     * Initializes the database connection objects for MySQL and MongoDB.
     */
    public SignUpController() {
        this.mySQLConnection = new MySQLConnection();
        this.mongoDBConnection = new MongoDBConnection();
    }

    /**
     * Handles the action event when the sign-up button is clicked.
     * Validates the user input and creates a new user account if the input is valid.
     * Shows appropriate error messages for invalid input or existing users.
     *
     * @param e The ActionEvent object representing the sign-up button click event.
     * @throws IOException If an I/O error occurs while navigating to the menu view.
     */
    public void signUpButtonOnAction(ActionEvent e) throws IOException {
        if (!firstNameTextField.getText().isBlank() && !lastNameTextField.getText().isBlank() &&
                !usernameTextField.getText().isBlank() && !passwordField.getText().isBlank()) {
            this.mySQLConnection.connect();

            if (!this.mySQLConnection.checkIfUserExists(usernameTextField.getText(), passwordField.getText())) {
                ((MySQLConnection) this.mySQLConnection).insertNewUser(
                        firstNameTextField.getText(), lastNameTextField.getText(),
                        usernameTextField.getText(), passwordField.getText());

                User.updateData(((MySQLConnection) this.mySQLConnection).getLatestUserId(),
                        firstNameTextField.getText(), lastNameTextField.getText(),
                        usernameTextField.getText());

                Node node = (Node) e.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.hide();

                MenuView menu = new MenuView();
                menu.start(new Stage());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("This user already exists!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your data!");
            alert.showAndWait();
        }
    }
}