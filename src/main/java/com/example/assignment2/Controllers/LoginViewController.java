package com.example.assignment2.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

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
    private Label msgLabel;

    @FXML
    void enterKeyPressedToLogin(KeyEvent event) {

    }

    @FXML
    void loginButtonClicked(MouseEvent event) {

          /*  if(!userNameField.getText().equals("ritik")){
                Alert alert =new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setContentText("Username/ phone number/ email is incorrect...");
                alert.show();
            }
*/
            if(!userNameField.getText().equals("ritik")){
                msgLabel.setText("Sorry, your username/email/phoneNumber was incorrect. Please double-check that.");
            }

            else if(!passwordField.getText().equals("ritik")){
                msgLabel.setText("Sorry, your password was incorrect. Please double-check your password.");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // setting cursors for all different objects
        showLabel.setCursor(Cursor.HAND);
        hideLabel.setCursor(Cursor.HAND);
        textFieldHBox.setVisible(false);

        loginButton.setDisable(true);

        userNameField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue.length()>0) {

                passwordField.textProperty().addListener((obs, old, value) -> {

                    if(value.length()>0) {
                        loginButton.setDisable(false);
                    }
                    else{
                        loginButton.setDisable(true);
                    }
                });
            }

            else{
                loginButton.setDisable(true);
            }
        });



    }
}
