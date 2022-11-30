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
    void enterKeyPressedToLogin(KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER){
            if(!userNameField.getText().equals("ritik") && !passwordField.getText().equals("ritik")){
                msgLabel1.setText("Sorry, your password and username both ");
                msgLabel2.setText("was incorrect.");
            }

            else if(!userNameField.getText().equals("ritik")){
                msgLabel1.setText("Sorry, your username/email/phoneNumber was incorrect. ");
                msgLabel2.setText("Please double-check that.");
            }

            else if(!passwordField.getText().equals("ritik")){
                msgLabel1.setText("Sorry, your password was incorrect. ");
                msgLabel2.setText("Please double-check your password.");
            }
        }
    }


    @FXML
    void showLabelClicked(MouseEvent event) {
        passwordTextField.setText(passwordField.getText());
        passwordFieldHBox.setVisible(false);
        textFieldHBox.setVisible(true);
    }

    @FXML
    void hideLabelClicked(MouseEvent event) {
        passwordField.setText(passwordTextField.getText());
        passwordFieldHBox.setVisible(true);
        textFieldHBox.setVisible(false);
    }
    @FXML
    void googlePlayButtonClicked(ActionEvent event) throws URISyntaxException, IOException {

        Desktop.getDesktop().browse(new URI("https://play.google.com/store/apps/details?id=com.instagram.android"));
    }

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

        msgLabel1.setText("");
        msgLabel2.setText("");

        textFieldHBox.setVisible(false);
        showLabel.setVisible(false);
        showCredentialsLabel.setVisible(false);


        loginButton.setDisable(true);

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
            if(newText.length()>0) {
                loginButton.setDisable(false);
                showLabel.setVisible(true);
            }
            else{
                loginButton.setDisable(true);
                showLabel.setVisible(false);
            }
        });




        loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if(userNameField.getText().equals("ritik") && passwordField.getText().equals("ritik")){
                    try {
                        SceneChanger.changeScenes(event,"Views/home-view.fxml","Home Page");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(!userNameField.getText().equals("ritik") && !passwordField.getText().equals("ritik")){
                    msgLabel1.setText("Sorry, your password and username both ");
                    msgLabel2.setText("was incorrect.");
                    showCredentialsLabel.setVisible(true);
                }

                else if(!userNameField.getText().equals("ritik")){
                    msgLabel1.setText("Sorry, your username/email/phoneNumber was incorrect. ");
                    msgLabel2.setText("Please double-check that.");
                }

                else if(!passwordField.getText().equals("ritik")){
                    msgLabel1.setText("Sorry, your password was incorrect. ");
                    msgLabel2.setText("Please double-check your password.");
                }
            }
        });



        showCredentialsLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText("CREDENTIALS");
                alert.setContentText("\tUser Name : ritik\n\tPassword: ritik");
                alert.show();

            }});





    }
}
