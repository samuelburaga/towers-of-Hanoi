package com.example.towersofhanoi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("View/hello-view.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("View/log_in.fxml"));
        // Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setMaximized(true); // Set the window to be maximized
        primaryStage.setTitle("Towers of Hanoi");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
       launch();
    }
}