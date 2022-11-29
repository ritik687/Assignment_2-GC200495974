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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private HBox loadingHboxComment;

    @FXML
    private VBox userCardLayoutVBox;

    @FXML
    private Button homeImageButton;

    @FXML
    private Button searchImageButton;


    private User checkedUser;

    private MappingUserWithHBox mappingUserWithHBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userCardLayoutVBox.setVisible(false);
        scrollPane.setVisible(false);
        loadingHboxComment.setVisible(true);

        homeImageButton.setCursor(Cursor.HAND);
        searchImageButton.setCursor(Cursor.HAND);

        mappingUserWithHBox =new MappingUserWithHBox();



    }

    @FXML
    void searchButtonClicked(ActionEvent event) throws IOException, InterruptedException {

        userCardLayoutVBox.setVisible(true);
        scrollPane.setVisible(true);

        String searchTerm = searchTextField.getText();

        APIResponse apiResponse = APIUtility.getDataFromFile();


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
                   row++;
*/

        }

        userCardLayoutVBox.getChildren().addAll(mappingUserWithHBox.getAllUsersAndHBoxesInfo().keySet());

        loadingHboxComment.setVisible(false);

        clickedAnyUserCardBoxInUserCardLayout();




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
                        System.out.println(User.getClickedUserFromUserCardBox().size());

                        SceneChanger.changeScenes(event, "Views/user-more-detail-view.fxml", "User Profile");
                        User.getClickedUserFromUserCardBox().remove(0);

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



