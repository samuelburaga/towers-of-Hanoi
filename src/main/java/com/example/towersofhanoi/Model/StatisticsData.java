package com.example.towersofhanoi.Model;

/**
 * The StatisticsData class is used for adding user data to the table with the best 10 performances of the users.
 * It is a generic class that can store various types of data.
 *
 * @param <T> the type of data stored in the time column
 */
public class StatisticsData<T> {
    private String usernameColumn;
    private int pointsColumn, disksColumn;
    private T timeColumn;

    /**
     * Constructs a new StatisticsData object with the specified username, points, time, and number of disks.
     *
     * @param username the username of the player
     * @param points   the points earned by the player
     * @param time     the time data for the player
     * @param disks    the number of disks used in the game
     */
    public StatisticsData(String username, int points, T time, int disks) {
        this.usernameColumn = username;
        this.pointsColumn = points;
        this.disksColumn = disks;
        this.timeColumn = time;
    }

    /**
     * Returns the username stored in the statistics data.
     *
     * @return the username
     */
    public String getUsername() {
        return this.usernameColumn;
    }

    /**
     * Returns the points stored in the statistics data.
     *
     * @return the points
     */
    public int getPoints() {
        return this.pointsColumn;
    }

    /**
     * Returns the number of disks stored in the statistics data.
     *
     * @return the number of disks
     */
    public int getDisks() {
        return this.disksColumn;
    }

    /**
     * Returns the time stored in the statistics data.
     *
     * @return the time
     */
    public T getTime() {
        return this.timeColumn;
    }
}