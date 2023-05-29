package com.example.towersofhanoi;

import javafx.scene.chart.PieChart;

import java.sql.*;
public class DatabaseConnection {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String hostname = "localhost", port = "3306", database = "towers-of-hanoi";
    static final String JDBC_URL = "jdbc:mysql://" + hostname + "/" + port + "/" + database;
    protected Connection connection;
    protected Statement statement;

    public DatabaseConnection(Connection connection) {
        this.connection = connection;
    }
    public Connection getConnection() {
        return this.connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
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
            connection = DriverManager.getConnection(JDBC_URL,"root", "");
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
    public void executeQuery(final String query) {
        try {
            ResultSet resultSet = statement.executeQuery(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
