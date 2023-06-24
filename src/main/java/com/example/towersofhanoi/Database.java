package com.example.towersofhanoi;

public interface Database {
    public static final String HOSTNAME = "localhost";
    public static final String PORT = "3306";
    void connect();
    void disconnect();
    boolean checkIfUserExists();
}
