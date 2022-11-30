package com.example.assignment2.Controllers;


import com.example.assignment2.Main;
import com.example.assignment2.Models.Post;
import com.example.assignment2.Models.User;
import com.example.assignment2.Utilities.APIUtility;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;


import java.net.URL;
import java.util.ResourceBundle;

public class PostCardController implements Initializable {

    @FXML
    private Label commentsLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Label likesLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setData(Post post){

        commentsLabel.setText(APIUtility.formatNumber(post.getTotalComments()));
        likesLabel.setText(APIUtility.formatNumber(post.getTotalLikes()));

        String response = APIUtility.sendGETRequest(post.getPicture());
        if(!post.getIsVideo())
        {
            if (response != "Error")
                imageView.setImage(new Image(post.getPicture()));
            else
                imageView.setImage(new Image(Main.class.getResourceAsStream("images/noProfileImage.png")));
        }



    }
}
