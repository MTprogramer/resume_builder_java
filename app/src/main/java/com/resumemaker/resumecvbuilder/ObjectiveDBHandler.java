package com.resumemaker.resumecvbuilder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class ObjectiveDBHandler extends SQLiteOpenHelper {
    private static final String COLUMN_NAME_OBJECTIVE = "objective_column";
    private static final String DB_NAME = "cvobjectives_db";
    private static final int DB_VERSION = 1;
    private static final String ID_COL = "id";
    private static final String TABLE_NAME_CV_OBJECTIVE = "cv_objective_table";

    public ObjectiveDBHandler(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE cv_objective_table (id INTEGER PRIMARY KEY AUTOINCREMENT, objective_column TEXT)");
    }

    public void addNewCourse(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_OBJECTIVE, str);
        writableDatabase.insert(TABLE_NAME_CV_OBJECTIVE, (String) null, contentValues);
        writableDatabase.close();
    }

    public ArrayList<Objective_Model> readCourses() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT * FROM cv_objective_table", (String[]) null);
        ArrayList<Objective_Model> arrayList = new ArrayList<>();
        if (rawQuery.moveToFirst()) {
            do {
                arrayList.add(new Objective_Model(rawQuery.getString(1)));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        return arrayList;
    }

    public void updateCourse(String str, String str2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_OBJECTIVE, str2);
        writableDatabase.update(TABLE_NAME_CV_OBJECTIVE, contentValues, "objective_column=?", new String[]{str});
        writableDatabase.close();
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cv_objective_table");
        onCreate(sQLiteDatabase);
    }

    /* access modifiers changed from: package-private */
    public void deleteData() {
        getWritableDatabase().execSQL("delete from cv_objective_table");
    }

    public int getDataCount() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT  * FROM cv_objective_table", (String[]) null);
        int count = rawQuery.getCount();
        rawQuery.close();
        return count;
    }
}
