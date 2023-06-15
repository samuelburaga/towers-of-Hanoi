package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.DatabaseConnection;
import com.example.towersofhanoi.StatisticsData;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Time;

public class StatisticsController {
    @FXML
    private TableView<StatisticsData> statisticsTable;
    @FXML
    private TableColumn<StatisticsData, String> usernameColumn;
    @FXML
    private TableColumn<StatisticsData, Integer> pointsColumn, disksColumn;
    @FXML
    private TableColumn<StatisticsData, Time> timeColumn;
    public void showStatistics() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        databaseConnection.Statement();
        String query = "SELECT * FROM " + databaseConnection.tables[1] + " ORDER BY points DESC LIMIT 10";
        ResultSet resultSet = databaseConnection.executeQuery(query);
        // databaseConnection.printQuery(resultSet);


        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("Username")); // Assuming "name" is the column name in the database
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("Points")); // Assuming "points" is the column name in the database
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("Time")); // Assuming "points" is the column name in the database
        disksColumn.setCellValueFactory(new PropertyValueFactory<>("Disks")); // Assuming "points" is the column name in the database

        // Clear existing rows
        statisticsTable.getItems().clear();
        // Populate the table with query results
        while (resultSet.next()) {
            // String username = resultSet.getString("name");
            int points = resultSet.getInt("points");
            int disks = resultSet.getInt("disks");
            Time time = resultSet.getTime("time");
            System.out.println(time);
            StatisticsData data = new StatisticsData("username", points, time, disks);
            statisticsTable.getItems().add(data);
        }
    }

}
