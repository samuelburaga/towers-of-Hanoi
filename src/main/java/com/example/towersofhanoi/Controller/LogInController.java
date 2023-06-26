package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Model.MongoDBConnection;
import com.example.towersofhanoi.View.LogInView;
import com.example.towersofhanoi.View.MenuView;
import com.example.towersofhanoi.Model.DatabaseConnection;
import com.example.towersofhanoi.Model.MySQLConnection;
import com.example.towersofhanoi.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

/**
 * The LogInController class is responsible for controlling the login screen.
 * It manages user authentication and navigation to the main menu or cancellation of the login process.
 */
public class LogInController {
    @FXML
    private Label logInMessage;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button logInButton, cancelButton;
    private DatabaseConnection mySQLConnection, mongoDBConnection;

    /**
     * Constructs a new LogInController.
     * Initializes the database model objects for MySQL and MongoDB connections.
     */
    public LogInController() {
        this.mySQLConnection = new MySQLConnection();
        this.mongoDBConnection = new MongoDBConnection();
    }

    /**
     * Handles the action when the login button is clicked.
     * Authenticates the user by connecting to the database and checking the username and password.
     * If the authentication is successful, opens the main menu.
     * Otherwise, displays an error message.
     *
     * @param e the action event
     * @throws IOException  if an error occurs during the opening of the menu view
     * @throws SQLException if an error occurs during the database operations
     */
    public void logInButtonOnAction(ActionEvent e) throws IOException, SQLException {
        if (usernameTextField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {
            this.mySQLConnection.connect();
            if (this.mySQLConnection.checkIfUserExists(usernameTextField.getText(), passwordField.getText())) {
                ResultSet resultSet = ((MySQLConnection) this.mySQLConnection).getUserByUsername(usernameTextField.getText());
                User.updateData(resultSet);
                Node node = (Node) e.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.hide();
                MenuView menu = new MenuView();
                menu.start(new Stage());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Your username or password is wrong!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your username and password!");
            alert.showAndWait();
        }
    }

    /**
     * Handles the action when the cancel button is clicked.
     * Closes the current window.
     *
     * @param e the action event
     */
    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}

