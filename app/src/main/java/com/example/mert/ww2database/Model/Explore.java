package com.example.mert.ww2database.Model;

import java.io.Serializable;

public class Explore implements Serializable {
    private String id;
    private String contentName;
    private String contentDate;
    private String contentDesc;
    private String contentImg;

    public Explore() {
    }

    public Explore(String id, String contentName, String contentDate, String contentDesc, String contentImg) {
        this.id = id;
        this.contentName = contentName;
        this.contentDate = contentDate;
        this.contentDesc = contentDesc;
        this.contentImg = contentImg;
    }

    public Explore(String id, String contentName, String contentDate, String contentImg) {
        this.id = id;
        this.contentName = contentName;
        this.contentDate = contentDate;
        this.contentImg = contentImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContentDate() {
        return contentDate;
    }

    public void setContentDate(String contentDate) {
        this.contentDate = contentDate;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public String getContentImg() {
        return contentImg;
    }

    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }
}

