package com.resumemaker.resumecvbuilder.DataModels;

public class ProjectsData {
    private String projectName;
    private String projectUrl;

    public ProjectsData(String projectName, String projectUrl) {
        this.projectName = projectName;
        this.projectUrl = projectUrl;
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
