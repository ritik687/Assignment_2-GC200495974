package com.example.assignment2.Utilities;

import com.example.assignment2.Interfaces.MediaInitializable;
import com.example.assignment2.Interfaces.UserInitializable;
import com.example.assignment2.Interfaces.UserProfileDetailsInitializable;
import com.example.assignment2.Main;
import com.example.assignment2.Models.Media;
import com.example.assignment2.Models.User;
import com.example.assignment2.Models.UserProfileDetails;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class SceneChanger {


    public static void changeScenes(ActionEvent event,String fxmlFileName, String title) throws IOException {
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

    public static void changeScenes(MouseEvent event, String fxmlFileName, String title, User user) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());

        UserInitializable controller = fxmlLoader.getController();
        controller.loadUserDetailsFromGraphicView(user);

        //get the stage from the ActionEvent
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        // disabling the maximize button.
        stage.resizableProperty().setValue(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void changeScenes(MouseEvent event, String fxmlFileName, String title, String searchTerm, User user) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());

       UserInitializable controller = fxmlLoader.getController();
        controller.loadUserDetailsFromListView(searchTerm,user);

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


}
