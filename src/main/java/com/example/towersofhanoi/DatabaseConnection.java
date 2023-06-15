package com.example.towersofhanoi;

import javafx.scene.chart.PieChart;

import java.sql.*;
public class DatabaseConnection {
    static final String username = "root", password = "hackerman";
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String hostname = "localhost", port = "3306";
    public static final String database = "towers-of-hanoi";
    public static final String [] tables = {"users", "scores"};
    static final String JDBC_URL = "jdbc:mysql://" + hostname + ":" + port + "/" + database;
    protected Connection connection;
    protected Statement statement;
    public DatabaseConnection() {

    }
    public DatabaseConnection(Connection connection) {
        this.connection = connection;
    }
    public Connection getConnection() {
        return this.connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public Statement getStatement() {
        return statement;
    }
    public void setStatement(Statement statement) {
        this.statement = statement;
    }
    public void loadDriver()
    {
        try {
            Class.forName(JDBC_DRIVER);
        }
        catch (ClassNotFoundException e) {
            System.out.println(" Error. Couldn't load driver!\n" + e);
            return;
        }
    }
    public void connect()
    {
        try {
            connection = DriverManager.getConnection(JDBC_URL, username, password);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public void Statement()
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
////        try {
////            ResultSet resultSet = this.statement.executeUpdate(query);
////            return resultSet;
////        }
////        catch (SQLException e){
////            e.printStackTrace();
////        }
////        return null;
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
            int columnsNumber = resultSetMetaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) {
                        System.out.print(", ");
                    }
                    String columnValue = resultSet.getString(i);
                    System.out.print(columnValue + " " + resultSetMetaData.getColumnName(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

    }
}
