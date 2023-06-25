package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Game;
import com.example.towersofhanoi.Menu;
import com.example.towersofhanoi.Tutorial;
import javafx.animation.FadeTransition;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionsController implements Initializable {
    @FXML
    private Label option1, option2, option3, option4, option5, option6, option7, option8, option9, option10;
    @FXML
    private Label previousClickedLabel;
    @FXML
    private Button backButton, saveChangesButton, playNowButton;
    @FXML
    private Button previousClickedButton;
    @FXML
    private Slider moveAnimationSpeedSlider;
    private String defaultColor = "-fx-background-color: #FA8163;"; // Set the default color
    private String clickedColor = "-fx-background-color: #0FB4BB;"; // Set the desired color
    private byte numberOfDisks;
    private double moveAnimationSpeed = 1.5;
    public void optionOnMouseClicked(MouseEvent e) {
        Label clickedLabel = (Label) e.getSource();
        numberOfDisks = Byte.parseByte(clickedLabel.getText());
        if (previousClickedLabel != null) {
            previousClickedLabel.setStyle(defaultColor); // Revert the color of the previously clicked label
        }
        clickedLabel.setStyle(clickedColor); // Set the color for the newly clicked label
        previousClickedLabel = clickedLabel; // Update the previously clicked label
    }
    public void backButtonOnAction(ActionEvent e) throws IOException {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor); // Revert the color of the previously clicked label
        }
        backButton.setStyle(clickedColor); // Set the color for the newly clicked label
        previousClickedButton = backButton; // Update the previously clicked label
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
        Menu menu = new Menu();
        menu.start(new Stage());
    }
    public void saveChangesButtonOnAction(ActionEvent e) {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor); // Revert the color of the previously clicked label
        }
        saveChangesButton.setStyle(clickedColor); // Set the color for the newly clicked label
        previousClickedButton = saveChangesButton; // Update the previously clicked label
        Game.moveDelay = Duration.millis(moveAnimationSpeed);
        Game.DISKS = numberOfDisks;
    }
    public void playNowButtonOnAction(ActionEvent e) throws IOException {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor); // Revert the color of the previously clicked label
        }
        playNowButton.setStyle(clickedColor); // Set the color for the newly clicked label
        previousClickedButton = playNowButton; // Update the previously clicked label
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveAnimationSpeedSlider.valueProperty().addListener((ObservableValue<? extends Number> num, Number oldValue, Number newValue)->{
            moveAnimationSpeed = (double) newValue;
            System.out.println(moveAnimationSpeed);
        });
    }
}
