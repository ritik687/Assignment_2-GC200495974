package com.example.assignment2.Controllers;

import com.example.assignment2.Main;
import com.example.assignment2.Models.Media;
import com.example.assignment2.Models.Post;
import com.example.assignment2.Utilities.APIUtility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserPostsViewController implements Initializable, MediaInitializable {

    @FXML
    private Button backImageButton;

    @FXML
    private GridPane postsGrid;

    @FXML
    private Label userNameLabel;

    @FXML
    private ImageView verifiedImageView;

    private int columns = 0;
    private int rows = 1;


    @FXML
    void backButtonPressed(MouseEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    // using MediaInitializable that is the easiest way to pass object from on scene to another
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
}






