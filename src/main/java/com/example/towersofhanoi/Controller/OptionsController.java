package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Game;
import com.example.towersofhanoi.Tutorial;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class OptionsController {
    @FXML
    private Label option1;
    public void option1OnMouseClicked(MouseEvent e) {
        Game.disks = 1;
    }
}
