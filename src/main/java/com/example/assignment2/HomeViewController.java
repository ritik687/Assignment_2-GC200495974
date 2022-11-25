package com.example.assignment2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private BorderPane borderPane = null;

    @FXML
    void homeButtonClicked(MouseEvent event) {

            borderPane.setCenter(anchorPane);
    }

    @FXML
    void searchButtonClicked(MouseEvent event) {

        loadView("search-view.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    private void loadView(String resourceName){
        Parent root = null;

        try {
            root= FXMLLoader.load(getClass().getResource(resourceName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(root);

    }
}
