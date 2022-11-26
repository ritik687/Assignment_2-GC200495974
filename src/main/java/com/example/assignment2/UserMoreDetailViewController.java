package com.example.assignment2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserMoreDetailViewController implements Initializable {

    @FXML
    private Circle circle;

    private User clickedUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            Image image =new Image(User.getClickedUserFromUserCardBox().get(0).getProfilePicture());
            circle.setFill(new ImagePattern(image));
    }

    @FXML
    void backButtonPressed(MouseEvent event) throws IOException {
            SceneChanger.changeScenes(event, "search-view.fxml","Search Anything");
    }

}
