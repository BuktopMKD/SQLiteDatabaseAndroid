package com.denofdevelopers.blogdbmanager_parttwo;

import android.content.ContentValues;
import android.content.Context;
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

    // Insert Data
    public boolean insertData(String name, String lastName, String dateOfBirth, String gender) {

        // Creating the DB
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        // Object for inserting data in to DB
        ContentValues contentValues = new ContentValues();

        // .put for inserting preparing data for inserting in to DB
        // arg1 for column name
        // arg2 for actual data
        contentValues.put(COL_2_NAME, name);
        contentValues.put(COL_3_LAST_NAME, lastName);
        contentValues.put(COL_4_DATE_OF_BIRTH, dateOfBirth);
        contentValues.put(COL_5_GENDER, gender);

        // Actual inserting the data in to Student.db
        // arg1 table name
        // arg2 nullHackStuff
        // arg3 content values
        long insertResult = sqLiteDatabase.insert(STUDENT_TABLE, null, contentValues);

        // .insert method returns row id
        // if there is error in inserting it will return -1
        if (insertResult != -1) {
            return true;
        } else {
            return false;
        }

        // Simplified return (same as above)
        // return insertResult != -1;
    }
}
