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

        spirit.showAttributes();
        System.out.println("Increasing speed to 3.");
        spirit.speed = 3;
        spirit.showAttributes();
        System.out.println("Changing temperature to -90");
        spirit.temperature = -90;
        spirit.showAttributes();
        System.out.println("Checking temperature");
        spirit.checkTemperature();
        spirit.showAttributes();

        MarsRobot moody = new MarsRobot();

        moody.mood = "angry";

        System.out.println("Reading the robot's mood...");
        moody.readingMood();

        System.out.println("Telling the robot a joke...");
        moody.mood = "happy";
        moody.readingMood();

    }
}
