package com.example.assignment2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class SearchViewController implements Initializable {



    @FXML
    private TextField searchTextField;
    
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label userNameLabel;

    @FXML
    private VBox vBox;

    @FXML
    private GridPane userContainer;

    private int column=0;
    private int row=0;
    private User checkingUser;
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            userContainer.setVisible(false);
            scrollPane.setVisible(false);
    }

    @FXML
    void searchButtonClicked(ActionEvent event) throws IOException, InterruptedException {
            userContainer.setVisible(true);
           scrollPane.setVisible(true);

           String searchTerm = searchTextField.getText();

           APIResponse apiResponse = APIUtility.getUsersFromFile();


           for(User user : apiResponse.getUsers())
           {
               if (apiResponse.getTotalResults() >= row)
               {
                   checkingUser = new User(user.getPosition(), user.getUserID(), user.getUserName(), user.getFullName(), user.getIsPrivate(), user.getIsVerified(), user.getHasAnonymousProfilePicture(), user.getHasHighlightReels(), user.getProfilePicture());
                   System.out.println(user.getIsVerified());

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("UserCard.fxml"));
                   HBox userBox = fxmlLoader.load();
                   UserCardController userCardController = fxmlLoader.getController();
                   userCardController.setData(checkingUser);

             /*  if(column ==6){
                   column=0;
                   ++row;
               }*/

                   userContainer.add(userBox, 1, row + 1);
                   GridPane.setMargin(userBox, new Insets(10));
                   row++;
               }
           }

    }

}


