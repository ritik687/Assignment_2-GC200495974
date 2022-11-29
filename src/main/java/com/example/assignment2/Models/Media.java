package com.example.assignment2.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class Media {

    @SerializedName("count")
    private int totalPosts;

    @SerializedName("media")
    private Post[] posts;


    public int getTotalPosts() {
        return totalPosts;
    }

    public List<Post> getPosts(){
        System.out.println(Arrays.asList(posts));
        return Arrays.asList(posts);
    }
}
