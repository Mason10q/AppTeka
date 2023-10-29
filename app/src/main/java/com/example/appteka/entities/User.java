package com.example.appteka.entities;

public class User {

    public String name;
    public String date;
    public String email;
    public String password;



    public User(String name, String date, String email, String password){
        this.name = name;
        this.date = date;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

}
