package com.example.assignment2.Models;

import com.example.assignment2.Models.User;
import javafx.scene.layout.HBox;

import java.util.HashMap;

public class MappingUserWithHBox {

    private HashMap<HBox, User> usersAndHBoxesInfo =new HashMap<>();


    public void addUserAndHBoxInfo(HBox hbox, User user){
            usersAndHBoxesInfo.put(hbox,user);
    }

    public HashMap<HBox,User> getAllUsersAndHBoxesInfo(){
            return usersAndHBoxesInfo;
    }


}
