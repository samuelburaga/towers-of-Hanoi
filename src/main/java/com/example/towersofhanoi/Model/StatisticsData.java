package com.example.towersofhanoi.Model;

public class StatisticsData <T> {
    private String usernameColumn;
    private int pointsColumn, disksColumn;
    private T timeColumn; // make timeColumn data type generic
    public StatisticsData(String username, int points, T time, int disks) {
        this.usernameColumn = username;
        this.pointsColumn = points;
        this.disksColumn = disks;
        this.timeColumn = time;
    }
    public String getUsername() {
        return this.usernameColumn;
    }
    public int getPoints() {
        return this.pointsColumn;
    }
    public int getDisks() {
        return this.disksColumn;
    }
    public T getTime() {
        return this.timeColumn;
    }
} // generic class
