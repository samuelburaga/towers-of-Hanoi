package com.example.towersofhanoi;

import com.example.towersofhanoi.Controller.PlayController;
import com.example.towersofhanoi.Controller.TutorialController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Play extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/Play.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        // Pass the number of disks to the controller
        PlayController controller = fxmlLoader.getController();
        byte numberOfDisks = Game.disks; // Replace with the actual number of disks
        controller.setNumberOfDisks(numberOfDisks);
        controller.setRods();
        primaryStage.setTitle("Play");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
