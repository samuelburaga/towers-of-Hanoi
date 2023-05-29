package com.example.towersofhanoi;

import java.sql.*;
public class DatabaseConnection {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
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
    public static void main(String[] args) {

    }
}
