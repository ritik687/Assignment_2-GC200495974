package com.example.assignment2;

import com.google.gson.annotations.SerializedName;

public class UserProfileDetail {

    @SerializedName("id")
    private String userID;

    @SerializedName("username")
    private String userName;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("bio")
    private String bioText;

    @SerializedName("followers")
    private int followers;

    @SerializedName("following")
    private int following;

    @SerializedName("category_name")
    private String categoryName;

    @SerializedName("is_private")
    private boolean privateAccount;

    @SerializedName("is_verified")
    private boolean verifiedAccount;

    @SerializedName("is_business")
    private boolean businessAccount;

    @SerializedName("profile_pic_url")
    private String profilePicture;

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBioText() {
        return bioText;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public boolean getPrivateAccount() {
        return privateAccount;
    }

    public boolean getVerifiedAccount() {
        return verifiedAccount;
    }

    public boolean getBusinessAccount() {
        return businessAccount;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
}