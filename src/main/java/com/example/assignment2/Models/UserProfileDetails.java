package com.example.assignment2.Models;

import com.example.assignment2.Models.Media;
import com.google.gson.annotations.SerializedName;

public class UserProfileDetails {

    @SerializedName("id")
    private String userID;

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

    public long getFollowers() {
        return followers;
    }

    public long getFollowing() {
        return following;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public boolean getIsPrivateAccount() {
        return privateAccount;
    }

    public boolean getIsVerifiedAccount() {
        return verifiedAccount;
    }

    public boolean getIsBusinessAccount() {
        return businessAccount;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
}
