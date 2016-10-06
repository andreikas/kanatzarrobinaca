package com.robinkanatzar.android.rck.databsepracticefortabou;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Random;

public class DataManager {

    private SQLiteDatabase db;
    public static final String TABLE_ROW_ID = "_id";
    public static final String TABLE_ROW_FR_WORD = "fr_word";
    public static final String TABLE_ROW_TABOO_1 = "taboo_word_1";
    public static final String TABLE_ROW_TABOO_2 = "taboo_word_2";
    public static final String TABLE_ROW_TABOO_3 = "taboo_word_3";
    public static final String TABLE_ROW_TABOO_4 = "taboo_word_4";
    public static final String TABLE_ROW_TABOO_5 = "taboo_word_5";

    private static final String DB_NAME = "tabou_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_N_AND_A = "tabou_db";

    public DataManager(Context context) {

        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
        db = helper.getWritableDatabase();
        addDefaultRows();
    }

    public Cursor getNewWord() {

        // find number of rows in the table
        String findNumRows = "SELECT COUNT(*) FROM " + TABLE_N_AND_A + ";";
        Cursor c = db.rawQuery(findNumRows, null);
        c.moveToFirst();
        int numRows = c.getInt(0);
        Log.d("RCK", "numRows: " + numRows);

        // produce a random number
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(numRows);
        Log.d("RCK", "Random number is: " + randomNumber);

        String selectAll = "SELECT * FROM " + TABLE_N_AND_A + ";";

        Cursor c2 = db.rawQuery(selectAll, null);

        String c1 = "";
        String C2 = "";
        String c3 = "";
        String c4 = "";
        String c5 = "";
        String c6 = "";

        int counter = 0;
        while (c2.moveToNext()){

            c1 = c2.getString(1);
            C2 = c2.getString(2);
            c3 = c2.getString(3);
            c4 = c2.getString(4);
            c5 = c2.getString(5);
            c6 = c2.getString(6);
            if (counter == randomNumber) {
                break;
            }
            counter = counter + 1;
        }

        Log.d("RCK", "c1-6..: " + c1 + C2 + c3 + c4 + c5 + c6);

        return c2;
    }

    public void addDefaultRows() {

        String deleteRows = "DELETE FROM " + TABLE_N_AND_A + ";";
        db.execSQL(deleteRows);

        // TODO add ten default rows of data
        String fillTableQueryString =
                "INSERT INTO "
                        + TABLE_N_AND_A
                        + " ("
                        + TABLE_ROW_FR_WORD
                        + ","
                        + TABLE_ROW_TABOO_1
                        + ","
                        + TABLE_ROW_TABOO_2
                        + ","
                        + TABLE_ROW_TABOO_3
                        + ","
                        + TABLE_ROW_TABOO_4
                        + ","
                        + TABLE_ROW_TABOO_5
                        + ") VALUES ('french word1','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word2','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word3','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word4','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word2','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word3','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word4','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word2','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word3','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word4','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word2','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word3','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word4','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word2','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word3','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word4','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word2','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word3','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word4','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word2','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word3','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word4','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word2','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word3','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word4','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word2','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word3','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word4','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word2','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word3','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word4','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word2','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word3','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word4','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word2','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word3','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word4','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word2','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word3','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word4','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word2','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word3','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word4','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word2','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word3','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word4','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5'),"
                        + "('french word5','taboo 1','taboo 2','taboo 3','taboo 4','taboo 5');";

        db.execSQL(fillTableQueryString);
    }

    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {
        public CustomSQLiteOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String newTableQueryString = "create table "
                    + TABLE_N_AND_A + " ("
                    + TABLE_ROW_ID
                    + " integer primary key autoincrement not null,"
                    + TABLE_ROW_FR_WORD
                    + " text not null,"
                    + TABLE_ROW_TABOO_1
                    + " text not null,"
                    + TABLE_ROW_TABOO_2
                    + " text not null,"
                    + TABLE_ROW_TABOO_3
                    + " text not null,"
                    + TABLE_ROW_TABOO_4
                    + " text not null,"
                    + TABLE_ROW_TABOO_5
                    + " text not null);";

            db.execSQL(newTableQueryString);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {

        }
    }
}
