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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;


public class SearchViewController implements Initializable {


    @FXML
    private TextField searchTextField;



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
    private RadioButton tableViewRadioButton;

    @FXML
    private ListView<User> listView;

    @FXML
    private HBox radioButtonsHBox;

    @FXML
    private HBox clearButtonHBox;

    @FXML
    private VBox centeredGraphicVBox;

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User,String> userIDColumn;

    @FXML
    private TableColumn<User, String> userNameColumn;

    @FXML
    private TableColumn<User, String> fullNameColumn;

    @FXML
    private TableColumn<User,String> isPrivateColumn;

    @FXML
    private TableColumn<User, String> isVerifiedColumn;

    @FXML
    private TableColumn<User, ImageView> profileImageColumn;



    private ToggleGroup toggleGroup;

    private User checkedUser; // this object is for checking the validations set methods\\

    private static String searchTerm;
    private static ArrayList<User> savingUsers = new ArrayList<>();

    private static APIResponse apiResponse;

    private MappingUserWithHBox mappingUserWithHBox;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        centeredGraphicVBox.setVisible(false);
        listView.setVisible(false);
        tableView.setVisible(false);

        clearButtonHBox.setVisible(false);
        searchImageButton.setDisable(true);

        clearButtonHBox.setCursor(Cursor.HAND);
        homeImageButton.setCursor(Cursor.HAND);
        searchImageButton.setCursor(Cursor.HAND);
        listView.setCursor(Cursor.HAND);
        simpleListViewRadioButton.setCursor(Cursor.HAND);
        graphicListViewRadioButton.setCursor(Cursor.HAND);
        tableViewRadioButton.setCursor(Cursor.HAND);
        tableView.setCursor(Cursor.HAND);

        mappingUserWithHBox = new MappingUserWithHBox();


        //configuring the radio buttons
        toggleGroup = new ToggleGroup(); // this is very important
        simpleListViewRadioButton.setToggleGroup(toggleGroup);
        graphicListViewRadioButton.setToggleGroup(toggleGroup);
        tableViewRadioButton.setToggleGroup(toggleGroup);
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

                        radioButtonsHBox.setVisible(false);

                        listView.setVisible(false);
                    }

        });


        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, user, selectedUser) -> {

            listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    try {
                        searchTerm = searchTextField.getText();

                            SceneChanger.changeScenes(event, "Views/user-profile-details-view.fxml", selectedUser.getFullName() + "'s Profile", searchTerm, selectedUser);



                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        });


        tableView.getSelectionModel().selectedItemProperty().addListener((observableValue, old, selectedUser) -> {

            tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {



                    try {
                        searchTerm = searchTextField.getText();
                        SceneChanger.changeScenes(event, "Views/user-profile-details-view.fxml", selectedUser.getFullName() + "'s Profile", searchTerm, selectedUser);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        });



        // retaining back all users if the user pressed the back button profile view
        retainAllDataBack();
    }

    /**
     * This method will get back all the users in the list and the text of the search bar when the back button is pressed from the user profile detail side.
     */
    void retainAllDataBack(){
        if(searchTerm != null){


            searchTextField.setText(searchTerm);
            listView.getItems().addAll(savingUsers);

            Collections.sort(listView.getItems());

            listView.setVisible(true);
            radioButtonsHBox.setVisible(true);
            simpleListViewRadioButton.setSelected(true);
        }

    }


    /**
     * This method will make the changes possible when the search button is clicked
     * @param event
     * @throws IOException
     * @throws InterruptedException
     */
    @FXML
    void searchButtonClicked(ActionEvent event) throws IOException, InterruptedException
                {

                    searchTerm = searchTextField.getText();


                    APIUtility.getUsersFromSearchTerm(searchTerm);
                    apiResponse = APIUtility.getUsersFromFile();


                    /**
                     * Here i have some api error handling that we get while connecting with api.
                     */

                    if(apiResponse.getMessage()!=null)
                            {
                                    if(apiResponse.getMessage().equals("You are already used all your quota this hour, please request again in the next hour."))
                                    {
                                        JFrame frame = new JFrame();
                                        JOptionPane.showMessageDialog(frame, "You are already used all your limit for one hour that is 50 requests per hour, please make request again in the next hour.");
                                        System.exit(0);
                                    }

                                    if(apiResponse.getMessage().equals("You have exceeded the rate limit per second for your plan, ULTRA, by the API provider"))
                                    {

                                        JFrame frame = new JFrame();
                                        JOptionPane.showMessageDialog(frame, "\t\t\"You  can only make one request per second or 50 requests per hour.\"\n\t\tSo, if you click search, try to click it once only and wait for second]");


                                    }


                                    if(apiResponse.getMessage().equals("Sorry cant find that :("))
                                    {

                                        radioButtonsHBox.setVisible(false);
                                        centeredGraphicVBox.setVisible(false);
                                        listView.getItems().clear();
                                        userCardLayoutVBox.getChildren().clear();


                                        listView.setVisible(false);

                                        JFrame frame = new JFrame();
                                        JOptionPane.showMessageDialog(frame, "Please try to hit the search again. There is back end error on the server side from where API is called. If stills, you did not get the result then, there are no results found for "+"\""+apiResponse.getSearchedUser()+"\"");
                                    }


                                 }


                            else
                            {
                                radioButtonsHBox.setVisible(true);
                                simpleListViewRadioButton.setSelected(true);



                                    try {
                                        savingUsers.removeAll(savingUsers);

                                        listView.getItems().clear();




                                        listView.getItems().addAll(apiResponse.getUsers());

                                        savingUsers.addAll(listView.getItems());

                                        Collections.sort(listView.getItems());
                                        listView.setVisible(true);
                                    }
                                    catch (Exception e)
                                    {
                                        System.out.println(e.getMessage());
                                    }

                            }


                }


    /**
     * This method will work accordingly with the radio button selected.
     * @param event
     * @throws IOException
     * @throws InterruptedException
     */
    @FXML
    void radioCheck(ActionEvent event) throws IOException, InterruptedException {
                    searchTerm = searchTextField.getText();

            //        APIResponse apiResponse = APIUtility.getUsersFromSearchTerm(searchTerm);

                    if (toggleGroup.getSelectedToggle().equals(simpleListViewRadioButton)) {

                        listView.getItems().clear();
                        listView.getItems().addAll(apiResponse.getUsers());

                        Collections.sort(listView.getItems());

                        //setting visibility
                        listView.setVisible(true);
                        centeredGraphicVBox.setVisible(false);
                        tableView.setVisible(false);
                    }

                    if (this.toggleGroup.getSelectedToggle().equals(this.graphicListViewRadioButton)) {

                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame, "\"Graphic List View made from HBoxes and takes times little time to load. Please try to click any HBox to view more details.\"");

                        // clearing all the duplicate children again
                        userCardLayoutVBox.getChildren().clear();
                        for (User user : apiResponse.getUsers()) {

                            /*checkedUser = new User(user.getPosition(), user.getUserID(), user.getUserName(), user.getFullName(), user.getIsPrivate(), user.getIsVerified(), user.getHasAnonymousProfilePicture(), user.getHasHighlightReels(), user.getProfilePicture());*/


                            FXMLLoader fxmlLoader = new FXMLLoader();
                            //            fxmlLoader.setLocation(getClass().getResource("user-card-view.fxml"));
                            fxmlLoader.setLocation(Main.class.getResource("Views/user-card-view.fxml"));
                            HBox userCardBox = fxmlLoader.load();

                            mappingUserWithHBox.addUserAndHBoxInfo(userCardBox, user);

                            UserCardController userCardController = fxmlLoader.getController();
                            userCardController.setData(user);

                        }


                        userCardLayoutVBox.getChildren().addAll(mappingUserWithHBox.getAllUsersAndHBoxesInfo().keySet());



                        clickedAnyUserCardBoxInUserCardLayout();


                        //setting visibility
                        centeredGraphicVBox.setVisible(true);
                        listView.setVisible(false);
                        tableView.setVisible(false);
                    }



                    if(this.toggleGroup.getSelectedToggle().equals(this.tableViewRadioButton))
                    {
                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame, "\"Table view takes time while scrolling because it contains ImageView objects thats why. Please try to click any row to view more details.\"");

                        tableView.getItems().clear();

                        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
                        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
                        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
                        isVerifiedColumn.setCellValueFactory(new PropertyValueFactory<>("verifiedChoice"));
                        isPrivateColumn.setCellValueFactory(new PropertyValueFactory<>("privateChoice"));
                        profileImageColumn.setCellValueFactory(new PropertyValueFactory<>("profileImage"));
                        tableView.getItems().addAll(apiResponse.getUsers());

                        Collections.sort(tableView.getItems());

                        tableView.setVisible(true);
                        listView.setVisible(false);
                        centeredGraphicVBox.setVisible(false);

                    }

                }


    /**
     * This method will set event handler on the userCardBox we clicked in the graphic list view and changes the scene to the user profile detail view.
     */
    public void clickedAnyUserCardBoxInUserCardLayout() {


                    for (HBox userCardBox : mappingUserWithHBox.getAllUsersAndHBoxesInfo().keySet()) {
                        userCardBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                try {

                                    User.addClickedUserFromGraphicView(mappingUserWithHBox.getAllUsersAndHBoxesInfo().get(userCardBox));
                                    System.out.println(User.getClickedUserFromGrahicView().size());

                                    SceneChanger.changeScenes(event, "Views/user-profile-details-view.fxml", User.getClickedUserFromGrahicView().get(0).getFullName()+"'s Profile", User.getClickedUserFromGrahicView().get(0));

                                    User.getClickedUserFromGrahicView().remove(0);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        });
                    }

                }


    /**
     * This method will take back to the home- view.fxml
     * @param event
     * @throws IOException
     */
    @FXML
    void backButtonClicked(MouseEvent event) throws IOException {
                    System.out.println("Calling API and displaying Ram's Profile. Please wait a little...:)");
                    SceneChanger.changeScenes(event, "Views/home-view.fxml", "Ram's Profile");

                }

    /**
     * This method will clear the text field and and set the other two things to setVisible(false)
     * @param event
     */
    @FXML
    void clearSearchTextField(MouseEvent event) {
                        searchTextField.clear();

                        listView.setVisible(false);
                        radioButtonsHBox.setVisible(false);
                }


}



