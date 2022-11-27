package com.example.assignment2.Models;

import com.google.gson.annotations.SerializedName;

public class Media {

    @SerializedName("count")
    private int totalPosts;

    public int getTotalPosts() {
        return totalPosts;
    }
}
