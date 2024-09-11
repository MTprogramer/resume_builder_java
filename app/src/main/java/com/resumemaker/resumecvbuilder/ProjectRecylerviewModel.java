package com.resumemaker.resumecvbuilder;

public class ProjectRecylerviewModel {
    private String experience;
    private String joinDate;
    private String projectNameOne;
    private String projectNameTwo;
    private String projectOneUrl;
    private String projectTwoUrl;

    public String getProjectNameTwo() {
        return this.projectNameTwo;
    }

    public void setProjectNameTwo(String str) {
        this.projectNameTwo = str;
    }

    public String getProjectTwoUrl() {
        return this.projectTwoUrl;
    }

    public void setProjectTwoUrl(String str) {
        this.projectTwoUrl = str;
    }

    public ProjectRecylerviewModel(String str, String str2, String str3, String str4) {
        this.projectNameOne = str;
        this.projectOneUrl = str2;
        this.projectNameTwo = str3;
        this.projectTwoUrl = str4;
    }

    public String getExperience() {
        return this.experience;
    }

    public void setExperience(String str) {
        this.experience = str;
    }

    public String getProjectNameOne() {
        return this.projectNameOne;
    }

    public void setProjectNameOne(String str) {
        this.projectNameOne = str;
    }

    public String getProjectOneUrl() {
        return this.projectOneUrl;
    }

    public void setProjectOneUrl(String str) {
        this.projectOneUrl = str;
    }

    public String getJoinDate() {
        return this.joinDate;
    }

    public void setJoinDate(String str) {
        this.joinDate = str;
    }

    public ProjectRecylerviewModel(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2) {
    }
}
