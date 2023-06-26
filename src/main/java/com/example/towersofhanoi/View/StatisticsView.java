package com.example.towersofhanoi.View;

import com.example.towersofhanoi.Controller.StatisticsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Samuel Buraga
 * @version 1.0
 */

/**
 * The StatisticsView class is responsible for displaying a table with the 10 best performances of the users.
 * It extends the JavaFX Application class and provides the necessary methods for initializing and showing the statistics screen.
 */
public class StatisticsView extends Application {

    /**
     * The start method is called when the application is launched. It initializes the statistics screen and shows it on the primary stage.
     *
     * @param primaryStage the primary stage of the application
     * @throws IOException  if an I/O error occurs while loading the FXML file
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Statistics.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        StatisticsController statisticsController = fxmlLoader.getController(); // get the controller
        statisticsController.showStatistics();
        primaryStage.setTitle("Statistics");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main method is the entry point of the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}