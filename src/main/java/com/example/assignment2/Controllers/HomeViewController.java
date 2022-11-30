package com.example.assignment2.Controllers;

import com.example.assignment2.Main;
import com.example.assignment2.Utilities.SceneChanger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {

    @FXML
    private AnchorPane childAnchorPane;

    @FXML
    private Button homeButton;

    @FXML
    private Button searchButton;

    @FXML
    private HBox instagramPanelHBox;

    @FXML
    private BorderPane borderPane = null;

    @FXML
    void homeButtonClicked(MouseEvent event) {

            borderPane.setCenter(childAnchorPane);
    }

    @FXML
    void searchButtonClicked(MouseEvent event) {

        loadView("Views/search-view.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homeButton.setCursor(Cursor.HAND);
        searchButton.setCursor(Cursor.HAND);
        instagramPanelHBox.setCursor(Cursor.HAND);
        loadView("Views/search-view.fxml");
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
        SceneChanger.changeScenes(event, "Views/home-view.fxml","Home Page");
    }
}
