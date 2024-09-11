package com.resumemaker.resumecvbuilder;

public class ExperienceRecylerviewModel {
    int deleteImg;
    private String designation;
    private String designations;
    private String endingDate;
    private String experience;
    private String jobDescriptions;
    private String joiningDate;
    private String organizationname;
    int updateImg;

    public String getDesignations() {
        return this.designations;
    }

    public void setDesignations(String str) {
        this.designations = str;
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

    public String getJobDescriptions() {
        return this.jobDescriptions;
    }

    public void setJobDescriptions(String str) {
        this.jobDescriptions = str;
    }

    public ExperienceRecylerviewModel(String str, String str2, String str3, String str4, String str5) {
        this.organizationname = str;
        this.designations = str2;
        this.joiningDate = str3;
        this.endingDate = str4;
        this.jobDescriptions = str5;
    }

    public String getExperience() {
        return this.experience;
    }

    public void setExperience(String str) {
        this.experience = str;
    }

    public String getOrganizationname() {
        return this.organizationname;
    }

    public void setOrganizationname(String str) {
        this.organizationname = str;
    }

    public String getDesignation() {
        return this.designation;
    }

    public void setDesignation(String str) {
        this.designation = str;
    }

    public int getDeleteImg() {
        return this.deleteImg;
    }

    public void setDeleteImg(int i) {
        this.deleteImg = i;
    }

    public int getUpdateImg() {
        return this.updateImg;
    }

    public void setUpdateImg(int i) {
        this.updateImg = i;
    }

    public ExperienceRecylerviewModel(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2) {
        this.experience = str;
        this.organizationname = str2;
        this.designation = str3;
        this.deleteImg = i;
        this.updateImg = i2;
    }
}
