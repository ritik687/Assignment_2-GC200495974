package com.example.assignment2.Controllers;


import com.example.assignment2.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.ResourceBundle;

public class PostCardViewController implements Initializable {

    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setData(){
        imageView.setImage(new Image(Main.class.getResourceAsStream("images/home.png")));
    }
}
