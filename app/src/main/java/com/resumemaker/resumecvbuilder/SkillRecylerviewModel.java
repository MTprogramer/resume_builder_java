package com.resumemaker.resumecvbuilder;

public class SkillRecylerviewModel {
    private String skillFourth;
    private String skillOne;
    private String skillThree;
    private String skillTwo;
    private String skillfourthlevel;
    private String skillonelevel;
    private String skillthreelevel;
    private String skilltwolevel;

    public String getSkillonelevel() {
        return this.skillonelevel;
    }

    public void setSkillonelevel(String str) {
        this.skillonelevel = str;
    }

    public String getSkilltwolevel() {
        return this.skilltwolevel;
    }

    public void setSkilltwolevel(String str) {
        this.skilltwolevel = str;
    }

    public String getSkillthreelevel() {
        return this.skillthreelevel;
    }

    public void setSkillthreelevel(String str) {
        this.skillthreelevel = str;
    }

    public String getSkillfourthlevel() {
        return this.skillfourthlevel;
    }

    public void setSkillfourthlevel(String str) {
        this.skillfourthlevel = str;
    }

    public SkillRecylerviewModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.skillOne = str;
        this.skillTwo = str2;
        this.skillThree = str3;
        this.skillFourth = str4;
        this.skillonelevel = str5;
        this.skilltwolevel = str6;
        this.skillthreelevel = str7;
        this.skillfourthlevel = str8;
    }

    public String getSkillOne() {
        return this.skillOne;
    }

    public void setSkillOne(String str) {
        this.skillOne = str;
    }

    public String getSkillTwo() {
        return this.skillTwo;
    }

    public void setSkillTwo(String str) {
        this.skillTwo = str;
    }

    public String getSkillThree() {
        return this.skillThree;
    }

    public void setSkillThree(String str) {
        this.skillThree = str;
    }

    public String getSkillFourth() {
        return this.skillFourth;
    }

    public void setSkillFourth(String str) {
        this.skillFourth = str;
    }
}
