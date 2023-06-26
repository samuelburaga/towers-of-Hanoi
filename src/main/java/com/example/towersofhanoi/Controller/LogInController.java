package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.*;
import com.example.towersofhanoi.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LogInController {
    @FXML
    private Label logInMessage;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button logInButton, cancelButton;
    public void logInButtonOnAction(ActionEvent e) throws IOException, SQLException {
        if(usernameTextField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {
            DatabaseConnection mySQLConnection = new MySQLConnection();
            mySQLConnection.connect();
            if(mySQLConnection.checkIfUserExists(usernameTextField.getText(), passwordField.getText())) {
                ResultSet resultSet = ((MySQLConnection) mySQLConnection).getUserByUsername(usernameTextField.getText());
                User.updateData(resultSet);
                Node node = (Node) e.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.hide();
                Menu menu = new Menu();
                menu.start(new Stage());
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Your username or password is wrong!");
                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your username and password!");
            alert.showAndWait();
        }
    }
    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
