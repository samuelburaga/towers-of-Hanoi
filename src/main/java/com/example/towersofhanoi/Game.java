package com.example.towersofhanoi;

public class Game {
    public static byte disks = 4;
    public static void automatic() {
            recursiveHanoi(disks, 'A', 'C', 'B');
    }
    public static void recursiveHanoi(byte numberOfDisks, char fromRod, char toRod, char auxRod) {
        if (numberOfDisks == 0) {
            return;
        }
        recursiveHanoi((byte) (numberOfDisks - 1), fromRod, auxRod, toRod);
        System.out.println("Move disk " + numberOfDisks + " from rod " + fromRod + " to rod " + toRod);
        recursiveHanoi((byte) (numberOfDisks - 1), auxRod, toRod, fromRod);
    }

}
