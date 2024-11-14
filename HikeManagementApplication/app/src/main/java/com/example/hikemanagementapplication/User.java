package com.example.hikemanagementapplication;

import java.io.Serializable;

public class User implements Serializable {

    private long id;//primary key
    private String name;
    private String email;
    private String gender;
    private String location;
    private String dob;
    private String parking;
    private String length;
    private String level;
    private String description;

//    public User( String name, String email, String gender, String location, String dob, String parking, String length, String level, String description) {
//        this.name = name;
//        this.email = email;
//        this.gender = gender;
//        this.location = location;
//        this.dob = dob;
//        this.parking = parking;
//        this.length = length;
//        this.level = level;
//        this.description = description;
//    }//end of constructor

    public User(long id, String name, String email, String gender, String location, String dob, String parking, String length, String level, String description) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.location = location;
        this.dob = dob;
        this.parking = parking;
        this.length = length;
        this.level = level;
        this.description = description;
    }//end of constructor


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}//end of model class
