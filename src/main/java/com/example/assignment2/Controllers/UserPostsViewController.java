package com.example.assignment2.Controllers;

import com.example.assignment2.Interfaces.MediaInitializable;
import com.example.assignment2.Interfaces.UserProfileDetailsInitializable;
import com.example.assignment2.Main;
import com.example.assignment2.Models.Media;
import com.example.assignment2.Models.Post;
import com.example.assignment2.Models.UserProfileDetails;
import com.example.assignment2.Utilities.APIUtility;
import com.example.assignment2.Utilities.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserPostsViewController implements Initializable, MediaInitializable, UserProfileDetailsInitializable {

    @FXML
    private Button backImageButton;

    @FXML
    private GridPane postsGrid;

    @FXML
    private Label fullNameLabel;

    @FXML
    private HBox homeButtonHBox;

    @FXML
    private HBox searchButtonHBox;

    @FXML
    private ImageView verifiedImageView;

    @FXML
    private Circle imageCircle;

    private int columns = 0;
    private int rows = 1;

    private UserProfileDetails duplicateUserProfileDetail;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
                verifiedImageView.setVisible(false);

                backImageButton.setCursor(Cursor.HAND);
                homeButtonHBox.setCursor(Cursor.HAND);
                searchButtonHBox.setCursor(Cursor.HAND);



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
    }


    // using MediaInitializable that is the easiest way to pass object from on scene to another.. this will works as a initialize method.
    @Override
    public void loadMediaDetails(Media media) {


        for (Post post : media.getPosts()) {

            if (!post.getIsVideo())
            {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("Views/post-card-view.fxml"));

                try {
                    VBox postCardVBox = fxmlLoader.load();
                    PostCardController postCardController = fxmlLoader.getController();
                    postCardController.setData(post);

                    if (columns == 3) {
                        columns = 0;
                        ++rows;
                    }

                    postsGrid.add(postCardVBox, columns++, rows);
                    GridPane.setMargin(postCardVBox, new Insets(30));


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void loadUserProfileDetails(UserProfileDetails userProfileDetail) {

        duplicateUserProfileDetail = userProfileDetail;
         fullNameLabel.setText(userProfileDetail.getFullName());

        if (userProfileDetail.getIsVerifiedAccount()) {
            verifiedImageView.setVisible(true);
            verifiedImageView.setImage(new Image(Main.class.getResourceAsStream("images/verified.png")));
        } else {
            verifiedImageView.setVisible(false);
        }
    }

    @FXML
    void backButtonPressed(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "Views/user-profile-details-view.fxml",duplicateUserProfileDetail.getFullName()+"'s Profile",duplicateUserProfileDetail);
    }

    @FXML
    void homeButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Calling API and displaying Ram's Profile. Please wait a little...:)");
        SceneChanger.changeScenes(event,"Views/home-view.fxml","Ram's Profile");
    }

    @FXML
    void searchButtonClicked(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "Views/search-view.fxml", "Search Page");
    }
}






