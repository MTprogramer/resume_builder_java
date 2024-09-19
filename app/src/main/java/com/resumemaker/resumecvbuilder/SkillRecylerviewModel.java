package com.resumemaker.resumecvbuilder;

public class SkillRecylerviewModel {

    private String skillName;
    private String skillLevel;

    SkillRecylerviewModel(String str, String str2) {
        this.skillName = str;
        this.skillLevel = str2;
    }

    public void setSkillLevel(String str) {skillLevel = str;}
    public void setSkillName(String str) {skillName = str;}


    public String getSkillLevel() {return skillLevel;}
    public String getSkillName() {return skillName;}


}
