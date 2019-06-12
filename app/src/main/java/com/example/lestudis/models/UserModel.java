package com.example.lestudis.models;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class UserModel {
    private String email;

    public UserModel(){
        //required for DataSnapshot.getValue
    }

    public UserModel(String email){
        this.email = email;
    }

    public String getEmail(){return this.email;}

    public void setEmail(String email){this.email = email;}
}
