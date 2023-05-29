package com.example.towersofhanoi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage logInStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("View/log_in.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        logInStage.setTitle("Log In");
        logInStage.setScene(scene);
        logInStage.show();
    }
}
