package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Game;
import com.example.towersofhanoi.Menu;
import com.example.towersofhanoi.Tutorial;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;

public class OptionsController {
    @FXML
    private Label option1, option2, option3, option4, option5, option6, option7, option8, option9, option10;
    @FXML
    private Label previousClickedLabel;
    public void optionOnMouseClicked(MouseEvent e) {
        Label clickedLabel = (Label) e.getSource();
        String clickedColor = "-fx-background-color: #0FB4BB;"; // Set the desired color
        String defaultColor = "-fx-background-color: #333E41;"; // Set the default color
        Game.numberOfDisks = Byte.parseByte(clickedLabel.getText());
        if (previousClickedLabel != null) {
            previousClickedLabel.setStyle(defaultColor); // Revert the color of the previously clicked label
        }
        clickedLabel.setStyle(clickedColor); // Set the color for the newly clicked label
        previousClickedLabel = clickedLabel; // Update the previously clicked label
    }
    public void backButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
        Menu menu = new Menu();
        menu.start(new Stage());
    }
    public void playNowButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
    }
}
