package com.example.assignment2.Controllers;

import com.example.assignment2.*;
import com.example.assignment2.Models.User;
import com.example.assignment2.Models.UserProfileDetails;
import com.example.assignment2.Utilities.APIUtility;
import com.example.assignment2.Utilities.SceneChanger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
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
    private Label followingLabel;

    @FXML
    private Label fullNameLabel;

    @FXML
    private TextArea textAreaForBio;

    @FXML
    private Label userNameLabel;

    @FXML
    private ImageView verifiedImageView;

    @FXML
    private Button backImageButton;


    @FXML
    private Label postsLabel;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        backImageButton.setCursor(Cursor.HAND);


       /* Image image =new Image(User.getClickedUserFromUserCardBox().get(0).getProfilePicture());
            circle.setFill(new ImagePattern(image));

            userNameLabel.setText(User.getClickedUserFromUserCardBox().get(0).getUserName());

        if(User.getClickedUserFromUserCardBox().get(0).getIsVerified()) {
            verifiedImageView.setVisible(true);
            verifiedImageView.setImage(new Image(Main.class.getResourceAsStream("images/verified.png")));
        }
        else {
            verifiedImageView.setVisible(false);
        }
        fullNameLabel.setText(User.getClickedUserFromUserCardBox().get(0).getFullName());*/
        try {
            loadProfileDetails();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void backButtonPressed(MouseEvent event) throws IOException {
            SceneChanger.changeScenes(event, "Views/search-view.fxml","Search");
    }

    void loadProfileDetails() throws IOException, InterruptedException
    {
        UserProfileDetails userProfileDetails = APIUtility.getUserProfileDetailsFromFile();

        String profilePictureURL = User.getClickedUserFromUserCardBox().get(0).getProfilePicture();
        String response =APIUtility.sendGETRequest(profilePictureURL);

        if(User.getClickedUserFromUserCardBox().get(0).getHasAnonymousProfilePicture()!=true) {
            if (response != "Error") {
                Image image = new Image(profilePictureURL);
                circle.setFill(new ImagePattern(image));
            } else {
                circle.setFill(new ImagePattern(new Image(Main.class.getResourceAsStream("images/noProfileImage.png"))));
            }
        }
        else{
            circle.setFill(new ImagePattern(new Image(Main.class.getResourceAsStream("images/noProfileImage.png"))));
        }

        userNameLabel.setText(User.getClickedUserFromUserCardBox().get(0).getUserName());

        userNameLabel.setText(userProfileDetails.getUserName());
        fullNameLabel.setText(userProfileDetails.getFullName());
        followersLabel.setText(format(userProfileDetails.getFollowers()));
        followingLabel.setText(format(userProfileDetails.getFollowing()));
        categoryLabel.setText(userProfileDetails.getCategoryName());
        postsLabel.setText(Integer.toString(userProfileDetails.getMedias().getTotalPosts()));


        textAreaForBio.setText(userProfileDetails.getBioText());
        textAreaForBio.setBorder(null);
        

        if(userProfileDetails.getIsVerifiedAccount()) {
            verifiedImageView.setVisible(true);
            verifiedImageView.setImage(new Image(Main.class.getResourceAsStream("images/verified.png")));
        }
        else {
            verifiedImageView.setVisible(false);
        }


    }

    private static String[] suffix = new String[]{"","K", "M", "B", "T"};
    private static int MAX_LENGTH = 4;

    private static String format(double number) {
        String r = new DecimalFormat("##0E0").format(number);
        r = r.replaceAll("E[0-9]", suffix[Character.getNumericValue(r.charAt(r.length() - 1)) / 3]);
        while(r.length() > MAX_LENGTH || r.matches("[0-9]+\\.[a-z]")){
            r = r.substring(0, r.length()-2) + r.substring(r.length() - 1);
        }
        return r;
    }

}
