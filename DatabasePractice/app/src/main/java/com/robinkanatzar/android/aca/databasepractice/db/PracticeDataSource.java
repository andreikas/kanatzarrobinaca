package com.robinkanatzar.android.aca.databasepractice.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PracticeDataSource {

    private SQLiteDatabase mDatabase; // declare my database
    private PracticeHelper mPracticeHelper; // declare an instance of the PracticeHelper class
    private Context mContext; // declare a variable for the context

    // constructor for the class
    public PracticeDataSource(Context context) {
        mContext = context;
        mPracticeHelper = new PracticeHelper(mContext);
    }

    // method to open the database
    public void open() throws SQLException {
        mDatabase = mPracticeHelper.getWritableDatabase();
    }

    // method to close the database
    public void close() {
        mDatabase.close();
    }

    // --------------------------------
    // CRUD Operations
    //---------------------------------

    // INSERT
    public void insertEntry(String name, String description) {
        mDatabase.beginTransaction();

        Log.d("RCK", "Inside insertEntry()");

        final String Insert_Data="INSERT INTO " + PracticeHelper.TABLE_NAME + " (" + PracticeHelper.COLUMN_1 + ", " + PracticeHelper.COLUMN_2 + ") VALUES('" + name + "', '" + description + "');";
        mDatabase.execSQL(Insert_Data);

        mDatabase.endTransaction();

    }

    // SELECT
    public Cursor selectAllEntries() {
        Log.d("RCK", "Inside selectAllEntries()");

        Cursor cursor = mDatabase.query(
                PracticeHelper.TABLE_NAME, // table
                new String[] { PracticeHelper.COLUMN_ID, PracticeHelper.COLUMN_1, PracticeHelper.COLUMN_2 }, // column names
                null, // where clause
                null, // where params
                null, // groupby
                null, // having
                null  // orderby
        );

        Log.d("RCK", "dumpCursorToString: " + DatabaseUtils.dumpCursorToString(cursor));
        return cursor;
    }

    // UPDATE Operations
    // TODO: add update operations

    public int updateTemperature(String newText) {
        Log.d("RCK", "Inside updateTemperature()");

        ContentValues values = new ContentValues();
        values.put(PracticeHelper.COLUMN_1, newText);
        int rowsUpdated = mDatabase.update(
                PracticeHelper.TABLE_NAME, // table
                values, // values
                null,   // where clause
                null    // where params
        );

        return rowsUpdated;
    }

    // DELETE Operations
    public void deleteAll() {
        Log.d("RCK", "Inside deleteAll()");
        mDatabase.delete(
                PracticeHelper.TABLE_NAME, // class.table name to delete
                null, // where clause
                null  // where params
        );
    }

}
