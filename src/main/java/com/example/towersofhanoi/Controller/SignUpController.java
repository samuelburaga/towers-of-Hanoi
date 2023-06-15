package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.DatabaseConnection;
import com.example.towersofhanoi.Menu;
import com.example.towersofhanoi.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class SignUpController {
    @FXML
    private TextField firstNameTextField, lastNameTextField, usernameTextField, passwordField;
    @FXML
    private Button signUpButton;
    public void signUpButtonOnAction(ActionEvent e) throws IOException {
        if(firstNameTextField.getText().isBlank() == false && lastNameTextField.getText().isBlank() == false && usernameTextField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {
            if(isSignUpValid()) {
                DatabaseConnection databaseConnection = new DatabaseConnection();
                databaseConnection.connect();
                databaseConnection.Statement();
                String query = "INSERT INTO users (first_name, last_name, username, password, created_at) VALUES (?, ?, ?, ?, NOW())";
                String[] variables = new String[4];

                Users.first_name = firstNameTextField.getText();
                Users.last_name = lastNameTextField.getText();
                Users.username = usernameTextField.getText();

                variables[0] = firstNameTextField.getText();
                variables[1] = lastNameTextField.getText();
                variables[2] = usernameTextField.getText();
                variables[3] = passwordField.getText();
                databaseConnection.executeUpdateWithVariables(query, variables);


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
                alert.setContentText("This user already exists!");
                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your data!");
            alert.showAndWait();
        }
    }
    public boolean isSignUpValid() {
          DatabaseConnection databaseConnection = new DatabaseConnection();
          databaseConnection.connect();
          databaseConnection.Statement();
          String query = "SELECT EXISTS (SELECT * FROM " + databaseConnection.tables[0] + " WHERE username = ?)";
          String[] variables = new String[1];
          variables[0] = usernameTextField.getText();
          ResultSet check = databaseConnection.executeQueryWithVariables(query, variables);
          try {
              if (check.next())
              {
                  int exists = check.getInt(1);
                  boolean existsResult = (exists == 0);
                  return existsResult;
              }
          }
          catch (SQLException e) {
              e.printStackTrace();
          }
         return false;
    }
}