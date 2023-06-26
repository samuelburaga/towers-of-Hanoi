package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Model.DatabaseConnection;
import com.example.towersofhanoi.Model.MongoDBConnection;
import com.example.towersofhanoi.Model.MySQLConnection;
import com.example.towersofhanoi.Model.StatisticsData;
import com.example.towersofhanoi.View.MenuView;
import com.example.towersofhanoi.View.SettingsView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;

/**
 * The StatisticsController class handles the functionality of the statistics view in the Towers of Hanoi application.
 * It is responsible for displaying the statistics data in a table view.
 */
public class StatisticsController {
    @FXML
    private TableView<StatisticsData> statisticsTable;

    @FXML
    private TableColumn<StatisticsData, String> usernameColumn;

    @FXML
    private TableColumn<StatisticsData, Integer> pointsColumn, disksColumn;

    @FXML
    private TableColumn<StatisticsData, Time> timeColumn;
    private DatabaseConnection mySQLConnection, mongoDBConnection;

    @FXML private Button backButton, quitButton;

    /**
     * Constructs a new StatisticsController object.
     * Initializes the database connection objects for MySQL and MongoDB.
     */
    public StatisticsController() {
        this.mySQLConnection = new MySQLConnection();
        this.mongoDBConnection = new MongoDBConnection();
    }

    /**
     * Displays the statistics data in the table view.
     * Sets up the table columns and retrieves the statistics data from the database.
     *
     * @throws SQLException If an SQL error occurs while retrieving the statistics data.
     */
    public void showStatistics() throws SQLException {
        // Set table column names
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        disksColumn.setCellValueFactory(new PropertyValueFactory<>("disks"));
        statisticsTable.getItems().clear();
        this.mySQLConnection.connect(); // Connect to the database
        this.mySQLConnection.extractStatistics(statisticsTable); // Extract the statistics
    }

    /**
     * Handles the action when the back button is clicked.
     * Changes the color of the clicked button and navigates back to the menu view.
     *
     * @param e the action event
     * @throws IOException if an error occurs during the opening of the menu view
     */
    public void backButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
        MenuView menu = new MenuView();
        menu.start(new Stage());
    }
    /**
     * Handles the action when the quit button is clicked.
     * Closes the current window.
     *
     * @param e the action event
     */
    public void quitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
}