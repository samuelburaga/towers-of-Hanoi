package com.example.towersofhanoi;
import org.bson.Document;

import java.sql.*;

public class User {
    public static int user_id;
    public static String first_name = "first_name", last_name = "last_name", username = "username";
    public static void updateData(final ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                User.user_id = resultSet.getInt("user_id");
                User.first_name = resultSet.getString("first_name");
                User.last_name = resultSet.getString("last_name");
                User.username = resultSet.getString("username");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the exception appropriately (e.g., show an error message)
        }
    }
    public static void updateData(final Document document) {
        if (document != null) {
            User.user_id = document.getInteger("user_id");
            User.first_name = document.getString("first_name");
            User.last_name = document.getString("last_name");
            User.username = document.getString("username");
        } else {
            System.out.println("User not found.");
        }
    }
    public static void updateData(final int user_id, final String first_name, final String last_name, final String username) {
        User.user_id = user_id;
        User.first_name = first_name;
        User.last_name = last_name;
        User.username = username;
    }
}
