package com.example.assignment2.Interfaces;

import com.example.assignment2.Models.User;

public interface UserInitializable {



    void loadUserDetailsFromListView(String passedSearchTerm, User passedUser);

    void loadAllUsers(String passedSearchTerm);

    void loadUserDetailsFromGraphicView(User passedUser);


}
