package com.resumemaker.resumecvbuilder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class EducationDBHandler extends SQLiteOpenHelper {
    private static final String COMPLETE_DATE_COL = "completiondate";
    private static final String DB_NAME = "educationdb";
    private static final int DB_VERSION = 1;
    private static final String DEGREE_COL = "degreetitle";
    private static final String ID_COL = "id";
    private static final String ORGANIZTION_NAME_COL = "organizationname";
    private static final String SCORE_COL = "score";
    private static final String TABLE_NAME = "employeeducation";

    public EducationDBHandler(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE employeeducation (id INTEGER PRIMARY KEY AUTOINCREMENT, organizationname TEXT,degreetitle TEXT,score TEXT,completiondate TEXT)");
    }

    public Boolean addNewCourse(String str, String str2, String str3, String str4) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ORGANIZTION_NAME_COL, str);
        contentValues.put(DEGREE_COL, str2);
        contentValues.put("score", str3);
        contentValues.put(COMPLETE_DATE_COL, str4);
        writableDatabase.insert(TABLE_NAME, (String) null, contentValues);
        writableDatabase.close();
        return null;
    }

    public ArrayList<EducationRecylerviewModel> readCourses() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT * FROM employeeducation", (String[]) null);
        ArrayList<EducationRecylerviewModel> arrayList = new ArrayList<>();
        if (rawQuery.moveToFirst()) {
            do {
                arrayList.add(new EducationRecylerviewModel(rawQuery.getString(1), rawQuery.getString(2), rawQuery.getString(3), rawQuery.getString(4)));
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

    public void updateCourse(String str, String str2, String str3, String str4, String str5) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ORGANIZTION_NAME_COL, str2);
        contentValues.put(DEGREE_COL, str3);
        contentValues.put("score", str4);
        contentValues.put(COMPLETE_DATE_COL, str5);
        writableDatabase.update(TABLE_NAME, contentValues, "organizationname=?", new String[]{str});
        writableDatabase.close();
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS employeeducation");
        onCreate(sQLiteDatabase);
    }

    /* access modifiers changed from: package-private */
    public void deleteData() {
        getWritableDatabase().execSQL("delete from employeeducation");
    }

    public int getDataCount() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT  * FROM employeeducation", (String[]) null);
        int count = rawQuery.getCount();
        rawQuery.close();
        return count;
    }
}
