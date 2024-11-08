package com.resumemaker.resumecvbuilder.DB.SkillsRoom.ExperienceRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.resumemaker.resumecvbuilder.DataModels.EducationData;
import com.resumemaker.resumecvbuilder.DataModels.ExperienceData;

import java.util.List;

@Dao
public interface ExperienceDao {
    @Insert
    void insert(ExperienceData experienceData);

    @Query("SELECT * FROM experience_data")
    List<ExperienceData> getAllExperienceData();

    @Update
    void update(ExperienceData experienceData);

    @Delete
    void delete(ExperienceData experienceData);
}
