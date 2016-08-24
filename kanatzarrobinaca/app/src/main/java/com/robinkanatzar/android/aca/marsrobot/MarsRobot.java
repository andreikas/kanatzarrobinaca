package com.robinkanatzar.android.aca.marsrobot;

/**
 * Created by robinkanatzar on 8/24/16.
 */
public class MarsRobot {
    String status;
    int speed;
    float temperature;

    void checkTemperature(){
        if (temperature < -80) {
            status = "returning home";
            speed = 5;
        }

    }

    void showAttributes() {


    }

}
