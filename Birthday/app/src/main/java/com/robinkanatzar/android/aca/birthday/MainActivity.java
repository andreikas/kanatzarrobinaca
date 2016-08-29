package com.robinkanatzar.android.aca.birthday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // declare variables
        String mMonth; // month string
        String mDay; // day string
        String mYear; // year string
        String mOriginalDate; // original date

        mOriginalDate = "09/08/1985"; // set the original date
        mMonth = mOriginalDate.substring(1,2);

        System.out.println("mMonth: " + mMonth);
    }
}
