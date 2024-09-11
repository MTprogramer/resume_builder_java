package com.resumemaker.resumecvbuilder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class ExperienceDBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "experiencedb";
    private static final int DB_VERSION = 1;
    private static final String DESIGNATION_COL = "designation";
    private static final String END_DATE_COL = "enddate";
    private static final String ID_COL = "id";
    private static final String JOB_DESCRIPTION_COL = "description";
    private static final String JOIN_DATE_COL = "joindate";
    private static final String ORGANIZTION_NAME_COL = "organizationname";
    private static final String TABLE_NAME = "employeexperience";

    public ExperienceDBHandler(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE employeexperience (id INTEGER PRIMARY KEY AUTOINCREMENT, organizationname TEXT,designation TEXT,joindate TEXT,enddate TEXT,description TEXT)");
    }

    public Boolean addNewCourse(String str, String str2, String str3, String str4, String str5) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ORGANIZTION_NAME_COL, str);
        contentValues.put(DESIGNATION_COL, str2);
        contentValues.put(JOIN_DATE_COL, str3);
        contentValues.put(END_DATE_COL, str4);
        contentValues.put(JOB_DESCRIPTION_COL, str5);
        writableDatabase.insert(TABLE_NAME, (String) null, contentValues);
        writableDatabase.close();
        return null;
    }

    public ArrayList<ExperienceRecylerviewModel> readCourses() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT * FROM employeexperience", (String[]) null);
        ArrayList<ExperienceRecylerviewModel> arrayList = new ArrayList<>();
        if (rawQuery.moveToFirst()) {
            do {
                arrayList.add(new ExperienceRecylerviewModel(rawQuery.getString(1), rawQuery.getString(2), rawQuery.getString(3), rawQuery.getString(4), rawQuery.getString(5)));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        return arrayList;
    }

    public void deleteCourse(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(TABLE_NAME, "organizationname=?", new String[]{str});
        writableDatabase.close();
        Log.d("deleted", "this deleted" + str);
    }

    public void updateCourse(String str, String str2, String str3, String str4, String str5, String str6) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ORGANIZTION_NAME_COL, str2);
        contentValues.put(DESIGNATION_COL, str3);
        contentValues.put(JOIN_DATE_COL, str4);
        contentValues.put(END_DATE_COL, str5);
        contentValues.put(JOB_DESCRIPTION_COL, str6);
        writableDatabase.update(TABLE_NAME, contentValues, "organizationname=?", new String[]{str});
        writableDatabase.close();
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS employeexperience");
        onCreate(sQLiteDatabase);
    }

    /* access modifiers changed from: package-private */
    public void deleteData() {
        getWritableDatabase().execSQL("delete from employeexperience");
    }

    public int getDataCount() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT  * FROM employeexperience", (String[]) null);
        int count = rawQuery.getCount();
        rawQuery.close();
        return count;
    }
}
