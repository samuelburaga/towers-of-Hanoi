package com.example.towersofhanoi;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class PlayerGame extends Game{
    public boolean validMove(char fromRod, char toRod) {
        Pane fromPane = getRodPane(fromRod);
        Pane toPane = getRodPane(toRod);
        if (fromPane == null || toPane == null) {
            return false; // Invalid move if either rod is null
        }
        if (fromPane.getChildren().isEmpty()) {
            return false; // "from" rod is empty, move is not valid
        }
        // Get the top disk on the "to" rod, if it exists
        Rectangle topDisk = toPane.getChildren().isEmpty() ? null : (Rectangle) toPane.getChildren().get(toPane.getChildren().size() - 1);
        Rectangle movingDisk = (Rectangle) fromPane.getChildren().get(fromPane.getChildren().size() - 1);
        if (topDisk != null && movingDisk.getWidth() >= topDisk.getWidth()) {
            return false; // Moving disk is larger or equal to the top disk on the "to" rod, move is not valid
        }
        return true; // Move is valid
    }
}
