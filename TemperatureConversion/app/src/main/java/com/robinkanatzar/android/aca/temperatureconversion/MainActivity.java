package com.robinkanatzar.android.aca.temperatureconversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        // assigns the text to the variable
        mFah = (TextView) findViewById(R.id.fahText); //sets mFah to what is in the fahText field
        // R stands for resource
        mCel = (TextView) findViewById(R.id.celText);
        mConvert = (Button) findViewById(R.id.convertButton);

        // click listener required to listen to events on a button
        // this will now execute whenever the button is clicked
        mConvert.setOnClickListener(new View.OnClickListener() {
            String currentFah;
            String convertedTemp;
            String currentCel;

            @Override
            public void onClick(View view) {
                // do our calculations inside here, so only goes when button clicked

                if (!mFah.getText().toString().equals("")) {
                    // if the fah field is not empty
                    // then convert from fah to cel

                    currentFah = mFah.getText().toString();
                    int fahValue = Integer.parseInt(currentFah); // new integer that was translated from string

                    fahValue = fahValue - 32;
                    fahValue = fahValue / 9;
                    fahValue = fahValue * 5;

                    convertedTemp = String.valueOf(fahValue);
                    mCel.setText(convertedTemp);

                } else if (!mCel.getText().toString().equals("")) {
                    // if the cel field is not empty
                    // convert from cel to fah

                    currentCel = mCel.getText().toString();
                    int celValue = Integer.parseInt(currentCel);

                    celValue = celValue * 9;
                    celValue = celValue / 5;
                    celValue = celValue + 32;

                    convertedTemp = String.valueOf(celValue);
                    mFah.setText(convertedTemp);

                }

            }
        });

        /*mFah.getText(); // built in method, will get the text currently inside text field



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

        System.out.println("converted fah: " + cel);*/
    }
}
