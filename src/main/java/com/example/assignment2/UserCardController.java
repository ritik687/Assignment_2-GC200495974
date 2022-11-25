package com.example.assignment2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserCardController {

    @FXML
    private Label userNameLabel;

    @FXML
    private Label fullNameLabel;

    @FXML
    private ImageView profileImageView;

    @FXML
    private ImageView verifiedImageView;





    public void setData(User user){

        userNameLabel.setText(user.getUserName());
        fullNameLabel.setText(user.getFullName());
        profileImageView.setImage(new Image(user.getProfilePicture()));
        System.out.println(user.getIsVerified());
        if(user.getIsVerified()) {
            verifiedImageView.setVisible(true);
            verifiedImageView.setImage(new Image(Main.class.getResourceAsStream("images/verified.png")));
        }
        else
            verifiedImageView.setVisible(false);

    }

}
