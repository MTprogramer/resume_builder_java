package com.resumemaker.resumecvbuilder;

public class ExperienceModel {
    private String designation;
    private String endingDate;
    int id = this.id;
    private String jobDescription;
    private String joiningDate;
    private String organozationName;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getOrganozationName() {
        return this.organozationName;
    }

    public void setOrganozationName(String str) {
        this.organozationName = str;
    }

    public String getDesignation() {
        return this.designation;
    }

    public void setDesignation(String str) {
        this.designation = str;
    }

    public String getJoiningDate() {
        return this.joiningDate;
    }

    public void setJoiningDate(String str) {
        this.joiningDate = str;
    }

    public String getEndingDate() {
        return this.endingDate;
    }

    public void setEndingDate(String str) {
        this.endingDate = str;
    }

    public String getJobDescription() {
        return this.jobDescription;
    }

    public void setJobDescription(String str) {
        this.jobDescription = str;
    }

    public ExperienceModel(String str, String str2, String str3, String str4, String str5) {
        this.organozationName = str;
        this.designation = str2;
        this.joiningDate = str3;
        this.endingDate = str4;
        this.jobDescription = str5;
    }
}
