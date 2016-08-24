package com.robinkanatzar.android.aca.marsrobot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MarsRobot spirit = new MarsRobot();

        spirit.status = "exploring";
        spirit.speed = 2;
        spirit.temperature = -60;
    }
}
