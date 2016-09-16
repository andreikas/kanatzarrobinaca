package com.robinkanatzar.android.aca.databasepractice.db;

import android.content.Context;
import android.database.Cursor;
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
    public void insertEntry(SQLiteDatabase db, String name, String description) {
        mDatabase.beginTransaction();

        Log.d("RCK", "Inside insertEntry()");

        final String Insert_Data="INSERT INTO TABLE_NAME VALUES(SampleTitle, SampleDescription)";
        db.execSQL(Insert_Data);
        /*
        try {
            for (Forecast.HourData hour : forecast.hourly.data) {
                ContentValues values = new ContentValues();
                values.put(ForecastHelper.COLUMN_TEMPERATURE, hour.temperature);
                mDatabase.insert(ForecastHelper.TABLE_TEMPERATURES, null, values);
            }
            mDatabase.setTransactionSuccessful();
        }
        finally {
            mDatabase.endTransaction();
        }*/

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

        return cursor;
    }

    // TODO: add more select clauses
    /*
    // SELECT WHERE
    public Cursor selectTempsGreaterThan(String minTemp) {
        String whereClause = ForecastHelper.COLUMN_TEMPERATURE + " > ?";

        Cursor cursor = mDatabase.query(
                ForecastHelper.TABLE_TEMPERATURES, // table
                new String[] { ForecastHelper.COLUMN_TEMPERATURE }, // column names
                whereClause, // where clause
                new String[] { minTemp }, // where params
                null, // groupby
                null, // having
                null  // orderby
        );

        return cursor;
    }*/

    // UPDATE Operations
    // TODO: add update operations

    public int updateTemperature(String newText) {
        Log.d("RCK", "Inside updateTemperature()");
        /*
        ContentValues values = new ContentValues();
        values.put(ForecastHelper.COLUMN_TEMPERATURE, newTemp);
        int rowsUpdated = mDatabase.update(
                ForecastHelper.TABLE_TEMPERATURES, // table
                values, // values
                null,   // where clause
                null    // where params
        );*/

        int rowsUpdated = 1; // TODO: delete this when the update works properly
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
