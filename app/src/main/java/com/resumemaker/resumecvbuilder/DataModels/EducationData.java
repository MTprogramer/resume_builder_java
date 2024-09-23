package com.resumemaker.resumecvbuilder.DataModels;

public class EducationData {
    private String completeDate;
    private String degree;
    private String instituteName;
    private String degreeDes;

    public EducationData(String completeDate, String degree, String instituteName, String degreeDes) {
        this.completeDate = completeDate;
        this.degree = degree;
        this.instituteName = instituteName;
        this.degreeDes = degreeDes;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getDegreeDes() {
        return degreeDes;
    }

    public void setDegreeDes(String degreeDes) {
        this.degreeDes = degreeDes;
    }
}
