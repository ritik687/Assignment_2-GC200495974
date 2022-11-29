package com.example.assignment2.Models;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("display_url")
    private String picture;

    @SerializedName("is_video")
    private boolean isVideo;

    public String getPicture() {
        return picture;
    }

    public boolean getIsVideo() {
        return isVideo;
    }
}
