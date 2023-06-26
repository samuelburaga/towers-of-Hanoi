package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Model.DatabaseConnection;
import com.example.towersofhanoi.Model.MongoDBConnection;
import com.example.towersofhanoi.Model.MySQLConnection;
import com.example.towersofhanoi.Model.StatisticsData;
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
    private DatabaseConnection mySQLConnection, mongoDBConnection;
    public StatisticsController() {
        // create database model objects
        this.mySQLConnection = new MySQLConnection();
        this.mongoDBConnection = new MongoDBConnection();
    }
    public void showStatistics() throws SQLException {
        // Set table column names
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        disksColumn.setCellValueFactory(new PropertyValueFactory<>("disks"));
        statisticsTable.getItems().clear();
        this.mySQLConnection.connect(); // connect to the database
        this.mySQLConnection.extractStatistics(statisticsTable); // extract the statistics
    } // show the 10 best performances
}
