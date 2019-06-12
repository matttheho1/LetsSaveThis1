package com.example.lestudis.models;

public class UserModel {
    private String email;

    public UserModel(String email){
        this.email = email;
    }

    public String getEmail(){return this.email;}

    public void setEmail(String email){this.email = email;}
}
