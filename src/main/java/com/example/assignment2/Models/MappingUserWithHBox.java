package com.example.assignment2.Models;

import com.example.assignment2.Models.User;
import javafx.scene.layout.HBox;

import java.util.HashMap;

public class MappingUserWithHBox {

    private HashMap<HBox, User> usersAndHBoxesInfo =new HashMap<>();


    /**
     * This method will add the user to the hashmap taking HBox and user as key value pair
     * @param hbox
     * @param user
     */
    public void addUserAndHBoxInfo(HBox hbox, User user){
            usersAndHBoxesInfo.put(hbox,user);
    }

    /**
     * This method will return all the users that is added to the hashmaps with respect to its HBox;
     * @return
     */
    public HashMap<HBox,User> getAllUsersAndHBoxesInfo(){
            return usersAndHBoxesInfo;
    }



}
