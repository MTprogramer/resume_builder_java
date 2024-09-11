package com.resumemaker.resumecvbuilder;

public class EducationRecylerviewModel {
    private String completiondate;
    private String degreetitle;
    int deleteImg;
    private String designation;
    private String experience;
    private String jobDescriptions;
    private String joinDate;
    private String organizationname;
    private String score;
    int updateImg;

    public String getDegreetitle() {
        return this.degreetitle;
    }

    public void setDegreetitle(String str) {
        this.degreetitle = str;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String str) {
        this.score = str;
    }

    public String getCompletiondate() {
        return this.completiondate;
    }

    public void setCompletiondate(String str) {
        this.completiondate = str;
    }

    public String getJobDescriptions() {
        return this.jobDescriptions;
    }

    public void setJobDescriptions(String str) {
        this.jobDescriptions = str;
    }

    public EducationRecylerviewModel(String str, String str2, String str3, String str4) {
        this.organizationname = str;
        this.degreetitle = str2;
        this.score = str3;
        this.completiondate = str4;
        this.jobDescriptions = this.jobDescriptions;
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

    public String getJoinDate() {
        return this.joinDate;
    }

    public void setJoinDate(String str) {
        this.joinDate = str;
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

    public EducationRecylerviewModel(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2) {
        this.experience = str;
        this.organizationname = str2;
        this.designation = str3;
        this.joinDate = str4;
        this.deleteImg = i;
        this.updateImg = i2;
    }
}
