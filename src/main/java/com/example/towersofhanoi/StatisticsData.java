package com.example.towersofhanoi;

import java.sql.Time;

public class StatisticsData {
    private String usernameColumn;
    private int pointsColumn, disksColumn;
    private Time timeColumn;
    public StatisticsData(String username, int points, Time time, int disks) {
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
    public Time getTime() {
        return this.timeColumn;
    }
}
