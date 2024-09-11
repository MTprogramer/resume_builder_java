package com.resumemaker.resumecvbuilder;

public class PersonalDetailsModel {
    private String address;
    private String contact;
    private String email;
    private int id = this.id;
    private String name;
    private String profession;

    public PersonalDetailsModel(String str, String str2, String str3, String str4, String str5) {
        this.name = str;
        this.profession = str2;
        this.address = str3;
        this.email = str4;
        this.contact = str5;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getProfession() {
        return this.profession;
    }

    public int getId() {
        return this.id;
    }

    public String getAddress() {
        return this.address;
    }

    public String getEmail() {
        return this.email;
    }

    public String getContact() {
        return this.contact;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void setContact(String str) {
        this.contact = str;
    }

    public void setProfession(String str) {
        this.profession = str;
    }
}
