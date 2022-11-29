package com.example.assignment2.Utilities;

import com.example.assignment2.Controllers.MediaInitializable;
import com.example.assignment2.Controllers.UserProfileDetailsInitializable;
import com.example.assignment2.Main;
import com.example.assignment2.Models.Media;
import com.example.assignment2.Models.UserProfileDetails;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;


public class SceneChanger {



    /**
     * This method will change to the new scene passed into the method as an argument
     */
    public static void changeScenes( MouseEvent event,String fxmlFileName, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());

        //derive the stage object from the action event
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        // disabling the maximize button.
        stage.resizableProperty().setValue(false);
        stage.setScene(scene);
        stage.show();
    }


    public static void changeScenes(MouseEvent event, String fxmlFileName, String title, Media media) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());

        MediaInitializable controller = fxmlLoader.getController();
        controller.loadMediaDetails(media);

        //get the stage from the ActionEvent
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        // disabling the maximize button.
        stage.resizableProperty().setValue(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void changeScenes(MouseEvent event, String fxmlFileName, String title, Media media, UserProfileDetails userProfileDetail) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());

        MediaInitializable mediaController = fxmlLoader.getController();
        mediaController.loadMediaDetails(media);

        UserProfileDetailsInitializable userProfileDetailsController = fxmlLoader.getController();
        userProfileDetailsController.loadUserProfileDetails(userProfileDetail);

        //get the stage from the ActionEvent
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        // disabling the maximize button.
        stage.resizableProperty().setValue(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void changeScenes(ActionEvent event, String fxmlFileName, String title, UserProfileDetails userProfileDetail) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());

        UserProfileDetailsInitializable userProfileDetailsController = fxmlLoader.getController();
        userProfileDetailsController.loadUserProfileDetails(userProfileDetail);

        //get the stage from the ActionEvent
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        // disabling the maximize button.
        stage.resizableProperty().setValue(false);
        stage.setScene(scene);
        stage.show();
    }


    public static void changeScenes( ActionEvent event,String fxmlFileName, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());

        //derive the stage object from the action event
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        // disabling the maximize button.
        stage.resizableProperty().setValue(false);
        stage.setScene(scene);
        stage.show();
    }




}
