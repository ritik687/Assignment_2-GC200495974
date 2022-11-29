package com.example.assignment2.Controllers;

import com.example.assignment2.Main;
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

public class UserPostsViewController implements Initializable {

    @FXML
    private Button backImageButton;

    @FXML
    private GridPane postsGrid;

    @FXML
    private Label userNameLabel;

    @FXML
    private ImageView verifiedImageView;

    @FXML
    void backButtonPressed(MouseEvent event) {

    }

    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int columns =0;
        int rows =1;

        for(int i=0;i<10;i++){
            FXMLLoader fxmlLoader =new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("Views/post-card-view.fxml"));

            try {
                VBox postCardVBox =fxmlLoader.load();
                PostCardViewController postCardViewController =fxmlLoader.getController();
                  postCardViewController.setData();

                if(columns == 3)
                {
                    columns =0;
                    ++rows;
                }

                postsGrid.add(postCardVBox,columns++, rows);
                GridPane.setMargin(postCardVBox,new Insets(30));


            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
