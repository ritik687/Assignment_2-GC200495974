package com.example.assignment2.Models;




import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class APIResponse {



    @SerializedName("id")
    private String searchedUser;

    @SerializedName("result")
    private User[] users;

    @SerializedName("message")
    private String message;

    /**
     * This is the constructor for the APIResponse class and will do validations accordingly
     * @param searchedUser - user that is searched
     * @param users - User objects in the form of array.
     * @param message - error message that will be reflected on the API side due to refreshing problem.
     */
    public APIResponse(String searchedUser, User[] users, String message) {
        setSearchedUser(searchedUser);
        setUsers(users);
        setMessage(message);
    }

    /**
     * This method will return the list of the Users that is returned by the API
     * @return
     */
    public List<User> getUsers(){

            return Arrays.asList(users);
    }

    /**
     * This method will give the message like error type and tell you what is required or what is not.
     * @return -
     */
    public String getMessage() {
        return message;
    }

    /**
     * As we calling API that needs search term or search user, so this method will return the searched user by us.
     * @return
     */
    public String getSearchedUser() {
        return searchedUser;
    }


    /**
     * This method will validate the message that should have length of its string greater than 0.
     * @param message
     */
    public void setMessage(String message)
    {
        if(message.length()>0){
            this.message= message;
        }
        else
            throw new IllegalArgumentException(" Message error should have atleast one charater in its string.");
    }


    /**
     * This method will validate that Users should not have null value
     * @param users
     */
    public void setUsers(User[] users) {
        if(users!=null){
            this.users= users;
        }
        else
            throw new IllegalArgumentException("User objects can never be null");
    }

    /**
     * This method will validate that searched user should have length of its string greater than 0.
     * @param searchedUser
     */
    public void setSearchedUser(String searchedUser) {
        if(searchedUser.length()>0)
        {
            this.searchedUser =searchedUser;
        }
        else
            throw new IllegalArgumentException("Searched User should have atleast one character.");
    }
}
