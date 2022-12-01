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


    /**
     * This method will reload mine profile details on the starting and set the view at the center of the borderPane
     * @param event
     */
    @FXML
    void profileButtonClicked(MouseEvent event) {
        loadView("Views/mine-profile-details-view.fxml");
        profileButtonHBox.setStyle("-fx-background-color: #caf39a;");
        searchButtonHBox.setStyle("-fx-background-color: transparent;");


    }

    /**
     * This method will load the search view at the center of the border pane
     * @param event
     */
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

        loadView("Views/mine-profile-details-view.fxml");

        Image image = new Image(Main.class.getResourceAsStream("images/profile.JPG"));
        imageCircle.setFill(new ImagePattern(image));

    }

    /**
     * This method will take resources that are views as a parameter and set it at the center of the borderpane
     * @param resourceName
     */
    private void loadView(String resourceName){
        Parent root = null;

        try {
            root= FXMLLoader.load(Main.class.getResource(resourceName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(root);

    }


    /**
     * This method will call the scene home-view.fxml if clicked on the instagram panel
     * @param event
     * @throws IOException
     */
    @FXML
    void InstagramPanelClicked(MouseEvent event) throws IOException {
        SceneChanger.changeScenes(event, "Views/home-view.fxml","Ram's Profile");

    }

    /**
     * This method will logout the profile and take you to the login page
     * @param event
     * @throws IOException
     */
    @FXML
    void logoutButtonClicked(MouseEvent event) throws IOException {

        int respone = JOptionPane.showConfirmDialog(this, "Do you want to continue action?","Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(respone == JOptionPane.YES_OPTION) {
            SceneChanger.changeScenes(event, "Views/login-view.fxml", "Login Page");
        }
    }


}
