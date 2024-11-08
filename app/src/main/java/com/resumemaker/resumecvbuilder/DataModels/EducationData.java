package com.resumemaker.resumecvbuilder.DataModels;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "education_data")
public class EducationData {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String completeDate;
    private String start_date;
    private String degree;
    private String instituteName;
    private String degreeDes;

    public EducationData(String completeDate, String start_date, String degree, String instituteName, String degreeDes) {
        this.completeDate = completeDate;
        this.start_date = start_date;
        this.degree = degree;
        this.instituteName = instituteName;
        this.degreeDes = degreeDes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
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


    // Constructor, getters, and setters remain the same
}
