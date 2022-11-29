package com.example.assignment2.Controllers;

import com.example.assignment2.Main;
import com.example.assignment2.Models.APIResponse;
import com.example.assignment2.Models.MappingUserWithHBox;
import com.example.assignment2.Models.User;
import com.example.assignment2.Utilities.APIUtility;
import com.example.assignment2.Utilities.SceneChanger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class SearchViewController implements Initializable {


    @FXML
    private TextField searchTextField;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private HBox hBoxComment;

    @FXML
    private VBox userCardLayoutVBox;

    @FXML
    private Button homeImageButton;

    @FXML
    private Button searchImageButton;

    @FXML
    private RadioButton graphicListViewRadioButton;

    @FXML
    private RadioButton simpleListViewRadioButton;

    @FXML
    private ListView<User> listView;

    @FXML
    private HBox radioButtonsHBox;


    @FXML
    private VBox centeredGraphicVBox;

    private ToggleGroup toggleGroup;

    private User checkedUser; // this object is for checking the validations set methods


    private MappingUserWithHBox mappingUserWithHBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        centeredGraphicVBox.setVisible(false);
        listView.setVisible(false);

        hBoxComment.setVisible(false);

        homeImageButton.setCursor(Cursor.HAND);
        searchImageButton.setCursor(Cursor.HAND);
        listView.setCursor(Cursor.HAND);
        simpleListViewRadioButton.setCursor(Cursor.HAND);
        graphicListViewRadioButton.setCursor(Cursor.HAND);

        mappingUserWithHBox =new MappingUserWithHBox();


        //configuring the radio buttons
        toggleGroup =new ToggleGroup(); // this is very important
        simpleListViewRadioButton.setToggleGroup(toggleGroup);
        graphicListViewRadioButton.setToggleGroup(toggleGroup);
        radioButtonsHBox.setVisible(false);


        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, user, selectedUser) -> {

            listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        User.addClickedUserFromUserCardBox(selectedUser);
                        SceneChanger.changeScenes(event, "Views/user-more-detail-view.fxml", "User Profile");
                        User.getClickedUserFromBothListViews().remove(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        });



    }



    @FXML
    void radioCheck(ActionEvent event) throws IOException, InterruptedException {
        String searchTerm = searchTextField.getText();

        APIResponse apiResponse = APIUtility.getDataFromFile();

         if(toggleGroup.getSelectedToggle().equals(simpleListViewRadioButton)){

                 listView.getItems().clear();
                 listView.getItems().addAll(apiResponse.getUsers());

                 //setting visibility
                 listView.setVisible(true);
                 centeredGraphicVBox.setVisible(false);
         }

         if(this.toggleGroup.getSelectedToggle().equals(this.graphicListViewRadioButton)){

                     for (User user : apiResponse.getUsers()) {
                       /*if (apiResponse.getTotalResults() >= row)
                       {*/

                         checkedUser = new User(user.getPosition(), user.getUserID(), user.getUserName(), user.getFullName(), user.getIsPrivate(), user.getIsVerified(), user.getHasAnonymousProfilePicture(), user.getHasHighlightReels(), user.getProfilePicture());


                         FXMLLoader fxmlLoader = new FXMLLoader();
        //            fxmlLoader.setLocation(getClass().getResource("user-card-view.fxml"));
                         fxmlLoader.setLocation(Main.class.getResource("Views/user-card-view.fxml"));
                         HBox userCardBox = fxmlLoader.load();

                         mappingUserWithHBox.addUserAndHBoxInfo(userCardBox, checkedUser);

                         UserCardController userCardController = fxmlLoader.getController();
                         userCardController.setData(checkedUser);
                           /*userContainer.add(userBox, 1, row + 1);
                           GridPane.setMargin(userBox, new Insets(10));
                           row++; */

                     }


                     userCardLayoutVBox.getChildren().addAll(mappingUserWithHBox.getAllUsersAndHBoxesInfo().keySet());

                     clickedAnyUserCardBoxInUserCardLayout();


                     //setting visibility
                     centeredGraphicVBox.setVisible(true);
                     listView.setVisible(false);
         }

    }




    @FXML
    void searchButtonClicked(ActionEvent event) throws IOException, InterruptedException {

        radioButtonsHBox.setVisible(true);
        simpleListViewRadioButton.setSelected(true);

        hBoxComment.setVisible(true);



//        userCardLayoutVBox.setVisible(true);
//        scrollPane.setVisible(true);

        String searchTerm = searchTextField.getText();

        APIResponse apiResponse = APIUtility.getDataFromFile();

        listView.getItems().clear();

        listView.getItems().addAll(apiResponse.getUsers());

        listView.setVisible(true);


    }

    public void clickedAnyUserCardBoxInUserCardLayout()
    {


        for(HBox userCardBox: mappingUserWithHBox.getAllUsersAndHBoxesInfo().keySet())
        {
            userCardBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {

                        User.addClickedUserFromUserCardBox(mappingUserWithHBox.getAllUsersAndHBoxesInfo().get(userCardBox));
                        System.out.println(User.getClickedUserFromBothListViews().size());

                        SceneChanger.changeScenes(event, "Views/user-more-detail-view.fxml", "Profile Page");
                        User.getClickedUserFromBothListViews().remove(0);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });
        }

    }




    @FXML
    void homeButtonClicked(MouseEvent event) throws IOException {
            SceneChanger.changeScenes(event,"Views/home-view.fxml","Home Page");
    }



}



