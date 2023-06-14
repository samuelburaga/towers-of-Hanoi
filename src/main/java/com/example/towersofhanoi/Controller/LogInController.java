package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.DatabaseConnection;
import com.example.towersofhanoi.HelloApplication;
import com.example.towersofhanoi.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    public void logInButtonOnAction(ActionEvent e) throws IOException {
        if(usernameTextField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {
            if(validateLogIn()) {
                Node node = (Node) e.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.hide();
                Menu menu = new Menu();
                menu.start(new Stage());
            }
            else {
                logInMessage.setText("Your username or password is wrong!");
            }
        }
        else {
            logInMessage.setText("Please enter your username and password!");
        }
    }
    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public boolean validateLogIn() {
        DatabaseConnection dc = new DatabaseConnection();
        dc.connect();
        dc.Statement();
        String query = "SELECT EXISTS (SELECT * FROM " + dc.tables[0] + " WHERE username = ? AND password = ?)";
        String[] variables = new String[2];
        variables[0] = usernameTextField.getText();
        variables[1] = passwordField.getText();
        ResultSet check = dc.exectureQueryWithVariables(query, variables);
        try {
            if (check.next())
            {
                int exists = check.getInt(1);
                boolean existsResult = (exists == 1);
                return existsResult;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
