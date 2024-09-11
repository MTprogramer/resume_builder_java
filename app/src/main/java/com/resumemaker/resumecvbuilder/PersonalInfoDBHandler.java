package com.resumemaker.resumecvbuilder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class PersonalInfoDBHandler extends SQLiteOpenHelper {
    private static final String COLUMN_NAME_ADDRESS = "address_column";
    private static final String COLUMN_NAME_CONTACT = "contact_column";
    private static final String COLUMN_NAME_EMAIL = "email_column";
    private static final String COLUMN_NAME_NAME = "name_column";
    private static final String COLUMN_NAME_PROFESSION = "profession_column";
    private static final String COLUMN_PROFILE_IMAGE = "profile_column";
    private static final String DB_NAME = "personal_details_db";
    private static final int DB_VERSION = 1;
    private static final String ID_COL = "id";
    private static final String TABLE_NAME_PERSONAL_DETAILS = "personal_details_table";

    public PersonalInfoDBHandler(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE personal_details_table (id INTEGER PRIMARY KEY AUTOINCREMENT, name_column TEXT,profession_column TEXT,contact_column TEXT,email_column TEXT,address_column TEXT)");
    }

    public void addNewCourse(String str, String str2, String str3, String str4, String str5) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_NAME, str);
        contentValues.put(COLUMN_NAME_PROFESSION, str2);
        contentValues.put(COLUMN_NAME_CONTACT, str3);
        contentValues.put(COLUMN_NAME_EMAIL, str4);
        contentValues.put(COLUMN_NAME_ADDRESS, str5);
        writableDatabase.insert(TABLE_NAME_PERSONAL_DETAILS, (String) null, contentValues);
        writableDatabase.close();
    }

    public ArrayList<PersonalDetailsModel> readCourses() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT * FROM personal_details_table", (String[]) null);
        ArrayList<PersonalDetailsModel> arrayList = new ArrayList<>();
        if (rawQuery.moveToFirst()) {
            do {
                arrayList.add(new PersonalDetailsModel(rawQuery.getString(1), rawQuery.getString(2), rawQuery.getString(5), rawQuery.getString(4), rawQuery.getString(3)));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        return arrayList;
    }

    public void updateCourse(String str, String str2, String str3, String str4, String str5, String str6) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_NAME, str2);
        contentValues.put(COLUMN_NAME_PROFESSION, str3);
        contentValues.put(COLUMN_NAME_CONTACT, str4);
        contentValues.put(COLUMN_NAME_EMAIL, str5);
        contentValues.put(COLUMN_NAME_ADDRESS, str6);
        writableDatabase.update(TABLE_NAME_PERSONAL_DETAILS, contentValues, "name_column=?", new String[]{str});
        writableDatabase.close();
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS personal_details_table");
        onCreate(sQLiteDatabase);
    }

    /* access modifiers changed from: package-private */
    public void deleteData() {
        getWritableDatabase().execSQL("delete from personal_details_table");
    }

    public int getDataCount() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT  * FROM personal_details_table", (String[]) null);
        int count = rawQuery.getCount();
        rawQuery.close();
        return count;
    }
}
