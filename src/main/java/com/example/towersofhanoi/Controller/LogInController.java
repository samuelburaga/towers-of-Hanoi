package com.example.towersofhanoi.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController {
    @FXML
    private Label logInLabel;
    @FXML
    private TextField username, password;
    @FXML
    private Button logInButton, cancelButton;
    public void logInLabelOnAction(ActionEvent e){
        logInLabel.setText("ERROR! INPUT INFROMATION!");
    }
    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
