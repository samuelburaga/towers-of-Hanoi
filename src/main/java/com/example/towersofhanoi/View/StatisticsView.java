package com.example.towersofhanoi.View;

import com.example.towersofhanoi.Controller.StatisticsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class StatisticsView extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/Statistics.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        StatisticsController statisticsController = fxmlLoader.getController(); // get the controller
        statisticsController.showStatistics();
        primaryStage.setTitle("Statistics");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
