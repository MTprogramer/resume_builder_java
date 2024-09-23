package com.resumemaker.resumecvbuilder.DataModels;

public class SkillsData {
    private String skillName;
    private String skillLevel;

    public SkillsData(String skillName, String skillLevel) {
        this.skillName = skillName;
        this.skillLevel = skillLevel;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }
}
