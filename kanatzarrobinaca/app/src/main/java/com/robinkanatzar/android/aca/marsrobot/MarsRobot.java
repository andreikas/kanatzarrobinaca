package com.robinkanatzar.android.aca.marsrobot;

/**
 * Created by robinkanatzar on 8/24/16.
 */
public class MarsRobot {
    String status;
    int speed;
    float temperature;
    String mood; // mood of robot
    Boolean useful; // is the robot useful today or not, 1 is good 0 is not useful

    void checkTemperature(){
        if (temperature < -80) {
            status = "returning home";
            speed = 5;
        }

    }

    void showAttributes() {
        System.out.println("Status: " + status);
        System.out.println("Speed: " + speed);
        System.out.println("Temperature: " + temperature);

    }

    void readingMood() {
        // reads the robot's mood
        System.out.println("This robot is feeling: " + mood);
        if (mood == "angry") {
            useful = false; // robot is useless if angry and will not work
            System.out.println("This robot is too angry to work today");
        }
        if (mood == "happy") {
            useful = true; // robot can work today because it is in a good mood
            System.out.println("This robot is in a good mood. S/he can work today");
        }

    }

}
