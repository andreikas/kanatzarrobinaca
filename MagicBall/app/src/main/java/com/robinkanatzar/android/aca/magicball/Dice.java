package com.robinkanatzar.android.aca.magicball;

import android.util.Log;

import java.util.Random;

public class Dice {

    public int getDiceRoll() {

        int min = 1; // min number on one die
        int max = 6; // max number on one die

        Random randomGenerator = new Random();
        int randomNum1 = randomGenerator.nextInt(max - min) + min;
        Log.d("RCK", "randomNum1 = " + randomNum1);
        int randomNum2 = randomGenerator.nextInt(max - min) + min;
        Log.d("RCK", "randomNum2 = " + randomNum2);
        int totalRoll = randomNum1 + randomNum2;

        return totalRoll;
    }
}
