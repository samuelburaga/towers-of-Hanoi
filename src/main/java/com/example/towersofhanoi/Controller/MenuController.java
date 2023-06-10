package com.example.towersofhanoi.Controller;

import com.example.towersofhanoi.Menu;
import com.example.towersofhanoi.Tutorial;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    private Button quitButton, tutorialButton;
    public void quitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
    public void tutorialButtonOnAction(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        // thisStage.hide();

        Tutorial tutorial = new Tutorial();
        tutorial.start(new Stage());
    }
}
