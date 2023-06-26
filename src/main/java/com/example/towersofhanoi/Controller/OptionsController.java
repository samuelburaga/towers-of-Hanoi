package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Model.Game;
import com.example.towersofhanoi.View.MenuView;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionsController implements Initializable {
    @FXML
    private Label option1, option2, option3, option4, option5, option6, option7, option8, option9, option10;
    @FXML
    private Label previousClickedLabel;
    @FXML
    private Button backButton, playNowButton;
    @FXML
    private Button previousClickedButton;
    @FXML
    private Slider moveAnimationSpeedSlider;
    private String defaultColor = "-fx-background-color: #FA8163;";
    private String clickedColor = "-fx-background-color: #0FB4BB;";
    public static byte numberOfDisks = 2;
    public static double moveAnimationSpeed = 0.1;
    public void optionOnMouseClicked(MouseEvent e) {
        Label clickedLabel = (Label) e.getSource(); // get the label which was clicked
        numberOfDisks = Byte.parseByte(clickedLabel.getText());
        if (previousClickedLabel != null) {
            previousClickedLabel.setStyle(defaultColor); // Revert the color of the previously clicked label
        }
        clickedLabel.setStyle(clickedColor); // Set the color for the newly clicked label
        previousClickedLabel = clickedLabel; // Update the previously clicked label
    }
    public void backButtonOnAction(ActionEvent e) throws IOException {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor); // Revert the color of the previously clicked button
        }
        backButton.setStyle(clickedColor); // Set the color for the newly clicked button
        previousClickedButton = backButton; // Update the previously clicked button
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
        MenuView menu = new MenuView();
        menu.start(new Stage());
    } // go back to the menu
    public void playNowButtonOnAction(ActionEvent e) throws IOException {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor); // Revert the color of the previously clicked button
        }
        playNowButton.setStyle(clickedColor); // Set the color for the newly clicked button
        previousClickedButton = playNowButton; // Update the previously clicked button
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
    } // play the game now
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveAnimationSpeedSlider.valueProperty().addListener((ObservableValue<? extends Number> num, Number oldValue, Number newValue)->{
            moveAnimationSpeed = (double) newValue;
            System.out.println(moveAnimationSpeed);
        });
    } // initializer for the slider
}
