package com.robinkanatzar.android.aca.temperatureconversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // new
    TextView mFah; // text user enters
    TextView mCel; // text user enters
    Button mConvert; // button user interacts with



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        float fah = 86; //fahrenheit temperature
        System.out.println("starting fah: " + fah);

        // subtract 32
        fah = fah - 32;

        // divide by 9
        fah = fah / 9;

        // multiply by 5
        fah = fah * 5;

        System.out.println("converted cel: " + fah);

        float cel = 3;
        System.out.println("starting cel: " + cel);

        // multiply by 9
        cel = cel * 9;

        // divide by 5
        cel = cel / 5;

        // add 32
        cel = cel + 32;

        System.out.println("converted fah: " + cel);
    }
}
