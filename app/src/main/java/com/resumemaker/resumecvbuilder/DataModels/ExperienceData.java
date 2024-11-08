package com.resumemaker.resumecvbuilder.DataModels;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "experience_data")
public class ExperienceData {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String designation;
    private String join_date;
    private String end_date;
    private String company_name;
    private String experience_des;
    private boolean isWorking = false;

    public ExperienceData(String designation, String join_date, String end_date, String company_name, String experience_des, boolean isWorking) {
        this.designation = designation;
        this.join_date = join_date;
        this.end_date = end_date;
        this.company_name = company_name;
        this.experience_des = experience_des;
        this.isWorking = isWorking;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getJoin_date() {
        return join_date;
    }

    public void setJoin_date(String join_date) {
        this.join_date = join_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getExperience_des() {
        return experience_des;
    }

    public void setExperience_des(String experience_des) {
        this.experience_des = experience_des;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    // Constructor, getters, and setters remain the same
}

