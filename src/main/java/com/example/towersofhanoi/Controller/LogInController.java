package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.DatabaseConnection;
import com.example.towersofhanoi.HelloApplication;
import com.example.towersofhanoi.Menu;
import com.example.towersofhanoi.Users;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    public void logInButtonOnAction(ActionEvent e) throws IOException, SQLException {
        if(usernameTextField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {
            if(isLogInValid()) {
                DatabaseConnection databaseConnection = new DatabaseConnection();
                databaseConnection.connect();
                databaseConnection.Statement();
                String query = "SELECT * FROM " + databaseConnection.tables[0] + " WHERE username = ?";
                String[] variables = new String[1];
                variables[0] = usernameTextField.getText();
                ResultSet resultSet = databaseConnection.executeQueryWithVariables(query, variables);
                // databaseConnection.printQuery(resultSet);
                try {
                    if (resultSet.next()) {
                        Users.user_id = resultSet.getInt("user_id");
                        Users.first_name = resultSet.getString("first_name");
                        Users.last_name = resultSet.getString("last_name");
                        Users.username = resultSet.getString("username");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Handle the exception appropriately (e.g., show an error message)
                }
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
    public boolean isLogInValid() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        databaseConnection.Statement();
        String query = "SELECT EXISTS (SELECT * FROM " + databaseConnection.tables[0] + " WHERE username = ? AND password = ?)";
        String[] variables = new String[2];
        variables[0] = usernameTextField.getText();
        variables[1] = passwordField.getText();
        ResultSet check = databaseConnection.executeQueryWithVariables(query, variables);
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
