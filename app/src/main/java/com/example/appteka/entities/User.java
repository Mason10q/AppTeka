package com.example.appteka.entities;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user_name")
    public String name;
    @SerializedName("birth_date")
    public String date;
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;
    @SerializedName("isAdmin")
    public Boolean isAdmin;



    public User(String name, String date, String email, String password, Boolean isAdmin){
        this.name = name;
        this.date = date;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

}
