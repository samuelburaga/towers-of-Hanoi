package com.example.towersofhanoi;

import javafx.scene.control.TableView;

public interface DatabaseConnection <T> {
    void connect(); // connects with the database
    void disconnect(); // disconnects from the database
    boolean checkIfUserExists(final String username, final String password);
    void deleteAccount(final String username); // deletes account
    void updateUsername(final String currentUsername, final String newUsername); // updates username
    T getUserByUsername(final String username); // gets user information using his username
    int getLatestUserId(); // gets the id of the latest added user (the auto_increment current value)
    void insertNewUser(final String first_name, final String last_name, final String username, final String password); // inserts new user in to the database
    void extractStatistics(TableView<StatisticsData> statisticsTable); // extracts data from the statistics table
}
