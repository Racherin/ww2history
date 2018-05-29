package com.example.mert.ww2database.Model;

import java.io.Serializable;

public class TechizatDetay implements Serializable{
    private int id;
    private int techizat_id;
    private String name;
    private String image;
    private String country;
    private String primaryRole;
    private String desc;

    public TechizatDetay() {
    }

    public TechizatDetay(int id, int techizat_id, String name, String image, String country, String primaryRole, String desc) {
        this.id = id;
        this.techizat_id = techizat_id;
        this.name = name;
        this.image = image;
        this.country = country;
        this.primaryRole = primaryRole;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTechizat_id() {
        return techizat_id;
    }

    public void setTechizat_id(int techizat_id) {
        this.techizat_id = techizat_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPrimaryRole() {
        return primaryRole;
    }

    public void setPrimaryRole(String primaryRole) {
        this.primaryRole = primaryRole;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
