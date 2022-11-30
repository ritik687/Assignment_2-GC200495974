package com.example.assignment2;

import com.example.assignment2.Models.APIResponse;
import com.example.assignment2.Utilities.APIUtility;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Views/home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ram's Profile");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/insta.png")));

        //disabling the maximize button
        stage.resizableProperty().setValue(false);

//      stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Calling API and displaying Ram's Profile. Please wait a little...:)");
        launch();

    }
}