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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class UserProfileDetailsViewController implements Initializable, UserProfileDetailsInitializable, UserInitializable {

    @FXML
    private Circle circle;


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
    private Button viewImagesButton;

    @FXML
    private ImageView privacyImageView;

    @FXML
    private Label msgLabel;

    @FXML
    private VBox centeredVBox;

    private Media media;

    private UserProfileDetails userProfileDetails;

    private String searchTerm= null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        backImageButton.setCursor(Cursor.HAND);
        viewImagesButton.setCursor(Cursor.HAND);
        privacyImageView.setCursor(Cursor.HAND);
        msgLabel.setVisible(false);



    }


    /**
     *  loading user details if clicked from the simple list view
     */

    @Override
    public void loadUserDetailsFromListView(String passedSearchTerm, User passedUser) {


        try {
            APIUtility.getUserProfileDetailsFromUserName(passedUser.getUserName());
            userProfileDetails =APIUtility.getUserProfileDetailsFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        displayData(userProfileDetails,passedUser);

    }

    /**
     * Laoding user details if selected from the passedUser
     * @param passedUser
     */
    @Override
    public void loadUserDetailsFromGraphicView(User passedUser) {

        if(User.getClickedUserFromGrahicView().size()>0)
        {


            try {
                APIUtility.getUserProfileDetailsFromUserName(passedUser.getUserName());
                userProfileDetails =APIUtility.getUserProfileDetailsFromFile();
                displayData(userProfileDetails,passedUser);
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

        }
    }


    /**
     * This method will display the data with respect to which list view is selected
     * @param userProfileDetails
     * @param passedUser
     */
    private void displayData(UserProfileDetails userProfileDetails, User passedUser){



            if(userProfileDetails.getMessage()!=null)
            {
                if (userProfileDetails.getMessage().equals("You are already used all your quota this hour, please request again in the next hour.")) {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "You are already used all your limit for one hour that is 50 requests per hour, please make request again in the next hour.");
                    System.exit(0);
                }

                if (userProfileDetails.getMessage().equals("You have exceeded the rate limit per second for your plan, ULTRA, by the API provider")) {

                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "\t\t\"You  can only make one request per second or 50 requests per hour.\"\n\t\tSo, if you click search, try to click it once only and wait for second]");

                }

                if(userProfileDetails.getMessage().equals("Cannot destructure property 'cookie' of '(intermediate value)' as it is undefined."))
                {
                    centeredVBox.setVisible(false);
                    viewImagesButton.setVisible(false);
                    userNameLabel.setVisible(false);
                    verifiedImageView.setVisible(false);
                    privacyImageView.setVisible(false);
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "\t\t\"Please go back and try to select the user again in the list. Backend API error that is only due to the URL of the image.\"");

                    msgLabel.setVisible(true);

                }
            }

            else {
                String profilePictureURL = userProfileDetails.getProfilePicture();
                String response = APIUtility.sendGETRequest(profilePictureURL);

                if (passedUser.getHasAnonymousProfilePicture() != true) {
                    if (response != "Error") {
                        Image image = new Image(profilePictureURL);
                        circle.setFill(new ImagePattern(image));
                    } else {
                        circle.setFill(new ImagePattern(new Image(Main.class.getResourceAsStream("images/noProfileImage.png"))));
                    }
                } else {
                    circle.setFill(new ImagePattern(new Image(Main.class.getResourceAsStream("images/noProfileImage.png"))));
                }


                userNameLabel.setText(passedUser.getUserName());

                userNameLabel.setText(userProfileDetails.getUserName());
                fullNameLabel.setText(userProfileDetails.getFullName());

                followersLabel.setText(APIUtility.formatNumber(userProfileDetails.getFollowers()));
                followingLabel.setText(APIUtility.formatNumber(userProfileDetails.getFollowing()));

                categoryLabel.setText(userProfileDetails.getCategoryName());
                postsLabel.setText(Integer.toString(userProfileDetails.getMedias().getTotalPosts()));


                textAreaForBio.setText(userProfileDetails.getBioText());
                textAreaForBio.setBorder(null);


                if (userProfileDetails.getIsPrivateAccount()) {
                    privacyImageView.setImage(new Image(Main.class.getResourceAsStream("images/lock2.png")));
                    privacyImageView.setVisible(true);

                } else {
                    privacyImageView.setImage(new Image(Main.class.getResourceAsStream("images/unlock.png")));
                    privacyImageView.setVisible(true);
                }


                if (userProfileDetails.getIsVerifiedAccount()) {
                    verifiedImageView.setVisible(true);
                } else {
                    verifiedImageView.setVisible(false);
                }


                centeredVBox.setVisible(true);
                viewImagesButton.setVisible(true);
                userNameLabel.setVisible(true);
            }

    }


    /**
     * This method will work when back button pressed from the post page and display the same data
     */

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


    /**
     * This method will view posts and the change the scene to users-post-view.fxml
     * @param event
     * @throws IOException
     * @throws InterruptedException
     */
    @FXML
    void viewPostsButtonClicked(MouseEvent event) throws IOException, InterruptedException {

        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, " Images takes little time to load :) ");
        media = userProfileDetails.getMedias();

        SceneChanger.changeScenes(event,"Views/user-posts-view.fxml",userProfileDetails.getFullName()+"'s Images",media,userProfileDetails);
    }


    /**
     * This method will take back to the search page
     * @param event
     * @throws IOException
     */
    @FXML
    void backButtonPressed(MouseEvent event) throws IOException {
        SceneChanger.changeScenes(event, "Views/search-view.fxml","Search Page");

    }


    /**
     * This method will check whether the user profile is private or public.
     * @param event
     */
    @FXML
    void privacyImageViewClicked(MouseEvent event) {
            if(userProfileDetails.getIsPrivateAccount()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("This account is private.");
                alert.show();
            }

            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("This is Public Profile.");
                alert.show();
            }
    }
}
