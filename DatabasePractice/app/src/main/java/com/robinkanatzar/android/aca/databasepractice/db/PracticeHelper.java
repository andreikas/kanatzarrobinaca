package com.robinkanatzar.android.aca.databasepractice.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PracticeHelper extends SQLiteOpenHelper {

    // Table and column information
    public static final String TABLE_NAME = "TABLE_NAME"; // the name of the table
    public static final String COLUMN_ID = "_ID"; // this is the first column which just contains the key, id
    public static final String COLUMN_1 = "TITLE"; // the first column
    public static final String COLUMN_2 = "DESCRIPTION"; // the second column

    // database information
    private static final String DB_NAME = "practice.db"; // name of database
    private static final int DB_VERSION = 1; // Must increment to trigger an upgrade

    // SQL scripts
    private static final String DB_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_1 + " TEXT, " +
                    COLUMN_2 + " TEXT)";
    private static final String DB_ALTER =
            "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_2 + " TEXT";



    public PracticeHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // this call executes the string of SQL saved in DB_CREATE
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // used when I release a new version of the app that changes the database
        // should contain what needs to be changed (and how) to the database
        // don't forget to change the onCreate method as well, so it doesn't get
        // rebuilt the same exact way and override the new changes

        // this calls the alter script saved in the string
        db.execSQL(DB_ALTER);
    }
}
