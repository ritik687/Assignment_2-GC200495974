package com.example.assignment2.Models;

import com.example.assignment2.Main;
import com.example.assignment2.Utilities.APIUtility;
import com.google.gson.annotations.SerializedName;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class User implements Comparable<User>{

        @SerializedName("id")
        private String userID;

        @SerializedName("username")
        private String userName;

        @SerializedName("full_name")
        private String fullName;

        @SerializedName("is_private")
        private boolean isPrivate;

        @SerializedName("is_verified")
        private boolean isVerified;

        @SerializedName("has_anonymous_profile_picture")
        private boolean hasAnonymousProfilePicture;

        @SerializedName("profile_pic_url")
        private String profilePicture;

        private  static ArrayList<User> clickedUserFromGrahicView =new ArrayList<>();


    /**
     * This is the constructor that will validate the set methods through the below given parameters
     * @param userID -userId of the respective user
     * @param userName - user name for the user profile
     * @param fullName - full name of the user
     * @param isPrivate -whether user account is private
     * @param isVerified - whether user account is verified
     * @param hasAnonymousProfilePicture - whether account has anonymous profile picture
     * @param profilePicture - profile picture of the user.
     */
    public User( String userID, String userName, String fullName, boolean isPrivate, boolean isVerified, boolean hasAnonymousProfilePicture, String profilePicture) {
        setUserID(userID);
        setUserName(userName);
        setFullName(fullName);
        setIsPrivate(isPrivate);
        setIsVerified(isVerified);
        setHasAnonymousProfilePicture(hasAnonymousProfilePicture);
        setProfilePicture(profilePicture);
    }

    /**
     * This method will return the userId for the respective user and it is used in the table View.
     * @return
     */
    public String getUserID() {
        return userID;
    }

    /**
     * This method will validate that user ID should have length greater than 0
     * @param userID
     */
    public void setUserID(String userID) {
        if(userID.length()>0)
        {
            this.userID=userID;
        }
        else
            throw new IllegalArgumentException(" User Id should have length greater than 0");
    }

    /**
     * This method will return the userName  of the user and is used in listview and table view
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * this method will validate that user name should have length greater that 0;
     * @param userName
     */
    public void setUserName(String userName) {
        if(userName.length() > 0)
        {
            this.userName= userName;
        }
        else
            throw new IllegalArgumentException(" User Name should have length greater than 0");
    }

    /**
     * This method will return the full name of the user
     * @return
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * this method will validate that full name should have length greater that 0;
     * @param fullName
     */
    public void setFullName(String fullName) {
        if(fullName.length()>0)
            this.fullName =fullName;
        else
            throw new IllegalArgumentException(String.format("%s should have its length greater than 0", fullName));
    }

    /**
     * This method will return true if the user account is private otherwise return false
     * @return
     */
    public boolean getIsPrivate() {
        return isPrivate;
    }

    /**
     * This method will validate whether the account is private or not ,and value can either be true or false
     * @param isPrivate
     */
    public void setIsPrivate(boolean isPrivate) {
        if(isPrivate == true || isPrivate == false)
        {
            this.isPrivate =isPrivate;
        }
        else{
            throw new IllegalArgumentException(String.format(" isPrivate has either value true or false"));
        }
    }

    /**
     * This method will return true if the user account is verfied otherwise false
     * @return
     */
    public boolean getIsVerified() {
        return isVerified;
    }

    /**
     * This method will validate whether the user account is verified or not ,and value can either be true or false
     * @param isVerified
     */
    public void setIsVerified(boolean isVerified) {
        if(isVerified == true || isVerified == false)
        {
            this.isVerified =isVerified;
        }
        else
        {
            throw new IllegalArgumentException(String.format(" isVerified has either value true or false"));
        }
    }

    /**
     * This method will return true if user account has anonymous profile picture otherwise return false
     * @return
     */
    public boolean getHasAnonymousProfilePicture() {
        return hasAnonymousProfilePicture;
    }

    /**
     * This method will validate either anonymous picture be true or false otherwise throws the exception
     * @param hasAnonymousProfilePicture
     */
    public void setHasAnonymousProfilePicture(boolean hasAnonymousProfilePicture) {
        if(hasAnonymousProfilePicture == true || hasAnonymousProfilePicture == false)
        {
            this.hasAnonymousProfilePicture =hasAnonymousProfilePicture;
        }
        else{
            throw new IllegalArgumentException(String.format(" Anonymous Profile Picture has either value true or false"));
        }
    }


    /**
     * This method will return the Stirng format of the image in the form of url
     * @return
     */
    public String getProfilePicture() {
        return profilePicture;
    }


    /**
     * This method will validate that the profile picture url is valid or not using java.net.URL
     * @param profilePicture
     */
    public void setProfilePicture(String profilePicture) {
        // validating url using JDK
        boolean isValidUrl = false;
        try {
            isValidUrl = APIUtility.isValidURL(profilePicture);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        if (isValidUrl) {
            this.profilePicture = profilePicture;
        } else
            throw new IllegalArgumentException(" Profile Picture URL is not valid.");
    }

    /**
     * This method is used in the Graphic list view so as to add user to it
     * @param user
     */
    public  static void addClickedUserFromGraphicView(User user){
        clickedUserFromGrahicView.add(user);
    }

    /**
     * This method will return the arrray list of only one user because we are adding and removing also, and is used in the Graphic list view.
     * @return
     */
    public  static ArrayList<User> getClickedUserFromGrahicView(){
        return clickedUserFromGrahicView;
    }

    /**
     * This is the string format of the list view display of the respective user
     * @return
     */
    public String toString(){
        String listViewString;
        if(getIsVerified())
            listViewString= String.format("@\t%s ☑",getUserName());
        else
            listViewString= String.format("@\t%s",getUserName());

        return listViewString;
    }

    /**
     * This method will return the profile image in the form of ImageView and is used in the table view
     * @return
     */
    public ImageView getProfileImage() {
        ImageView imageView = null;
        String response = APIUtility.sendGETRequest(profilePicture);
        if(response!="Error") {
             imageView = new ImageView(new Image(profilePicture));
             imageView.setFitWidth(50);
             imageView.setFitHeight(50);
        }

        return imageView;
    }

    /**
     * This method will return YES or NO according to whether the account is verified or not.
     * @return
     */
    public String getVerifiedChoice() {
        if(getIsVerified())
        {
            return "YES";
        }
        else
        {
            return "NO";
        }
    }

    /**
     * This method will return YES or NO according to whether the account is private or not.
     * @return
     */
    public String getPrivateChoice() {
        if(getIsPrivate())
        {
            return "YES";
        }
        else
        {
            return "NO";
        }
    }

    /**
     * This method will compare user with other users and sort them according to the users who are verified and returns the verified users first.
     * @param otherUser
     * @return
     */
    @Override
    public int compareTo(User otherUser) {

        return -(String.valueOf(this.isVerified).compareTo(String.valueOf(otherUser.getIsVerified())));
    }
}
