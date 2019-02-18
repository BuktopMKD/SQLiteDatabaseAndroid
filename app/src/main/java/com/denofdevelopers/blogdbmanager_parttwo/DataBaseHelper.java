package com.denofdevelopers.blogdbmanager_parttwo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    // DB information
    public static final String DB_NAME = "Person.db";              // Camel case don`t apply & location of the file after creating is in data/data/app on device
    public static final String STUDENT_TABLE = "student_table";    // Table name
    public static final String COL_1_ID = "id";                    // Column name
    public static final String COL_2_NAME = "name";
    public static final String COL_3_LAST_NAME = "last_name";
    public static final String COL_4_DATE_OF_BIRTH = "date_of_birth";
    public static final String COL_5_GENDER = "gender";
    public static final int VERSION = 1;

    public DataBaseHelper(Context context) {
        // Here the DB is created
        // Whenever this constructor is called, database is created
        // Create the database with our own arguments
        super(context, DB_NAME, null, VERSION);

        // This below is just for checking on first run to see that the db is created
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Command that executes any quarry
        // Creating a table with given columns
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
}
