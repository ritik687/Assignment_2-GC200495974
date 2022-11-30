package com.example.assignment2.Controllers;

import com.example.assignment2.Interfaces.UserInitializable;
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


public class SearchViewController implements Initializable, UserInitializable {


    @FXML
    private TextField searchTextField;

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
    private HBox clearButtonHBox;


    @FXML
    private VBox centeredGraphicVBox;

    @FXML
    private Label msgLabel1;

    @FXML
    private Label msgLabel2;



    private ToggleGroup toggleGroup;

    private User checkedUser; // this object is for checking the validations set methods\\

    private String searchTerm;

    private APIResponse apiResponse;


    private MappingUserWithHBox mappingUserWithHBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        centeredGraphicVBox.setVisible(false);
        listView.setVisible(false);
        hBoxComment.setVisible(false);
        msgLabel1.setVisible(false);
        msgLabel2.setVisible(false);
        clearButtonHBox.setVisible(false);
        searchImageButton.setDisable(true);

        clearButtonHBox.setCursor(Cursor.HAND);
        homeImageButton.setCursor(Cursor.HAND);
        searchImageButton.setCursor(Cursor.HAND);
        listView.setCursor(Cursor.HAND);
        simpleListViewRadioButton.setCursor(Cursor.HAND);
        graphicListViewRadioButton.setCursor(Cursor.HAND);

        mappingUserWithHBox = new MappingUserWithHBox();


        //configuring the radio buttons
        toggleGroup = new ToggleGroup(); // this is very important
        simpleListViewRadioButton.setToggleGroup(toggleGroup);
        graphicListViewRadioButton.setToggleGroup(toggleGroup);
        radioButtonsHBox.setVisible(false);

        searchTextField.textProperty().addListener((observableValue, s, searchText) -> {
                    if(searchText.length()!=0){
                        searchImageButton.setDisable(false);
                        clearButtonHBox.setVisible(true);
                    }
                    else
                    {
                        searchImageButton.setDisable(true);
                        clearButtonHBox.setVisible(false);
                    }

        });


        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, user, selectedUser) -> {

            listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    try {
                        searchTerm = searchTextField.getText();
//                        User.addClickedUserFromUserCardBox(selectedUser);
                        SceneChanger.changeScenes(event, "Views/user-profile-details-view.fxml", selectedUser.getFullName()+"'s Profile", searchTerm, selectedUser);

//                        User.getClickedUserFromBothListViews().remove(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        });

    }



    @Override
    public void loadAllUsers(String passedSearchTerm) {

        if (passedSearchTerm != null) {
            searchTextField.setText(passedSearchTerm);

            APIResponse apiResponse = null;
            try {
                 APIUtility.getUsersFromSearchTerm(passedSearchTerm);
                 apiResponse =APIUtility.getUsersFromFile();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (toggleGroup.getSelectedToggle().equals(simpleListViewRadioButton)) {

                listView.getItems().clear();
                listView.getItems().addAll(apiResponse.getUsers());

                //setting visibility
                listView.setVisible(true);
                centeredGraphicVBox.setVisible(false);
            }
        }

    }

    @Override
    public void loadUserDetailsFromGraphicView(User passedUser) {

    }

    @Override
    public void loadUserDetailsFromListView(String passedSearchTerm, User passedUser) {

    }


                @FXML
                void searchButtonClicked(ActionEvent event) throws IOException, InterruptedException {

                    searchTerm = searchTextField.getText();

            //        APIResponse apiResponse = APIUtility.getDataFromFile();
//                    apiResponse = APIUtility.getUsersFromSearchTerm(searchTerm);
                    APIUtility.getUsersFromSearchTerm(searchTerm);
                    apiResponse = APIUtility.getUsersFromFile();

                    if(apiResponse.getIsError())
                    {
                        msgLabel1.setVisible(true);
                        msgLabel2.setVisible(true);
                        radioButtonsHBox.setVisible(false);

                        hBoxComment.setVisible(false);

                        centeredGraphicVBox.setVisible(false);
                        listView.getItems().clear();
                        userCardLayoutVBox.getChildren().clear();


                        listView.setVisible(false);
                        msgLabel2.setText("\""+searchTerm+"\"");
                    }

                    else
                    {
                        msgLabel1.setVisible(false);
                        msgLabel2.setVisible(false);

                        radioButtonsHBox.setVisible(true);
                        hBoxComment.setVisible(true);

                        simpleListViewRadioButton.setSelected(true);




                        listView.getItems().clear();
                        listView.getItems().addAll(apiResponse.getUsers());
                        listView.setVisible(true);
                    }


                }

                @FXML
                void radioCheck(ActionEvent event) throws IOException, InterruptedException {
                    searchTerm = searchTextField.getText();

            //        APIResponse apiResponse = APIUtility.getUsersFromSearchTerm(searchTerm);

                    if (toggleGroup.getSelectedToggle().equals(simpleListViewRadioButton)) {

                        listView.getItems().clear();
                        listView.getItems().addAll(apiResponse.getUsers());

                        //setting visibility
                        listView.setVisible(true);
                        centeredGraphicVBox.setVisible(false);
                    }

                    if (this.toggleGroup.getSelectedToggle().equals(this.graphicListViewRadioButton)) {

                        // clearing all the duplicate children again
                        userCardLayoutVBox.getChildren().clear();
                        for (User user : apiResponse.getUsers()) {
                                   /*if (apiResponse.getTotalResults() >= row)
                                   {*/

                            /*checkedUser = new User(user.getPosition(), user.getUserID(), user.getUserName(), user.getFullName(), user.getIsPrivate(), user.getIsVerified(), user.getHasAnonymousProfilePicture(), user.getHasHighlightReels(), user.getProfilePicture());*/


                            FXMLLoader fxmlLoader = new FXMLLoader();
                            //            fxmlLoader.setLocation(getClass().getResource("user-card-view.fxml"));
                            fxmlLoader.setLocation(Main.class.getResource("Views/user-card-view.fxml"));
                            HBox userCardBox = fxmlLoader.load();

                            mappingUserWithHBox.addUserAndHBoxInfo(userCardBox, user);

                            UserCardController userCardController = fxmlLoader.getController();
                            userCardController.setData(user);
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




                public void clickedAnyUserCardBoxInUserCardLayout() {


                    for (HBox userCardBox : mappingUserWithHBox.getAllUsersAndHBoxesInfo().keySet()) {
                        userCardBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                try {

                                    User.addClickedUserFromUserCardBox(mappingUserWithHBox.getAllUsersAndHBoxesInfo().get(userCardBox));
                                    System.out.println(User.getClickedUserFromBothListViews().size());

                                    SceneChanger.changeScenes(event, "Views/user-profile-details-view.fxml", User.getClickedUserFromBothListViews().get(0).getFullName()+"'s Profile", User.getClickedUserFromBothListViews().get(0));

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
                    SceneChanger.changeScenes(event, "Views/home-view.fxml", "Home Page");
                }

                @FXML
                void clearSearchTextField(MouseEvent event) {
                        searchTextField.clear();

                }


}



