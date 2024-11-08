package com.resumemaker.resumecvbuilder.DB.SkillsRoom.EducationRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.resumemaker.resumecvbuilder.DataModels.EducationData;

import java.util.List;

@Dao
public interface EducationDao {
    @Insert
    void insert(EducationData educationData);

    @Query("SELECT * FROM education_data")
    List<EducationData> getAllEducationData();


    @Update
    void update(EducationData educationData);

    @Delete
    void delete(EducationData educationData);
}
