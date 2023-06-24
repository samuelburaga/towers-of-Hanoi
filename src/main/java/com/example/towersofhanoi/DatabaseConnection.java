package com.example.towersofhanoi;

import javafx.scene.control.TableView;

import java.sql.SQLException;

public interface DatabaseConnection <T> {
    public static final String HOSTNAME = "localhost";
    public static final String PORT = "3306";
    void connect();
    void disconnect();
    boolean checkIfUserExists(final String username, final String password);
    void deleteAccount(final String username);
    void updateUsername(final String currentUsername, final String newUsername);
    T getUserByUsername(final String username);
    int getLatestUserId();
    void insertNewUser(final String first_name, final String last_name, final String username, final String password);
    void extractStatistics(TableView<StatisticsData> statisticsTable);
}
