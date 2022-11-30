package com.example.assignment2.Controllers;

import com.example.assignment2.Main;
import com.example.assignment2.Models.UserProfileDetails;
import com.example.assignment2.Utilities.APIUtility;
import com.example.assignment2.Utilities.SceneChanger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeViewController extends Component implements Initializable {

    @FXML
    private AnchorPane childAnchorPane;

    @FXML
    private HBox instagramPanelHBox;

    @FXML
    private BorderPane borderPane = null;

    @FXML
    private Circle imageCircle;

    @FXML
    private HBox profileButtonHBox;

    @FXML
    private HBox searchButtonHBox;

    @FXML
    private HBox logoutButtonHBox;



    @FXML
    void profileButtonClicked(MouseEvent event) {

//            borderPane.setCenter(childAnchorPane);
        loadView("Views/mine-profile-details-view.fxml");
        profileButtonHBox.setStyle("-fx-background-color: #caf39a;");
        searchButtonHBox.setStyle("-fx-background-color: transparent;");
    }

    @FXML
    void searchButtonClicked(MouseEvent event) {

        loadView("Views/search-view.fxml");
        profileButtonHBox.setStyle("-fx-background-color: transparent;");
        searchButtonHBox.setStyle("-fx-background-color: #caf39a;");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profileButtonHBox.setCursor(Cursor.HAND);
        searchButtonHBox.setCursor(Cursor.HAND);
        instagramPanelHBox.setCursor(Cursor.HAND);
        logoutButtonHBox.setCursor(Cursor.HAND);

        profileButtonHBox.setStyle("-fx-background-color: #caf39a;");


        String profilePictureURL = null;
        try {
            profilePictureURL = APIUtility.getMineProfilePictureURL();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String response = APIUtility.sendGETRequest(profilePictureURL);

            if (response != "Error")
            {
                Image image = new Image(profilePictureURL);
                imageCircle.setFill(new ImagePattern(image));
            } else {
                imageCircle.setFill(new ImagePattern(new Image(Main.class.getResourceAsStream("images/noProfileImage.png"))));
            }


        loadView("Views/mine-profile-details-view.fxml");

    }

    private void loadView(String resourceName){
        Parent root = null;

        try {
            root= FXMLLoader.load(Main.class.getResource(resourceName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(root);

    }




    @FXML
    void InstagramPanelClicked(MouseEvent event) throws IOException {
        SceneChanger.changeScenes(event, "Views/home-view.fxml","Ram's Profile");

    }


    @FXML
    void logoutButtonClicked(MouseEvent event) throws IOException {

        int respone = JOptionPane.showConfirmDialog(this, "Do you want to continue action?","Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(respone == JOptionPane.YES_OPTION) {
            SceneChanger.changeScenes(event, "Views/login-view.fxml", "Login Page");
        }
    }

}
