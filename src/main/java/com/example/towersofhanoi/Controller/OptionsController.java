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
    private Label option1, option2, option3, option4, option5, option6, option7, option8, option9, option10;
    public void optionOnMouseClicked(MouseEvent e) {
        Label clickedLabel = (Label) e.getSource();
        System.out.println("Clicked label: " + clickedLabel.getText());
        Game.disks = Byte.parseByte(clickedLabel.getText());
        System.out.println(Game.disks);
    }
}
