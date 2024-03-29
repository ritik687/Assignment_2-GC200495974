package com.example.assignment2.Controllers;

import com.example.assignment2.Main;
import com.example.assignment2.Models.APIResponse;
import com.example.assignment2.Models.Media;
import com.example.assignment2.Models.Post;
import com.example.assignment2.Models.UserProfileDetails;
import com.example.assignment2.Utilities.APIUtility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MineProfileDetailsController implements Initializable{

    @FXML
    private Label bioLabel;

    @FXML
    private Label categoryNameLabel;

    @FXML
    private Circle circle;

    @FXML
    private Label followersLabel;

    @FXML
    private Label followingLabel;

    @FXML
    private Label fullNameLabel;

    @FXML
    private GridPane postsGrid;

    @FXML
    private Label postsLabel;

    @FXML
    private Label userNameLabel;

    @FXML
    private ImageView verifiedImageView;

    @FXML
    private ScrollPane scrollPane;


    private UserProfileDetails userProfileDetails;

    private int columns =0;
    private int rows =1;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            APIUtility.getUserProfileDetailsFromUserName("ritik_mall_");
            userProfileDetails =APIUtility.getUserProfileDetailsFromFile();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


        if(userProfileDetails.getMessage()!=null)
        {
                if (userProfileDetails.getMessage().equals("You are already used all your quota this hour, please request again in the next hour."))
                {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "You are already used all your limit for one hour that is 50 requests per hour, please make request again in the next hour.");
                    System.exit(0);
                }

                 if(userProfileDetails.getMessage().equals("Cannot destructure property 'cookie' of '(intermediate value)' as it is undefined."))
                {
                    scrollPane.setVisible(false);
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "\t\t\"Please try again to hit the Profile Button again or re run the program. There is some API connecting server side problem and the most possible reason is Image is reflecting null from the API\"");

                }
        }


        else
        {
                String profilePictureURL = userProfileDetails.getProfilePicture();
                String response = APIUtility.sendGETRequest(profilePictureURL);

                if (response != "Error") {
                    Image image = new Image(profilePictureURL);
                    circle.setFill(new ImagePattern(image));
                } else {
                    circle.setFill(new ImagePattern(new Image(Main.class.getResourceAsStream("images/noProfileImage.png"))));
                }

                // setting all the labels
                userNameLabel.setText(userProfileDetails.getUserName());
                fullNameLabel.setText(userProfileDetails.getFullName());
                followersLabel.setText(APIUtility.formatNumber(userProfileDetails.getFollowers()));
                followingLabel.setText(APIUtility.formatNumber(userProfileDetails.getFollowing()));
                postsLabel.setText(Integer.toString(userProfileDetails.getMedias().getTotalPosts()));
                categoryNameLabel.setText(userProfileDetails.getCategoryName());
                bioLabel.setText(userProfileDetails.getBioText());

                if (userProfileDetails.getIsVerifiedAccount()) {
                    verifiedImageView.setVisible(true);
                    verifiedImageView.setImage(new Image(Main.class.getResourceAsStream("images/verified.png")));
                } else {
                    verifiedImageView.setVisible(false);
                }


                for (Post post : userProfileDetails.getMedias().getPosts()) {

                    if (!post.getIsVideo()) {

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
                            GridPane.setMargin(postCardVBox, new Insets(20));


                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                scrollPane.setVisible(true);

            }



    }

}
