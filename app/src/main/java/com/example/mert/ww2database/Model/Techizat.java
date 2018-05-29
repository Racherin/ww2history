package com.example.mert.ww2database.Model;

public class Techizat {
    private int id;
    private String eq_name;
    private String eq_desc;
    private String eq_img;

    public Techizat(int id, String eq_name, String eq_desc, String eq_img) {
        this.id = id;
        this.eq_name = eq_name;
        this.eq_desc = eq_desc;
        this.eq_img = eq_img;
    }

    public Techizat() {
    }

    public Techizat(int id, String eq_name, String eq_img) {
        this.id = id;
        this.eq_name = eq_name;
        this.eq_img = eq_img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEq_name() {
        return eq_name;
    }

    public void setEq_name(String eq_name) {
        this.eq_name = eq_name;
    }

    public String getEq_desc() {
        return eq_desc;
    }

    public void setEq_desc(String eq_desc) {
        this.eq_desc = eq_desc;
    }

    public String getEq_img() {
        return eq_img;
    }

    public void setEq_img(String eq_img) {
        this.eq_img = eq_img;
    }
}
