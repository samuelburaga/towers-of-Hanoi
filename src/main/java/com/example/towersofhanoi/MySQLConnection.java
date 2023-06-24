package com.example.towersofhanoi;

import java.sql.*;

public class MySQLConnection implements DatabaseConnection {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root", PASSWORD = "hackerman";
    private static final String HOSTNAME = "localhost", PORT = "3306";
    public static final String database = "towers-of-hanoi";
    private static final String JDBC_URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + database;
    public static final String [] tables = {"users", "statistics", "achievements", "notifications", "complaints"};
    private Connection connection;
    private Statement statement;
    public MySQLConnection() {

    }
    public void loadDriver() {
        try {
            Class.forName(JDBC_DRIVER);
        }
        catch (ClassNotFoundException e) {
            System.out.println("Error. Couldn't load the driver!\n" + e);
            return;
        }
    }
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
    public void createStatement(Connection connection)
    {
        try {
            this.statement = connection.createStatement();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public ResultSet executeQuery(final String query) {
        try {
            ResultSet resultSet = this.statement.executeQuery(query);
            return resultSet;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    //    public ResultSet executeUpdate(final String query) {
//        try {
//            ResultSet resultSet = this.statement.executeUpdate(query);
//            return resultSet;
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//        return null;
//    }
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
    }
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
    }
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
        System.out.println("Account deleted.");
    }
    @Override
    public void updateUsername(final String currentUsername, final String newUsername) {
        String query = "UPDATE " + this.tables[0] + " SET username = ? WHERE username = ?";
        String[] variables = new String[2];
        variables[0] = newUsername;
        variables[1] = currentUsername;
        this.executeUpdateWithVariables(query, variables);
    }
    public ResultSet getUserByUsername(final String username) {
        String query = "SELECT * FROM " + this.tables[0] + " WHERE username = ?";
        String[] variables = new String[1];
        variables[0] = username;
        ResultSet resultSet = this.executeQueryWithVariables(query, variables);
        return resultSet;
    }
    public void insertNewUser(final String first_name, final String last_name, final String username, final String password) {
        String query = "INSERT INTO users (first_name, last_name, username, password, created_at) VALUES (?, ?, ?, ?, NOW())";
        String[] variables = new String[4];
        variables[0] = first_name;
        variables[1] = last_name;
        variables[2] = username;
        variables[3] = password;
        this.executeUpdateWithVariables(query, variables);
    }
    public static void main(String[] args) {
    }
}
