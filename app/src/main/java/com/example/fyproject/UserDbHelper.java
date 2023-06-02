package com.example.fyproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "login";
    public static final String TABLE_NAME = "health_conditions";
    public static final String COLUMN_CONDITION = "condition";

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the users table
        String SQL_CREATE_USERS_TABLE = "CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " ("
                + UserContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UserContract.UserEntry.COLUMN_USERNAME + " TEXT NOT NULL, "
                + UserContract.UserEntry.COLUMN_PASSWORD + " TEXT NOT NULL, "
                + UserContract.UserEntry.COLUMN_GENDER + " TEXT NOT NULL, "
                + UserContract.UserEntry.COLUMN_AGE + " INTEGER NOT NULL, "
                + UserContract.UserEntry.COLUMN_EMAIL_ADDRESS + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_USERS_TABLE);

        // Create the health_conditions table
        String SQL_CREATE_CONDITIONS_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_CONDITION + " TEXT PRIMARY KEY);";

        db.execSQL(SQL_CREATE_CONDITIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the users table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME);
        // Drop the health_conditions table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create the tables again
        onCreate(db);
    }
}
