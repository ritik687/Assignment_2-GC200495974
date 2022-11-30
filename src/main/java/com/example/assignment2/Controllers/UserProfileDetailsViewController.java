package com.example.assignment2.Controllers;

import com.example.assignment2.*;
import com.example.assignment2.Interfaces.UserInitializable;
import com.example.assignment2.Interfaces.UserProfileDetailsInitializable;
import com.example.assignment2.Models.Media;
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
import java.util.ResourceBundle;



public class UserProfileDetailsViewController implements Initializable, UserProfileDetailsInitializable, UserInitializable {

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

    @FXML
    private Button viewPostsButton;

    private Media media;

    private UserProfileDetails userProfileDetails;

//    private User user;

    private String searchTerm= null;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        backImageButton.setCursor(Cursor.HAND);
        viewPostsButton.setCursor(Cursor.HAND);




//        try {
//            loadProfileDetails();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }



    }


    // loading details with the help of graphic list view
    /*void loadProfileDetails() throws IOException, InterruptedException
    {




    }*/






    @FXML
    void viewPostsButtonClicked(MouseEvent event) throws IOException, InterruptedException {
//        userProfileDetails= APIUtility.getUserProfileDetailsFromFile();
        media = userProfileDetails.getMedias();

        SceneChanger.changeScenes(event,"Views/user-posts-view.fxml","Posts",media,userProfileDetails);
    }




    // this is when back button pressed from the post view
    @Override
    public void loadUserProfileDetails(UserProfileDetails passedUserProfileDetail) {

        userProfileDetails = passedUserProfileDetail;
        if(passedUserProfileDetail!=null)
        {
            String response = APIUtility.sendGETRequest(passedUserProfileDetail.getProfilePicture());

            /*if (passedUserProfileDetail.getHasAnonymousProfilePicture() != true) {*/
            if (response != "Error") {
                Image image = new Image(passedUserProfileDetail.getProfilePicture());
                circle.setFill(new ImagePattern(image));
            } else {
                circle.setFill(new ImagePattern(new Image(Main.class.getResourceAsStream("images/noProfileImage.png"))));
            }
         /*   } else {
                circle.setFill(new ImagePattern(new Image(Main.class.getResourceAsStream("images/noProfileImage.png"))));
            }*/

            userNameLabel.setText(passedUserProfileDetail.getUserName());
            fullNameLabel.setText(passedUserProfileDetail.getFullName());
            followersLabel.setText(APIUtility.formatNumber(passedUserProfileDetail.getFollowers()));
            followingLabel.setText(APIUtility.formatNumber(passedUserProfileDetail.getFollowing()));
            categoryLabel.setText(passedUserProfileDetail.getCategoryName());
            postsLabel.setText(Integer.toString(passedUserProfileDetail.getMedias().getTotalPosts()));


            textAreaForBio.setText(passedUserProfileDetail.getBioText());
            textAreaForBio.setBorder(null);


            if (passedUserProfileDetail.getIsVerifiedAccount()) {
                verifiedImageView.setVisible(true);
                verifiedImageView.setImage(new Image(Main.class.getResourceAsStream("images/verified.png")));
            } else {
                verifiedImageView.setVisible(false);
            }
        }





    }



    // loading user details if clicked from the simple list view
    @Override
    public void loadUserDetailsFromListView(String passedSearchTerm, User passedUser) {


//        UserProfileDetails userProfileDetails = null;
        try {
            userProfileDetails = APIUtility.getUserProfileDetailsFromUserName(passedUser.getUserName());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String profilePictureURL = userProfileDetails.getProfilePicture();
        String response = APIUtility.sendGETRequest(profilePictureURL);
        System.out.println(response);

        if (passedUser.getHasAnonymousProfilePicture() != true) {
            if (response != "Error")
            {
                Image image = new Image(profilePictureURL);
                circle.setFill(new ImagePattern(image));
            }
            else
            {
                circle.setFill(new ImagePattern(new Image(Main.class.getResourceAsStream("images/noProfileImage.png"))));
            }
        } else {
            circle.setFill(new ImagePattern(new Image(Main.class.getResourceAsStream("images/noProfileImage.png"))));
        }

        userNameLabel.setText(userProfileDetails.getUserName());
        fullNameLabel.setText(userProfileDetails.getFullName());
        followersLabel.setText(APIUtility.formatNumber(userProfileDetails.getFollowers()));
        followingLabel.setText(APIUtility.formatNumber(userProfileDetails.getFollowing()));
        categoryLabel.setText(userProfileDetails.getCategoryName());
        postsLabel.setText(Integer.toString(userProfileDetails.getMedias().getTotalPosts()));


        textAreaForBio.setText(userProfileDetails.getBioText());
        textAreaForBio.setBorder(null);


        if (userProfileDetails.getIsVerifiedAccount()) {
            verifiedImageView.setVisible(true);
            verifiedImageView.setImage(new Image(Main.class.getResourceAsStream("images/verified.png")));
        } else {
            verifiedImageView.setVisible(false);
        }



    }


    @Override
    public void loadAllUsers(String passedSearchTerm) {
            searchTerm =passedSearchTerm;

    }

    @Override
    public void loadUserDetailsFromGraphicView(User passedUser) {

        if(User.getClickedUserFromBothListViews().size()>0)
        {

            try {
                userProfileDetails = APIUtility.getUserProfileDetailsFromUserName(passedUser.getUserName());
//                System.out.println(userProfileDetails);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            String profilePictureURL = userProfileDetails.getProfilePicture();
            String response = APIUtility.sendGETRequest(profilePictureURL);

            if (User.getClickedUserFromBothListViews().get(0).getHasAnonymousProfilePicture() != true) {
                if (response != "Error") {
                    Image image = new Image(profilePictureURL);
                    circle.setFill(new ImagePattern(image));
                } else {
                    circle.setFill(new ImagePattern(new Image(Main.class.getResourceAsStream("images/noProfileImage.png"))));
                }
            } else {
                circle.setFill(new ImagePattern(new Image(Main.class.getResourceAsStream("images/noProfileImage.png"))));
            }


            userNameLabel.setText(User.getClickedUserFromBothListViews().get(0).getUserName());

            userNameLabel.setText(userProfileDetails.getUserName());
            fullNameLabel.setText(userProfileDetails.getFullName());
            followersLabel.setText(APIUtility.formatNumber(userProfileDetails.getFollowers()));
            followingLabel.setText(APIUtility.formatNumber(userProfileDetails.getFollowing()));
            categoryLabel.setText(userProfileDetails.getCategoryName());
            postsLabel.setText(Integer.toString(userProfileDetails.getMedias().getTotalPosts()));


            textAreaForBio.setText(userProfileDetails.getBioText());
            textAreaForBio.setBorder(null);


            if (userProfileDetails.getIsVerifiedAccount()) {
                verifiedImageView.setVisible(true);
                verifiedImageView.setImage(new Image(Main.class.getResourceAsStream("images/verified.png")));
            } else {
                verifiedImageView.setVisible(false);
            }

        }
    }

    @FXML
    void backButtonPressed(MouseEvent event) throws IOException {
        SceneChanger.changeScenes(event, "Views/search-view.fxml","Search Page",searchTerm);

    }
}
