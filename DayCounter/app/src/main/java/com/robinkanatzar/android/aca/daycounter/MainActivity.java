package com.robinkanatzar.android.aca.daycounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

/*
    displays every date in a given year in a single list from January 1 to December 31

    You’ll need to craft a nested for loop to accomplish this
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int yearIn = 2016;
        int monthIn;
        int dayIn;
        int totalDaysInMonth; // total days in the month (value of monthIn variable)

        for(monthIn = 1; monthIn < 13; monthIn++) {
            totalDaysInMonth = countDays(monthIn, yearIn) + 1;
            for (dayIn = 1; dayIn < totalDaysInMonth; dayIn++) {
                System.out.println(monthIn + "/" + dayIn + "/" + yearIn + "\n");
            }
        }
    }

    private int countDays(int month, int year) {
        int count = -1;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                count = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                count = 30;
                break;
            case 2:
                if (year % 4 == 0) {
                    count = 29;
                } else {
                    count = 28;
                }

                if ((year % 100 == 0) && (year % 400 != 0)) {
                    count = 28;
                }
        }
        return count;
    }

}
