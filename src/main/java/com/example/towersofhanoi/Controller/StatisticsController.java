package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.DatabaseConnection;
import com.example.towersofhanoi.MySQLConnection;
import com.example.towersofhanoi.StatisticsData;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        DatabaseConnection mySQLConnection = new MySQLConnection();
        mySQLConnection.connect();
        String query = "SELECT s.points, s.disks, s.time, u.username FROM statistics s " +
                "JOIN users u ON s.users_user_id = u.user_id " +
                "ORDER BY s.points DESC, s.time ASC, s.disks DESC, u.user_id ASC LIMIT 10";
        ResultSet resultSet = ((MySQLConnection) mySQLConnection).executeQuery(query);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        disksColumn.setCellValueFactory(new PropertyValueFactory<>("disks"));
        statisticsTable.getItems().clear();

        while (resultSet.next()) {
            int points = resultSet.getInt("points");
            int disks = resultSet.getInt("disks");
            Time time = resultSet.getTime("time");
            String username = resultSet.getString("username");
            StatisticsData data = new StatisticsData(username, points, time, disks);
            statisticsTable.getItems().add(data);
        }
    }

}
