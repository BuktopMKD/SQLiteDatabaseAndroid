package com.denofdevelopers.blogdbmanager_parttwo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Person.db";
    public static final String STUDENT_TABLE = "student_table";
    public static final String COL_1_ID = "id";
    public static final String COL_2_NAME = "name";
    public static final String COL_3_LAST_NAME = "last_name";
    public static final String COL_4_DATE_OF_BIRTH = "date_of_birth";
    public static final String COL_5_GENDER = "gender";
    public static final int VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "
                + STUDENT_TABLE + " ("
                + COL_1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_2_NAME + " TEXT, "
                + COL_3_LAST_NAME + " TEXT, "
                + COL_4_DATE_OF_BIRTH + " TEXT,"
                + COL_5_GENDER + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String lastName, String dateOfBirth, String gender) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_NAME, name);
        contentValues.put(COL_3_LAST_NAME, lastName);
        contentValues.put(COL_4_DATE_OF_BIRTH, dateOfBirth);
        contentValues.put(COL_5_GENDER, gender);
        long insertResult = sqLiteDatabase.insert(STUDENT_TABLE, null, contentValues);
        return insertResult != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + STUDENT_TABLE, null);
    }
}
