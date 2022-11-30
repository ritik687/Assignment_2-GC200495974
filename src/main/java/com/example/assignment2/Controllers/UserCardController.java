package com.example.assignment2.Controllers;

import com.example.assignment2.Main;
import com.example.assignment2.Models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class UserCardController implements Initializable {

    @FXML
    private Label userNameLabel;

    @FXML
    private Label fullNameLabel;

    @FXML
    private ImageView profileImageView;

    @FXML
    private ImageView verifiedImageView;

    @FXML
    private HBox hBox;


//    private ArrayList<User> clickedUser;

    //"ddf542","42adf5","f5aa42","ff5056","bdb2fe","b9e5ff"
    private String[] colors;


    public void setData(User user) throws IOException {

        userNameLabel.setText(user.getUserName());
        fullNameLabel.setText(user.getFullName());



        String profilePicture= user.getProfilePicture();

        /*if(user.getHasAnonymousProfilePicture())
            profileImageView.setImage(new Image(Main.class.getResourceAsStream("images/noProfileImage.png")));
        else*/
        profileImageView.setImage(new Image(profilePicture));



        // checking whether the user is verified if yes then display the verified logo
        if(user.getIsVerified()) {
            verifiedImageView.setVisible(true);
            verifiedImageView.setImage(new Image(Main.class.getResourceAsStream("images/verified.png")));
        }
        else {
            verifiedImageView.setVisible(false);
        }

        hBox.setStyle("-fx-background-color: "+colors[(int)(Math.random()*colors.length)]);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colors = new String[]{"#f6f509","#f7c59d","#bfa5f7","#b9c5f9","#ff7b78","#6c7293","#ACDDDE","#CAF1DE","#FEF8DD","#F7D8BA"};

        hBox.setCursor(Cursor.HAND);

    }





}

