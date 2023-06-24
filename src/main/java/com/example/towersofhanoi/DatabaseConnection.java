package com.example.towersofhanoi;

public interface DatabaseConnection {
    public static final String HOSTNAME = "localhost";
    public static final String PORT = "3306";
    void connect();
    void disconnect();
    boolean checkIfUserExists(final String username, final String password);
    void deleteAccount(final String username);
    void updateUsername(final String currentUsername, final String newUsername);
    // void getUserByUsername(final String username);
    // void insertNewUser(final String first_name, final String last_name, final String username, final String password);
}
