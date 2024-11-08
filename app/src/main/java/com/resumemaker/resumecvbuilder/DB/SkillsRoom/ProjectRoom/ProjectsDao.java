package com.resumemaker.resumecvbuilder.DB.SkillsRoom.ProjectRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.resumemaker.resumecvbuilder.DataModels.ProjectsData;
import com.resumemaker.resumecvbuilder.DataModels.SkillsData;

import java.util.List;

@Dao
public interface ProjectsDao {
    @Insert
    void insert(ProjectsData projectsData);

    @Query("SELECT * FROM projects_data")
    List<ProjectsData> getAllProjectsData();

    @Update
    void update(ProjectsData projectsData);

    @Delete
    void delete(ProjectsData projectsData);
}
