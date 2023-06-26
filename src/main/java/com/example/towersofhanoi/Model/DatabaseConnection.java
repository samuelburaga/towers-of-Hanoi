package com.example.towersofhanoi.Model;

import javafx.scene.control.TableView;

/**
 * The DatabaseConnection interface defines the methods for connecting to a database, performing various operations,
 * and extracting data from the database.
 *
 * @param <T> the type parameter for the user information
 */
public interface DatabaseConnection<T> {

    /**
     * Connects to the database.
     */
    void connect();

    /**
     * Disconnects from the database.
     */
    void disconnect();

    /**
     * Checks if a user with the specified username and password exists in the database.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return true if the user exists, false otherwise
     */
    boolean checkIfUserExists(final String username, final String password);

    /**
     * Deletes the account associated with the specified username from the database.
     *
     * @param username the username of the account to be deleted
     */
    void deleteAccount(final String username);

    /**
     * Updates the username for the account with the specified currentUsername to the newUsername.
     *
     * @param currentUsername the current username
     * @param newUsername     the new username
     */
    void updateUsername(final String currentUsername, final String newUsername);

    /**
     * Retrieves user information from the database based on the specified username.
     *
     * @param username the username of the user
     * @return the user information of type T
     */
    T getUserByUsername(final String username);

    /**
     * Gets the ID of the latest added user in the database (the auto_increment current value).
     *
     * @return the latest user ID
     */
    int getLatestUserId();

    /**
     * Inserts a new user with the specified first name, last name, username, and password into the database.
     *
     * @param first_name the first name of the new user
     * @param last_name  the last name of the new user
     * @param username   the username of the new user
     * @param password   the password of the new user
     */
    void insertNewUser(final String first_name, final String last_name, final String username, final String password);

    /**
     * Extracts statistics data from the database and populates it into the specified TableView.
     *
     * @param statisticsTable the TableView to populate with statistics data
     */
    void extractStatistics(TableView<StatisticsData> statisticsTable);
}