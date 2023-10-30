package com.example.appteka.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Drug implements Serializable {
    @SerializedName("drug_name")
    public String name;
    @SerializedName("drug_image")
    public String image;
    @SerializedName("weight")
    public Integer weight;
    @SerializedName("price")
    public Integer price;
    @SerializedName("min_age")
    public Integer minAge;

    public Integer amount = 1;
    Drug(String name, String image, int weight, int price, int minAge){
        this.name = name;
        this.image = image;
        this.weight = weight;
        this.price = price;
        this.minAge = minAge;
    }

}
