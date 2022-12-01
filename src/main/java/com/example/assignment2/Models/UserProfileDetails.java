package com.example.assignment2.Models;

import com.example.assignment2.Models.Media;
import com.example.assignment2.Utilities.APIUtility;
import com.google.gson.annotations.SerializedName;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.List;

public class UserProfileDetails {


    @SerializedName("username")
    private String userName;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("bio")
    private String bioText;

    @SerializedName("followers")
    private long followers;

    @SerializedName("following")
    private long following;

    @SerializedName("category_name")
    private String categoryName;

    @SerializedName("is_private")
    private boolean privateAccount;

    @SerializedName("is_verified")
    private boolean verifiedAccount;

    @SerializedName("profile_pic_url_hd")
    private String profilePicture;

    // lastMedia in the json format is object not array.
    @SerializedName("lastMedia")
    private Media medias;

    @SerializedName("message")
    private String message;


    /**
     * This is the constructor that will do validations accordingly for the UserProfileDetails Class.
     * @param userName - user name of the user in his/her profile.
     * @param fullName - full name of the user in his/her profile.
     * @param bioText - bio information of the user
     * @param followers - no.of followers per user
     * @param following - no. of following per user
     * @param categoryName - category name of the user.
     * @param privateAccount - whether the account of user is private or not.
     * @param verifiedAccount - whether the user is verified or not.
     * @param profilePicture - profile picture as a string in the form of url
     * @param medias - Media objects that should not be null.
     * @param message - message error that only happened on the server API side.
     */
    public UserProfileDetails(String userName, String fullName, String bioText, long followers, long following, String categoryName, boolean privateAccount, boolean verifiedAccount, String profilePicture, Media medias, String message) {
        setUserName(userName);
        setFullName(fullName);
        setBioText(bioText);
        setFollowers(followers);
        setFollowing(following);
        setCategoryName(categoryName);
        setPrivateAccount(privateAccount);
        setVerifiedAccount(verifiedAccount);
        setProfilePicture(profilePicture);
        setMedias(medias);
        setMessage(message);
    }


    /**
     * This method will return the message that will work as a error on the api side and reflect changes accordingly
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method will return the username of the searched user from the getProfile section
      * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method will return the fullName of the user in his/her profile
     * @return
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * This method will return the Bio string of the user that we are going to select from the user.
     * @return
     */
    public String getBioText() {
        return bioText;
    }

    /**
     * This method will return the total followers of the respective user profile
     * @return
     */
    public long getFollowers() {
        return followers;
    }

    /**
     * This method will return the total followings of the respective user profile
     * @return
     */
    public long getFollowing() {
        return following;
    }

    /**
     * This method will return the category of the user profile whether its artist, singer, business, etc
     * @return
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * This method will return the boolean value true if the user account is private
     * @return
     */
    public boolean getIsPrivateAccount() {
        return privateAccount;
    }

    /**
     *This method will return the boolean value true if the user account is verified
     * @return
     */
    public boolean getIsVerifiedAccount() {
        return verifiedAccount;
    }

    /**
     * This method will return the string value like link to the image
     * @return
     */
    public String getProfilePicture() {
        return profilePicture;
    }

    /**
     * This method will return the medias objects that contains different posts objects
     * @return
     */
    public Media getMedias(){
        return medias;
    }


    /**
     * This method will validate that userName should have atleast one character
     * @param userName
     */
    public void setUserName(String userName) {
        if(userName.length()>0)
            this.userName =userName;
        else
            throw new IllegalArgumentException(" User Name should have atleast one character ");
    }

    /**
     * This method will validate that the fullName should have atleast one character.
     * @param fullName
     */
    public void setFullName(String fullName) {
        if(fullName.length()>0)
            this.fullName = fullName;
        else
            throw new IllegalArgumentException(" Full name should have atleast one character");
    }

    /**
     * This method will validate that the bio text length should be greater than 0
     * @param bioText
     */
    public void setBioText(String bioText) {
        if(bioText.length()>0)
            this.bioText = bioText;
        else
            throw new IllegalArgumentException("Bio data should have atleast one character");
    }

    /**
     * This method will validatae that followers count cant be negative.
     * @param followers
     */
    public void setFollowers(long followers) {
        if(followers>=0)
            this.followers=followers;
        else
            throw new IllegalArgumentException("Followers cant be negative but can be zero");
    }

    /**
     * This method will validate that following count cant be negative.
     * @param following
     */
    public void setFollowing(long following) {
        if(following>=0)
            this.following= following;
        else
            throw new IllegalArgumentException("Followers cant be negative but can be zero");
    }

    /**
     * This method will validate whether the category name is in the list of categories.
     * @param categoryName
     */
    public void setCategoryName(String categoryName) {
        List<String> categories = Arrays.asList("artist","musician/band","blogger","clothing(brand)","community","digital creator","education","entrepreneur","health/beauty","editor","writer","personal blog","product/service","gamer","restaurant","beauty,cosmetic & personal care","supermarket/convenience store","photographer","shopping & retail","video creator");

        if(categories.contains(categoryName.trim().toLowerCase())){
            this.categoryName= categoryName;
        }
        else
            throw new IllegalArgumentException(categoryName+" is not in the list"+categories);
    }

    /**
     * This method will validate private account have either value true or false.
     * @param privateAccount
     */
    public void setPrivateAccount(boolean privateAccount) {
        if(privateAccount == true || privateAccount == false)
        {
            this.privateAccount =privateAccount;
        }
        else{
            throw new IllegalArgumentException(String.format(" isPrivate has either value true or false"));
        }
    }

    /**
     *This method will validate verified account have either value true or false.
     * @param verifiedAccount
     */
    public void setVerifiedAccount(boolean verifiedAccount) {
        if(verifiedAccount == true || verifiedAccount == false)
        {
            this.verifiedAccount =verifiedAccount;
        }
        else
        {
            throw new IllegalArgumentException(String.format(" isVerified has either value true or false"));
        }
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
     * This method will validate that Media object should not be null
     * @param medias
     */
    public void setMedias(Media medias) {
        if(medias!=null){
            this.medias =medias;
        }
        else
            throw new IllegalArgumentException("Medias objects can never be null");
    }

    /**
     * This method will validate the message that should have length of its string greater than 0.
     * @param message
     */
    public void setMessage(String message) {
        if(message.length()>0){
            this.message= message;
        }
        else
            throw new IllegalArgumentException(" Message error should have atleast one charater in its string.");
    }
}
