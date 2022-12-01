package com.example.assignment2.Controllers;

import com.example.assignment2.Main;
import com.example.assignment2.Utilities.SceneChanger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;


import java.awt.*;



public class LoginViewController implements Initializable {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label showLabel;

    @FXML
    private Label hideLabel;

    @FXML
    private TextField userNameField;


    @FXML
    private HBox passwordFieldHBox;


    @FXML
    private HBox textFieldHBox;

    @FXML
    private Label msgLabel1;

    @FXML
    private Label msgLabel2;

    @FXML
    private Button googlePlayButton;

    @FXML
    private Button microsoftButton;

    @FXML
    private Label showCredentialsLabel;

    @FXML
    private HBox instagramLabelHBox;

    /**
     *This method will show the password
     * @param event
     */
                @FXML
                void showLabelClicked(MouseEvent event) {
                    passwordTextField.setText(passwordField.getText());
                    passwordFieldHBox.setVisible(false);
                    textFieldHBox.setVisible(true);
                }

    /**
     * This method will hide the password
     * @param event
     */
                @FXML
                void hideLabelClicked(MouseEvent event) {
                       passwordField.setText(passwordTextField.getText());
                       passwordFieldHBox.setVisible(true);
                       textFieldHBox.setVisible(false);
                }

    /**
     * This method will open the google play store link in the browser
     * @param event
     * @throws URISyntaxException
     * @throws IOException
     */
    @FXML
    void googlePlayButtonClicked(ActionEvent event) throws URISyntaxException, IOException {

                    Desktop.getDesktop().browse(new URI("https://play.google.com/store/apps/details?id=com.instagram.android"));
                }

    /**
     * This method will open the microsoft store link in the browser
     * @param event
     * @throws URISyntaxException
     * @throws IOException
     */
    @FXML
                void microsoftButtonClicked(ActionEvent event) throws URISyntaxException, IOException {
                    Desktop.getDesktop().browse(new URI("https://apps.microsoft.com/store/detail/instagram/9NBLGGH5L9XT?hl=en-ca&gl=ca"));
                }



            @Override
            public void initialize(URL url, ResourceBundle resourceBundle) {

                            // setting cursors for all different objects
                            showLabel.setCursor(Cursor.HAND);
                            hideLabel.setCursor(Cursor.HAND);
                            googlePlayButton.setCursor(Cursor.OPEN_HAND);
                            microsoftButton.setCursor(Cursor.OPEN_HAND);
                            showCredentialsLabel.setCursor(Cursor.HAND);
                            instagramLabelHBox.setCursor(Cursor.HAND);
                            loginButton.setCursor(Cursor.HAND);

                            msgLabel1.setText("");
                            msgLabel2.setText("");

                            textFieldHBox.setVisible(false);
                            showLabel.setVisible(false);
                            showCredentialsLabel.setVisible(false);


                            loginButton.setDisable(true);

                            // calling textfields textproperty listener method
                            textFieldsTextPropertyListener();


                /**
                 * Setting event handler on the login button if its clicked then changed the scene to home-view.fxml
                 */
                loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {

                                    JFrame frame = new JFrame();
                                    JOptionPane.showMessageDialog(frame, "\" Directing you to Ram's Profile Details. This will take a little time :) \"");

                                    if(userNameField.getText().equals("ritik_mall_") && passwordField.getText().equals("ritik")){
                                        try {
                                            System.out.println("Calling API and displaying Ram's Profile. Please wait a little...:)");
                                            SceneChanger.changeScenes(event,"Views/home-view.fxml","Ram's Profile");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    else if(!userNameField.getText().equals("ritik_mall_") && !passwordField.getText().equals("ritik")){
                                        msgLabel1.setText("Sorry, your password and username both ");
                                        msgLabel2.setText("was incorrect.");
                                        showCredentialsLabel.setVisible(true);
                                    }

                                    else if(!userNameField.getText().equals("ritik_mall_")){
                                        msgLabel1.setText("Sorry, your username/email/phoneNumber was incorrect. ");
                                        msgLabel2.setText("Please double-check that.");
                                    }

                                    else if(!passwordField.getText().equals("ritik")){
                                        msgLabel1.setText("Sorry, your password was incorrect. ");
                                        msgLabel2.setText("Please double-check your password.");
                                    }
                                }
                            });

                /**
                 * This method will set even handler mouse click on the instagram label button which refresh the login page
                 */
                instagramLabelHBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            SceneChanger.changeScenes(event,"Views/login-view.fxml","Login Page");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });


                /**
                 * This method will set the event handler mouse clicked on the show credentials label
                 */
                            showCredentialsLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Message");
                                    alert.setHeaderText("CREDENTIALS");
                                    alert.setContentText("\tUser Name : ritik_mall_\n\tPassword: ritik");
                                    alert.show();

                                }});





            }


    /**
     * This method will add listener to the text field text property() that is login button will become disable when the text is typed or not.
     */
    public void textFieldsTextPropertyListener(){

                userNameField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    if(newValue.length()>0) {

                        passwordField.textProperty().addListener((obs, old, value) -> {

                            if(value.length()>0) {
                                loginButton.setDisable(false);
                                showLabel.setVisible(true);
                            }
                            else{
                                loginButton.setDisable(true);
                                showLabel.setVisible(false);

                            }
                        });
                    }

                    else{
                        loginButton.setDisable(true);
                    }
                });

                // again adding the same listener to the password field
                passwordField.textProperty().addListener((observableValue, s, newText) -> {
                    if(newText.length()>0)
                    {
                        loginButton.setDisable(false);
                        showLabel.setVisible(true);
                    }
                    else
                    {
                        loginButton.setDisable(true);
                        showLabel.setVisible(false);
                    }
                });

                userNameField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    if(newValue.length()>0)
                    {
                        loginButton.setDisable(false);
                        showLabel.setVisible(true);
                    }

                    else
                    {
                        loginButton.setDisable(true);
                        showLabel.setVisible(false);
                    }
                });

            }
    }

