package com.example.assignment2.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class Media {

    @SerializedName("count")
    private int totalPosts;

    @SerializedName("media")
    private Post[] posts;


    /**
     * This is the constructor for the Media class and will do validations accordingly
     * @param totalPosts - total number of posts that contains both videos and photos
     * @param posts - Post objects in the form of array but will be return as list.
     */
    public Media(int totalPosts, Post[] posts) {
        setTotalPosts(totalPosts);
        setPosts(posts);
    }


    /**
     * This method totalPosts including videos and images in the Media
     * @return
     */
    public int getTotalPosts() {
        return totalPosts;
    }

    /**
     * This method will return the Post objects as a list that are in the Media objects
     * @return
     */
    public List<Post> getPosts(){

        return Arrays.asList(posts);
    }

    /**
     * This method will validate that total number of posts can never be negative.
     * @param totalPosts
     */
    public void setTotalPosts(int totalPosts) {
        if(totalPosts<0){
            throw new IllegalArgumentException(" Posts count can never be negative  but can be zero");
        }
        else
            this.totalPosts=totalPosts;
    }

    /**
     * This method will validate that Post objects should not be null.
     * @param posts
     */
    public void setPosts(Post[] posts) {
        if(posts!=null)
            this.posts=posts;
        else
            throw new IllegalArgumentException("Post objects cant be null");
    }
}
