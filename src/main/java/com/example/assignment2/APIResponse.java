package com.example.assignment2;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class APIResponse {

    @SerializedName("search_param")
    private String searchParameter;

    @SerializedName("count")
    private int totalResults;

    @SerializedName("result")
    private User[] users;



    public List<User> getUsers(){
            return Arrays.asList(users);
    }

    public int getTotalResults()
    {
        return totalResults;
    }

    public String getSearchParameter()
    {
        return searchParameter;
    }





}
