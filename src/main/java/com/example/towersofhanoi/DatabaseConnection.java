package com.example.towersofhanoi;

import java.sql.*;
public class DatabaseConnection {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/towers-of-Hanoi";
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
            Connection con = DriverManager.getConnection(JDBC_URL,"root", "");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
