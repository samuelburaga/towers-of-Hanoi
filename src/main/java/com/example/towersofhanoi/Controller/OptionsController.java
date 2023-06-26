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

/**
 * The OptionsController class is responsible for controlling the options screen.
 * It handles user interactions and settings related to game options such as the number of disks and move animation speed.
 */
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

    /**
     * Initializes the controller.
     *
     * @param url            the location used to resolve relative paths
     * @param resourceBundle the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveAnimationSpeedSlider.valueProperty().addListener((ObservableValue<? extends Number> num, Number oldValue, Number newValue) -> {
            moveAnimationSpeed = (double) newValue;
            System.out.println(moveAnimationSpeed);
        });
    }

    /**
     * Handles the action when an option label is clicked.
     * Updates the selected number of disks and changes the color of the clicked label.
     *
     * @param e the mouse event
     */
    public void optionOnMouseClicked(MouseEvent e) {
        Label clickedLabel = (Label) e.getSource();
        numberOfDisks = Byte.parseByte(clickedLabel.getText());
        if (previousClickedLabel != null) {
            previousClickedLabel.setStyle(defaultColor);
        }
        clickedLabel.setStyle(clickedColor);
        previousClickedLabel = clickedLabel;
    }

    /**
     * Handles the action when the back button is clicked.
     * Changes the color of the clicked button and navigates back to the menu view.
     *
     * @param e the action event
     * @throws IOException if an error occurs during the opening of the menu view
     */
    public void backButtonOnAction(ActionEvent e) throws IOException {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor);
        }
        backButton.setStyle(clickedColor);
        previousClickedButton = backButton;
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
        MenuView menu = new MenuView();
        menu.start(new Stage());
    }

    /**
     * Handles the action when the play now button is clicked.
     * Changes the color of the clicked button and hides the current window.
     *
     * @param e the action event
     * @throws IOException if an error occurs
     */
    public void playNowButtonOnAction(ActionEvent e) throws IOException {
        if (previousClickedButton != null) {
            previousClickedButton.setStyle(defaultColor);
        }
        playNowButton.setStyle(clickedColor);
        previousClickedButton = playNowButton;
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
    }
}
