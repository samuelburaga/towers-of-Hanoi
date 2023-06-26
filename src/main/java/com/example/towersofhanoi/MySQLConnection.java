package com.example.towersofhanoi;

import javafx.scene.control.TableView;

import java.sql.*;

public class MySQLConnection implements DatabaseConnection <ResultSet> {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // JDBC driver
    private static final String USERNAME = "root", PASSWORD = "hackerman"; // the username and password of the database
    private static final String HOSTNAME = "localhost", PORT = "3306"; // hostname and port
    public static final String database = "towers-of-hanoi"; // the name of the database
    private static final String JDBC_URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + database; // JDBC URL
    public static final String [] tables = {"users", "statistics", "achievements", "notifications", "complaints"};
    private Connection connection; // data member used for creating a connection with the database
    private Statement statement; // data member used for creating a statement
    public void loadDriver() {
        try {
            Class.forName(JDBC_DRIVER);
        }
        catch (ClassNotFoundException e) {
            System.out.println("Error. Couldn't load the driver!\n" + e);
            return;
        }
    } // loads the JDBC driver
    @Override
    public void connect() {
        try {
            this.connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            this.createStatement(connection);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void disconnect() {

    }
    public void createStatement(Connection connection) {
        try {
            this.statement = connection.createStatement();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    } // creates a statement using the connection parameter
    public ResultSet executeQuery(final String query) {
        try {
            ResultSet resultSet = this.statement.executeQuery(query);
            return resultSet;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    } // executes a SQL query that doesn't contain variable
    public ResultSet executeQueryWithVariables(String query, String[] variables) {
        try {
            PreparedStatement ps = this.connection.prepareStatement(query);
            for (int index = 0; index < variables.length; index++) {
                ps.setString(index + 1, variables[index]);
            }
            ResultSet resultSet = ps.executeQuery();
            return resultSet;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    } // executes a SQL query that contains variable
    public void executeUpdateWithVariables(String query, String[] variables) {
        try {
            PreparedStatement ps = this.connection.prepareStatement(query);
            for (int index = 0; index < variables.length; index++) {
                ps.setString(index + 1, variables[index]);
            }
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    } // executes a SQL update query that contains variable
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
    } // prints the reuslt of a SQL query
    @Override
    public boolean checkIfUserExists(final String username, final String password) {
        String query = "SELECT EXISTS (SELECT * FROM " + this.tables[0] + " WHERE username = ? AND password = ?)";
        String[] variables = new String[2];
        variables[0] = username;
        variables[1] = password;
        ResultSet check = this.executeQueryWithVariables(query, variables);
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
    @Override
    public void deleteAccount(final String username) {
        String query = "DELETE FROM " + this.tables[0] + " WHERE username = ?";
        String[] variables = new String[1];
        variables[0] = User.username;
        this.executeUpdateWithVariables(query, variables);
    }
    @Override
    public void updateUsername(final String currentUsername, final String newUsername) {
        String query = "UPDATE " + this.tables[0] + " SET username = ? WHERE username = ?";
        String[] variables = new String[2];
        variables[0] = newUsername;
        variables[1] = currentUsername;
        this.executeUpdateWithVariables(query, variables);
    }
    @Override
    public ResultSet getUserByUsername(final String username) {
        String query = "SELECT * FROM " + this.tables[0] + " WHERE username = ?";
        String[] variables = new String[1];
        variables[0] = username;
        ResultSet resultSet = this.executeQueryWithVariables(query, variables);
        return resultSet;
    }
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
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return user_id;
    }
    @Override
    public void extractStatistics(TableView<StatisticsData> statisticsTable) {
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
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
