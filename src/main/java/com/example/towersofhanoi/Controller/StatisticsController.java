package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.DatabaseConnection;
import com.example.towersofhanoi.MySQLConnection;
import com.example.towersofhanoi.StatisticsData;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        disksColumn.setCellValueFactory(new PropertyValueFactory<>("disks"));
        statisticsTable.getItems().clear();
        DatabaseConnection mySQLConnection = new MySQLConnection();
        mySQLConnection.connect();
        ((MySQLConnection) mySQLConnection).extractStatistics(statisticsTable);
    }
}
