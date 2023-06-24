package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.*;
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
            Database mySQLConnection = new MySQLConnection();
            mySQLConnection.connect();
            if(mySQLConnection.checkIfUserExists(usernameTextField.getText(), passwordField.getText()) == false) {
                ((MySQLConnection) mySQLConnection).insertNewUser(firstNameTextField.getText(), lastNameTextField.getText(), usernameTextField.getText(), passwordField.getText());
                Users.first_name = firstNameTextField.getText();
                Users.last_name = lastNameTextField.getText();
                Users.username = usernameTextField.getText();

//                query = "SELECT * FROM " + this.tables[0] + " WHERE username = ?";
//                variables = new String[1];
//                variables[0] = usernameTextField.getText();
//                ResultSet resultSet = this.executeQueryWithVariables(query, variables);
//                // databaseConnection.printQuery(resultSet);
//                try {
//                    if (resultSet.next()) {
//                        Users.user_id = resultSet.getInt("user_id");
//                        Users.first_name = resultSet.getString("first_name");
//                        Users.last_name = resultSet.getString("last_name");
//                        Users.username = resultSet.getString("username");
//                    }
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                    // Handle the exception appropriately (e.g., show an error message)
//                }
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
}
