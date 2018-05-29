package com.example.mert.ww2database.Model;

import java.io.Serializable;

public class Profile implements Serializable{
   //CREATE TABLE `Profiles` (
    //	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
    //	`name`	VARCHAR(255),
    //	`country`	VARCHAR(255),
    //	`category`	VARCHAR(255),
    //	`gender`	VARCHAR(255),
    //	`birth`	INTEGER,
    //	`death`	INTEGER,
    //	`description`	TEXT,
    //	`image_url`	TEXT
    //);

    private int id;
    private String name;
    private String country;
    private String category;
    private String gender;
    private int birth;
    private int death;
    private String desription;
    private String image_url;

    public Profile(int id, String name, String country, String category, String gender, int birth, int death, String desription, String image_url) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.category = category;
        this.gender = gender;
        this.birth = birth;
        this.death = death;
        this.desription = desription;
        this.image_url = image_url;
    }
    public Profile(int id,String name,String country,String category,String image_url){
        this.id=id;
        this.name=name;
        this.country=country;
        this.category=category;
        this.image_url=image_url;
    }

    public Profile() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}

