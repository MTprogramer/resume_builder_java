package com.resumemaker.resumecvbuilder.DB.SkillsRoom.PersonalInfoRoom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.resumemaker.resumecvbuilder.DataModels.ExperienceData;
import com.resumemaker.resumecvbuilder.DataModels.PersonalInfo;

@Dao
public interface PersonalInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdate(PersonalInfo personalInfo);


    @Query("DELETE FROM personal_info")
    void clearAll();

    @Query("SELECT * FROM personal_info WHERE id = 1 LIMIT 1")
    PersonalInfo getPersonalInfo();

    @Query("SELECT COUNT(*) FROM personal_info")
    int getCount();

    @Query("SELECT obj FROM personal_info WHERE id = 1 LIMIT 1")
    String getObj();

    @Query("UPDATE personal_info SET obj = :obj WHERE id = 1")
    void updateObj(String obj);
}
