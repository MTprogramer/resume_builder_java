package com.resumemaker.resumecvbuilder.DataModels;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "skills_data")
public class SkillsData {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String skillName;
    private String skillLevel;

    public SkillsData(String skillName, String skillLevel) {
        this.skillName = skillName;
        this.skillLevel = skillLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
