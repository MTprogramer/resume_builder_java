package com.resumemaker.resumecvbuilder.DataModels;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "projects_data")
public class ProjectsData {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String projectName;
    private String projectUrl;

    public ProjectsData(String projectName, String projectUrl) {
        this.projectName = projectName;
        this.projectUrl = projectUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }
}

