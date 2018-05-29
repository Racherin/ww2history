package com.example.mert.ww2database.Model;

import java.io.Serializable;

public class Nations implements Serializable{
    private int id;
    private String country_name;
    private String country_alliance;
    private int population_1939;
    private String flag;
    private String description;

    public Nations() {
    }

    public Nations(int id, String country_name, String country_alliance, int population_1939, String flag, String description) {
        this.id = id;
        this.country_name = country_name;
        this.country_alliance = country_alliance;
        this.population_1939 = population_1939;
        this.flag = flag;
        this.description = description;
    }
    public Nations(int id, String country_name,String flag){
        this.id=id;
        this.country_name=country_name;
        this.flag=flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCountry_alliance() {
        return country_alliance;
    }

    public void setCountry_alliance(String country_alliance) {
        this.country_alliance = country_alliance;
    }

    public int getPopulation_1939() {
        return population_1939;
    }

    public void setPopulation_1939(int population_1939) {
        this.population_1939 = population_1939;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
