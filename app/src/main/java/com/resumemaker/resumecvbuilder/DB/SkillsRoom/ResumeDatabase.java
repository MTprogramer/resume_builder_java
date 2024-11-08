package com.resumemaker.resumecvbuilder.DB.SkillsRoom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.resumemaker.resumecvbuilder.DB.SkillsRoom.EducationRoom.EducationDao;
import com.resumemaker.resumecvbuilder.DB.SkillsRoom.ExperienceRoom.ExperienceDao;
import com.resumemaker.resumecvbuilder.DB.SkillsRoom.PersonalInfoRoom.PersonalInfoDao;
import com.resumemaker.resumecvbuilder.DB.SkillsRoom.ProjectRoom.ProjectsDao;
import com.resumemaker.resumecvbuilder.DataModels.EducationData;
import com.resumemaker.resumecvbuilder.DataModels.ExperienceData;
import com.resumemaker.resumecvbuilder.DataModels.PersonalInfo;
import com.resumemaker.resumecvbuilder.DataModels.ProjectsData;
import com.resumemaker.resumecvbuilder.DataModels.SkillsData;

@Database(entities = {EducationData.class, ExperienceData.class, ProjectsData.class, SkillsData.class, PersonalInfo.class}, version = 2, exportSchema = false)
public abstract class ResumeDatabase extends RoomDatabase {
    // DAOs provide access to each table
    public abstract EducationDao educationDao();
    public abstract ExperienceDao experienceDao();
    public abstract ProjectsDao projectsDao();
    public abstract SkillsDao skillsDao();
    public abstract PersonalInfoDao personalInfoDao();

    // Singleton instance for the entire database
    private static ResumeDatabase instance;

    public static synchronized ResumeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ResumeDatabase.class, "resume_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
