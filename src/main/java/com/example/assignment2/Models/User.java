package com.example.assignment2.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {
        @SerializedName("position")
        private int position;

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
        
        @SerializedName("has_highlight_reels")
        private boolean hasHighlightReels;
        
        @SerializedName("profile_pic_url")
        private String profilePicture;

        private  static ArrayList<User> clickedUserFromBothListViews =new ArrayList<>();



    public User(int position, String userID, String userName, String fullName, boolean isPrivate, boolean isVerified, boolean hasAnonymousProfilePicture, boolean hasHighlightReels, String profilePicture) {
        setPosition(position);
        setUserID(userID);
        setUserName(userName);
        setFullName(fullName);
        setIsPrivate(isPrivate);
        setIsVerified(isVerified);
        setHasAnonymousProfilePicture(hasAnonymousProfilePicture);
        setHasHighlightReels(hasHighlightReels);
        setProfilePicture(profilePicture);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        if(position >=0)
        {
            this.position =position;
        }
        else{
            throw new IllegalArgumentException(" Position Should be greater than or equal to 0" );
        }
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        if(userID.length()>0)
        {
            this.userID=userID;
        }
        else
            throw new IllegalArgumentException(" User Id should have length greater than 0");
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if(userName.length() > 0)
        {
            this.userName= userName;
        }
        else
            throw new IllegalArgumentException(" User Name should have length greater than 0");
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if(fullName.length()>0)
            this.fullName =fullName;
        else
            throw new IllegalArgumentException(String.format("%s should have its length greater than 0", fullName));
    }

    public boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(boolean isPrivate) {
        if(isPrivate == true || isPrivate == false)
        {
            this.isPrivate =isPrivate;
        }
        else{
            throw new IllegalArgumentException(String.format(" isPrivate has either value true or false"));
        }
    }

    public boolean getIsVerified() {
        return isVerified;
    }

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

    public boolean getHasAnonymousProfilePicture() {
        return hasAnonymousProfilePicture;
    }

    public void setHasAnonymousProfilePicture(boolean hasAnonymousProfilePicture) {
        if(hasAnonymousProfilePicture == true || hasAnonymousProfilePicture == false)
        {
            this.hasAnonymousProfilePicture =hasAnonymousProfilePicture;
        }
        else{
            throw new IllegalArgumentException(String.format(" Anonymous Profile Picture has either value true or false"));
        }
    }

    public boolean getHasHighlightReels() {
        return hasHighlightReels;
    }

    public void setHasHighlightReels(boolean hasHighlightReels) {
        if(hasHighlightReels == true || hasHighlightReels== false){
            this.hasHighlightReels =hasHighlightReels;
        }
        else
            throw new IllegalArgumentException(" hasHighlight reels has value either true or false");
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        if(profilePicture.length()>0){
            this.profilePicture =profilePicture;
        }

        else
            throw new IllegalArgumentException(" Profile Picture URL should have length greater than 0");
    }

    public  static void addClickedUserFromUserCardBox(User user){
        clickedUserFromBothListViews.add(user);
    }
    public  static ArrayList<User> getClickedUserFromBothListViews(){
        return clickedUserFromBothListViews;
    }

    public String toString(){
        String listViewString;
        if(getIsVerified())
            listViewString= String.format("@\t%s☑",getUserName());
        else
            listViewString= String.format("@\t%s",getUserName());

        return listViewString;
    }
}
