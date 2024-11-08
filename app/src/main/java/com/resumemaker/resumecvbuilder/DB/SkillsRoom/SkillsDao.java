package com.resumemaker.resumecvbuilder.DB.SkillsRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.resumemaker.resumecvbuilder.DataModels.ExperienceData;
import com.resumemaker.resumecvbuilder.DataModels.SkillsData;

import java.util.List;

@Dao
public interface SkillsDao {
    @Insert
    void insert(SkillsData skillsData);

    @Query("SELECT * FROM skills_data")
    List<SkillsData> getAllSkillsData();

    @Update
    void update(SkillsData skillsData);

    @Delete
    void delete(SkillsData skillsData);
}
