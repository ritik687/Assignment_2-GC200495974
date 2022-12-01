package com.example.assignment2.Models;

import com.example.assignment2.Utilities.APIUtility;
import com.google.gson.annotations.SerializedName;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Post {

    @SerializedName("display_url")
    private String picture;

    @SerializedName("is_video")
    private boolean isVideo;

    @SerializedName("comment_count")
    private long totalComments;

    @SerializedName("like")
    private long totalLikes;

    /**
     * This is the constructor for the Post class and will do validations accordingly.
     * @param picture - picture URL as a string
     * @param isVideo - whether the post is video or not
     * @param totalComments - total no. of comments per post.
     * @param totalLikes - total no. of likes per post.
     */
    public Post(String picture, boolean isVideo, long totalComments, long totalLikes) {
        setPicture(picture);
        setIsVideo(isVideo);
        setTotalComments(totalComments);
        setTotalLikes(totalLikes);
    }

    /**
     * This method will return the pictures that we are going to display as a post
     * @return
     */
    public String getPicture() {
        return picture;
    }

    /**
     * This method will return the boolean value for the image that is false because that will be the video . But I only displayed the images
     * @return
     */

    public boolean getIsVideo() {
        return isVideo;
    }

    /**
     * This method will return the total comments per post
     * @return
     */
    public long getTotalComments() {
        return totalComments;
    }

    /**
     * This metho will return the total likes per post
     * @return
     */
    public long getTotalLikes() {
        return totalLikes;
    }

    /**
     * This method will validate that the post picture url is valid or not using java.net.URL
     * @param picture
     */
    public void setPicture(String picture) {
        // validating url using JDK
        boolean isValidUrl = false;
        try {
            isValidUrl = APIUtility.isValidURL(picture);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        if (isValidUrl) {
            this.picture = picture;
        } else
            throw new IllegalArgumentException(" Profile Picture URL is not valid.");
    }


    /**
     * This method will check for two values that is true or false. it will be true for if the post is video.
     * @param isVideo
     */
    public void setIsVideo(boolean isVideo) {
        if(isVideo == true || isVideo == false)
        {
            this.isVideo =isVideo;
        }
        else
        {
            throw new IllegalArgumentException(String.format(" isVideo has either value true or false"));
        }
    }

    /**
     * This method will validate that total number of comments have value should be zero or greater than 0.
     * @param totalComments
     */
    public void setTotalComments(long totalComments) {
        if(totalComments<0)
        {
            throw new IllegalArgumentException(" Comments count will always be positive or zero");
        }
        else
        {
            this.totalComments=totalComments;
        }
    }

    /**
     * This method will validate that total number of likes cant be negative but can be zero or more.
     * @param totalLikes
     */
    public void setTotalLikes(long totalLikes) {
        if(totalLikes<0)
        {
            throw new IllegalArgumentException("Likes count will always be positive or zero");
        }
        else
        {
            this.totalLikes=totalLikes;
        }
    }
}
