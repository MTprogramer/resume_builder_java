package com.resumemaker.resumecvbuilder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class SkillDBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "skillDatabaseTest";
    private static final int DB_VERSION = 5;
    private static final String ID_COL = "id";
    private static final String SKILL_FOURTH_COL = "skillFourth";
    private static final String SKILL_FOURTH_LEVEL = "skillFourthlevel";
    private static final String SKILL_ONE_COL = "skillOne";
    private static final String SKILL_ONE_LEVEL = "skillOnelevel";
    private static final String SKILL_THREE_COL = "skillThree";
    private static final String SKILL_THREE_LEVEL = "skillThreelevel";
    private static final String SKILL_TWO_COL = "skillTwo";
    private static final String SKILL_TWO_LEVEL = "skillTwolevel";
    private static final String TABLE_NAME = "employeSkillTest";

    public SkillDBHandler(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 5);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE employeSkillTest (id INTEGER PRIMARY KEY AUTOINCREMENT, skillOne TEXT,skillTwo TEXT,skillThree TEXT,skillFourth TEXT,skillOnelevel TEXT,skillTwolevel TEXT,skillThreelevel TEXT,skillFourthlevel TEXT)");
    }

    public Boolean addNewCourse(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SKILL_ONE_COL, str);
        contentValues.put(SKILL_TWO_COL, str2);
        contentValues.put(SKILL_THREE_COL, str3);
        contentValues.put(SKILL_FOURTH_COL, str4);
        contentValues.put(SKILL_ONE_LEVEL, str5);
        contentValues.put(SKILL_TWO_LEVEL, str6);
        contentValues.put(SKILL_THREE_LEVEL, str7);
        contentValues.put(SKILL_FOURTH_LEVEL, str8);
        writableDatabase.insert(TABLE_NAME, (String) null, contentValues);
        writableDatabase.close();
        return null;
    }

    public ArrayList<SkillRecylerviewModel> readCourses() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT * FROM employeSkillTest", (String[]) null);
        ArrayList<SkillRecylerviewModel> arrayList = new ArrayList<>();
        if (rawQuery.moveToFirst()) {
            do {
                arrayList.add(new SkillRecylerviewModel(rawQuery.getString(1), rawQuery.getString(2), rawQuery.getString(3), rawQuery.getString(4), rawQuery.getString(5), rawQuery.getString(6), rawQuery.getString(7), rawQuery.getString(8)));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        return arrayList;
    }

    public void deleteCourse(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(TABLE_NAME, "skillone=?", new String[]{str});
        writableDatabase.close();
    }

    public void updateCourse(String str, String str2, String str3, String str4, String str5) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SKILL_ONE_COL, str2);
        contentValues.put(SKILL_TWO_COL, str3);
        contentValues.put(SKILL_THREE_COL, str4);
        contentValues.put(SKILL_FOURTH_COL, str5);
        writableDatabase.update(TABLE_NAME, contentValues, "skillone=?", new String[]{str});
        writableDatabase.close();
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS employeSkillTest");
        onCreate(sQLiteDatabase);
    }

    /* access modifiers changed from: package-private */
    public void deleteData() {
        getWritableDatabase().execSQL("delete from employeSkillTest");
    }

    public int getDataCount() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT  * FROM employeSkillTest", (String[]) null);
        int count = rawQuery.getCount();
        rawQuery.close();
        return count;
    }
}
