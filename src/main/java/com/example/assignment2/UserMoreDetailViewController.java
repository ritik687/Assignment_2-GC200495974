package com.example.assignment2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserMoreDetailViewController implements Initializable {

    @FXML
    private Circle circle;


    @FXML
    private Label PostsLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label followersLabel;

    @FXML
    private Label follwingLabel;

    @FXML
    private Label fullNameLabel;

    @FXML
    private TextArea textAreaForBio;

    @FXML
    private Label userNameLabel;

    @FXML
    private ImageView verifiedImageView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            Image image =new Image(User.getClickedUserFromUserCardBox().get(0).getProfilePicture());
            circle.setFill(new ImagePattern(image));

            userNameLabel.setText(User.getClickedUserFromUserCardBox().get(0).getUserName());

        if(User.getClickedUserFromUserCardBox().get(0).getIsVerified()) {
            verifiedImageView.setVisible(true);
            verifiedImageView.setImage(new Image(Main.class.getResourceAsStream("images/verified.png")));
        }
        else {
            verifiedImageView.setVisible(false);
        }
        fullNameLabel.setText(User.getClickedUserFromUserCardBox().get(0).getFullName());

    }

    @FXML
    void backButtonPressed(MouseEvent event) throws IOException {
            SceneChanger.changeScenes(event, "search-view.fxml","Search Anything");
    }

}
