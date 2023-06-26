package com.example.towersofhanoi.Model;

import javafx.scene.control.TableView;

import java.sql.*;

/**
 * This class represents a connection to a MySQL database and implements the DatabaseConnection interface.
 * It provides methods for connecting to the database, executing queries, updating data, and extracting statistics.
 */
public class MySQLConnection implements DatabaseConnection<ResultSet> {
    private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // JDBC driver
    private String USERNAME = null, PASSWORD = null; // the username and password of the database
    private String HOSTNAME = null, PORT = null; // hostname and port
    public String database = null; // the name of the database
    private String JDBC_URL = null; // JDBC URL
    public static final String[] tables = {"users", "statistics", "achievements", "notifications", "complaints"};
    private Connection connection; // data member used for creating a connection with the database
    private Statement statement; // data member used for creating a statement

    /**
     * Constructs a new instance of the MySQLConnection class with default connection parameters.
     * Sets the username, password, hostname, port, database name, and JDBC URL.
     */
    public MySQLConnection() {
        this.USERNAME = "root";
        this.PASSWORD = "hackerman";
        this.HOSTNAME = "localhost";
        this.PORT = "3306";
        this.database = "towers-of-hanoi";
        this.JDBC_URL = "jdbc:mysql://" + this.HOSTNAME + ":" + this.PORT + "/" + this.database;
    }

    /**
     * Loads the MySQL JDBC driver.
     * Throws an exception if the driver is not found.
     */
    public void loadDriver() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Error. Couldn't load the driver!\n" + e);
            return;
        }
    }

    /**
     * Connects to the MySQL database using the JDBC URL, username, and password.
     * Creates a statement for executing queries.
     */
    @Override
    public void connect() {
        try {
            this.connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            this.createStatement(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnects from the MySQL database.
     * Currently empty as no specific disconnection logic is implemented.
     */
    @Override
    public void disconnect() {
        // Implementation for disconnection logic if needed
    }

    /**
     * Creates a statement using the provided database connection.
     *
     * @param connection the database connection
     */
    public void createStatement(Connection connection) {
        try {
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes a SQL query that doesn't contain variables.
     *
     * @param query the SQL query to execute
     * @return the result set returned by the query
     */
    public ResultSet executeQuery(final String query) {
        try {
            ResultSet resultSet = this.statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Executes a SQL query that contains variables.
     *
     * @param query     the SQL query to execute
     * @param variables an array of variables to substitute in the query
     * @return the result set returned by the query
     */
    public ResultSet executeQueryWithVariables(final String query, final String[] variables) {
        try {
            PreparedStatement ps = this.connection.prepareStatement(query);
            for (int index = 0; index < variables.length; index++) {
                ps.setString(index + 1, variables[index]);
            }
            ResultSet resultSet = ps.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Executes a SQL update query that contains variables.
     *
     * @param query     the SQL update query to execute
     * @param variables an array of variables to substitute in the query
     */
    public void executeUpdateWithVariables(final String query, final String[] variables) {
        try {
            PreparedStatement ps = this.connection.prepareStatement(query);
            for (int index = 0; index < variables.length; index++) {
                ps.setString(index + 1, variables[index]);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the result of a SQL query.
     *
     * @param resultSet the result set to print
     */
    public void printQuery(final ResultSet resultSet) {
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numberOfColumns = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                for (int index = 1; index <= numberOfColumns; index++) {
                    if (index > 1) {
                        System.out.print(", ");
                    }
                    String columnValue = resultSet.getString(index);
                    System.out.print(columnValue + " " + resultSetMetaData.getColumnName(index));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a user exists in the database with the given username and password.
     *
     * @param username the username to check
     * @param password the password to check
     * @return true if the user exists, false otherwise
     */
    @Override
    public boolean checkIfUserExists(final String username, final String password) {
        String query = "SELECT EXISTS (SELECT * FROM " + this.tables[0] + " WHERE username = ? AND password = ?)";
        String[] variables = new String[2];
        variables[0] = username;
        variables[1] = password;
        ResultSet check = this.executeQueryWithVariables(query, variables);
        try {
            if (check.next()) {
                int exists = check.getInt(1);
                boolean existsResult = (exists == 1);
                return existsResult;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * Checks if a user exists in the database with the given username and password.
     *
     * @param username the username to check
     * @param password the password to check
     * @return true if the user exists, false otherwise
     */
    @Override
    public boolean checkIfUserExistsSI(final String username, final String password) {
        String query = "SELECT EXISTS (SELECT * FROM " + this.tables[0] + " WHERE username = ? OR password = ?)";
        String[] variables = new String[2];
        variables[0] = username;
        variables[1] = password;
        ResultSet check = this.executeQueryWithVariables(query, variables);
        try {
            if (check.next()) {
                int exists = check.getInt(1);
                boolean existsResult = (exists == 1);
                return existsResult;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * Deletes a user account from the database with the given username.
     *
     * @param username the username of the account to delete
     */
    @Override
    public void deleteAccount(final String username) {
        String query = "DELETE FROM " + this.tables[0] + " WHERE username = ?";
        String[] variables = new String[1];
        variables[0] = User.username;
        this.executeUpdateWithVariables(query, variables);
    }

    /**
     * Updates the username of an account in the database.
     *
     * @param currentUsername the current username of the account
     * @param newUsername     the new username to update
     */
    @Override
    public void updateUsername(final String currentUsername, final String newUsername) {
        String query = "UPDATE " + this.tables[0] + " SET username = ? WHERE username = ?";
        String[] variables = new String[2];
        variables[0] = newUsername;
        variables[1] = currentUsername;
        this.executeUpdateWithVariables(query, variables);
    }

    /**
     * Retrieves user information from the database based on the username.
     *
     * @param username the username to search for
     * @return the result set containing the user information
     */
    @Override
    public ResultSet getUserByUsername(final String username) {
        String query = "SELECT * FROM " + this.tables[0] + " WHERE username = ?";
        String[] variables = new String[1];
        variables[0] = username;
        ResultSet resultSet = this.executeQueryWithVariables(query, variables);
        return resultSet;
    }

    /**
     * Inserts a new user into the database with the provided information.
     *
     * @param first_name the first name of the user
     * @param last_name  the last name of the user
     * @param username   the username of the user
     * @param password   the password of the user
     */
    @Override
    public void insertNewUser(final String first_name, final String last_name, final String username, final String password) {
        String query = "INSERT INTO " + this.tables[0] + " (first_name, last_name, username, password, created_at) VALUES (?, ?, ?, ?, NOW())";
        String[] variables = new String[4];
        variables[0] = first_name;
        variables[1] = last_name;
        variables[2] = username;
        variables[3] = password;
        this.executeUpdateWithVariables(query, variables);
    }

    /**
     * Retrieves the latest user ID from the users table in the database.
     *
     * @return the latest user ID, or -1 if no user ID is found
     */
    @Override
    public int getLatestUserId() {
        int user_id = -1; // Default value if no user_id is found
        try {
            String query = "SELECT MAX(user_id) FROM users";
            ResultSet resultSet = this.executeQuery(query);
            // Check if the result set has a value
            if (resultSet.next()) {
                user_id = resultSet.getInt(1);
            }
            resultSet.close();             // Close the result set and statement
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user_id;
    }

    /**
     * Extracts statistics from the database and populates a TableView with the data.
     *
     * @param statisticsTable the TableView to populate with statistics data
     */
    @Override
    public void extractStatistics(final TableView<StatisticsData> statisticsTable) {
        String query =
                "SELECT s.points, s.disks, s.time, u.username FROM statistics s " +
                        "JOIN users u ON s.users_user_id = u.user_id " +
                        "ORDER BY s.points DESC, s.time ASC, s.disks DESC, u.user_id ASC LIMIT 10"; // query that extracts the 10 best performances
        ResultSet resultSet = this.executeQuery(query);
        try {
            while (resultSet.next()) {
                int points = resultSet.getInt("points");
                int disks = resultSet.getInt("disks");
                Time time = resultSet.getTime("time");
                String username = resultSet.getString("username");
                StatisticsData data = new StatisticsData(username, points, time, disks); // create StatisticsData object
                statisticsTable.getItems().add(data); // add the data to the table
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}