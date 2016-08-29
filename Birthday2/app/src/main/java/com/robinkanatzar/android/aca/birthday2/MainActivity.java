package com.robinkanatzar.android.aca.birthday2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // declare variables
        String mMonth;
        String mDay;
        String mYear;
        String mOriginal;

        mOriginal = "08/09/1985"; // set original date to convert

        mMonth = mOriginal.substring(0,2); // set month to the first two characters in the string
        mDay = mOriginal.substring(3,5); // set day to characters 3 and 4
        mYear = mOriginal.substring(6,10); // set year to the last four digits

        // print out each string
        System.out.println("month: " + mMonth);
        System.out.println("day: " + mDay);
        System.out.println("year: " + mYear);

    }
}
