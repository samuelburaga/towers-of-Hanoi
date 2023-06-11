package com.example.towersofhanoi;

import com.example.towersofhanoi.Controller.TutorialController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Tutorial extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("View/Tutorial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);

        // Pass the number of disks to the controller
        TutorialController controller = fxmlLoader.getController();
        byte numberOfDisks = Game.disks; // Replace with the actual number of disks
        controller.setNumberOfDisks(numberOfDisks);

        primaryStage.setTitle("Tutorial");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
