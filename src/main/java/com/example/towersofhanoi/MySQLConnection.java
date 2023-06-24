package com.example.towersofhanoi;

import java.sql.*;

public class MySQLConnection implements Database {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root", PASSWORD = "hackerman";
    private static final String HOSTNAME = "localhost", PORT = "3306";
    public static final String database = "towers-of-hanoi";
    private static final String JDBC_URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + database;
    private static final String [] tables = {"users", "statistics", "achievements", "notifications", "complaints"};
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
    public static void main(String[] args) {
    }
}
