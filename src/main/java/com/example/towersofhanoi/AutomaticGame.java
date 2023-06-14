package com.example.towersofhanoi;

import javafx.application.Platform;

public class AutomaticGame extends Game {
    public static void recursiveHanoi(byte numberOfDisks, char fromRod, char toRod, char auxRod) {
        if (numberOfDisks == 0) {
            return;
        }
        recursiveHanoi((byte) (numberOfDisks - 1), fromRod, auxRod, toRod);
        System.out.println("Move disk " + numberOfDisks + " from rod " + fromRod + " to rod " + toRod);
        moveDisk(numberOfDisks, fromRod, toRod);
        recursiveHanoi((byte) (numberOfDisks - 1), auxRod, toRod, fromRod);
    }
    public static void automatic(Runnable onFinishCallback) {
        animationThread = new Thread(new Game.AnimationRunnable(onFinishCallback));
        animationThread.start();
    }
    private static class AnimationRunnable implements Runnable {
        private final Runnable onFinishCallback;
        public AnimationRunnable(Runnable onFinishCallback) {
            this.onFinishCallback = onFinishCallback;
        }
        @Override
        public void run() {
            recursiveHanoi(numberOfDisks, 'A', 'C', 'B');
            animationThread = null; // Set the animation thread to null after completion
            Platform.runLater(onFinishCallback);
        }
}
