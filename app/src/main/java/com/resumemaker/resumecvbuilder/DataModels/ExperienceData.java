package com.resumemaker.resumecvbuilder.DataModels;

public class ExperienceData {
    private String designation;
    private String join_date;
    private String end_date;
    private String company_name;
    private String experience_des;

    public ExperienceData(String designation, String join_date, String end_date, String company_name, String experience_des) {
        this.designation = designation;
        this.join_date = join_date;
        this.end_date = end_date;
        this.company_name = company_name;
        this.experience_des = experience_des;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getJoinDate() {
        return join_date;
    }

    public void setJoinDate(String join_date) {
        this.join_date = join_date;
    }

    public String getEndDate() {
        return end_date;
    }

    public void setEndDate(String end_date) {
        this.end_date = end_date;
    }

    public String getCompanyName() {
        return company_name;
    }

    public void setCompanyName(String company_name) {
        this.company_name = company_name;
    }

    public String getExperienceDes() {
        return experience_des;
    }

    public void setExperienceDes(String experience_des) {
        this.experience_des = experience_des;
    }
}
