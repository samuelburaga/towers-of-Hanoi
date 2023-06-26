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

public class SignUpController {
    @FXML
    private TextField firstNameTextField, lastNameTextField, usernameTextField, passwordField;
    @FXML
    private Button signUpButton;
    private DatabaseConnection mySQLConnection, mongoDBConnection;
    public SignUpController() {
        // create database model objects
        this.mySQLConnection = new MySQLConnection();
        this.mongoDBConnection = new MongoDBConnection();
    }
    public void signUpButtonOnAction(ActionEvent e) throws IOException {
        if(firstNameTextField.getText().isBlank() == false && lastNameTextField.getText().isBlank() == false && usernameTextField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {
            this.mySQLConnection.connect();
            // check if this username or password already exists
            if(this.mySQLConnection.checkIfUserExists(usernameTextField.getText(), passwordField.getText()) == false) {
                ((MySQLConnection) this.mySQLConnection).insertNewUser(firstNameTextField.getText(), lastNameTextField.getText(), usernameTextField.getText(), passwordField.getText());
                // update user information
                User.updateData(((MySQLConnection) this.mySQLConnection).getLatestUserId(), firstNameTextField.getText(), lastNameTextField.getText(), usernameTextField.getText());
                Node node = (Node) e.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.hide();
                MenuView menu = new MenuView();
                menu.start(new Stage()); // go to menu
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("This user already exists!");
                alert.showAndWait();
            } // Another user has this username or password
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your data!");
            alert.showAndWait();
        } // The user didn't type any data
    } // sign up
}