package com.resumemaker.resumecvbuilder.DataModels;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "personal_info")
public class PersonalInfo {
    @PrimaryKey
    private int id = 1;

    private String imageUri;
    private String fullName;
    private String profession;
    private String email;
    private String phoneNo;
    private String address;
    private String obj;

    public PersonalInfo(String imageUri, String fullName, String profession, String email, String phoneNo, String address , String obj) {
        this.imageUri = imageUri;
        this.fullName = fullName;
        this.profession = profession;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.obj = obj;
    }

    // Getters and Setters


    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
