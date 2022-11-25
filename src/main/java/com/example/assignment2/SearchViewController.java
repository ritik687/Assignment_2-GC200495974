package com.example.assignment2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    private VBox userCardLayout;

   /* private int column=0;
    private int row=0;*/
    private User checkingUser;

    private ArrayList<HBox> userCardHBoxes =new ArrayList<>();
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            userCardLayout.setVisible(false);
            scrollPane.setVisible(false);
    }

    @FXML
    void searchButtonClicked(MouseEvent event) throws IOException, InterruptedException {
            userCardLayout.setVisible(true);
           scrollPane.setVisible(true);

           String searchTerm = searchTextField.getText();

           APIResponse apiResponse = APIUtility.getUsersFromFile();


           for(User user : apiResponse.getUsers())
           {
               /*if (apiResponse.getTotalResults() >= row)
               {*/
                   checkingUser = new User(user.getPosition(), user.getUserID(), user.getUserName(), user.getFullName(), user.getIsPrivate(), user.getIsVerified(), user.getHasAnonymousProfilePicture(), user.getHasHighlightReels(), user.getProfilePicture());



                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("user-card-view.fxml"));
                   HBox userCardBox = fxmlLoader.load();
                    userCardHBoxes.add(userCardBox);

                   UserCardController userCardController = fxmlLoader.getController();
                   userCardController.setData(checkingUser);
                   /*userContainer.add(userBox, 1, row + 1);
                   GridPane.setMargin(userBox, new Insets(10));
                   row++;
*/
               }

                userCardLayout.getChildren().addAll(userCardHBoxes);



           }




    }

//    }


