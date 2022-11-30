package com.example.assignment2.Models;

import com.example.assignment2.Models.User;
import com.google.gson.annotations.SerializedName;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;

public class APIResponse {

    @SerializedName("search_param")
    private String searchParameter;

    @SerializedName("count")
    private int totalResults;

    @SerializedName("result")
    private User[] users;

    @SerializedName("error")
    private boolean isError;

    public boolean getIsError() {
        return isError;
    }

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

   /* public ArrayList<User> allUsers(){
        ArrayList<User> allUsers= new ArrayList<>();
        for(User user: users){
            allUsers.add(user);
        }
        return allUsers;
    }*/





}
