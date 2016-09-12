package com.robinkanatzar.android.aca.accessscopethisandstatic;

import android.util.Log;

public abstract class AlienShip {


    private static int numShips;
    private int shieldStrength;
    public String shipName;

    public AlienShip(int shieldStrength){
        Log.i("Location: ", "AlienShip constructor");
        numShips++;
        setShieldStrength(shieldStrength);
    }

    public abstract void fireWeapon();
    // Ahh my body where is it?

    public static int getNumShips(){
        return numShips;
    }
    private void setShieldStrength(int shieldStrength){
        this.shieldStrength = shieldStrength;
    }

    public int getShieldStrength(){
        return this.shieldStrength;
    }

    public void hitDetected(){
        shieldStrength -=25;
        Log.i("Incoming: ", "Bam!!");
        if (shieldStrength == 0){
            destroyShip();
        }

    }

    private void destroyShip(){
        numShips--;
        Log.i("Explosion: ", "" + this.shipName + " destroyed");
    }


    /*
    public AlienShip(){
        numShips++;

        this.setShieldStrength(100);

    }

    public static int getNumShips(){
        return numShips;

    }

    private void setShieldStrength(int shieldStrength){

        // "this" distinguishes between the
        // member variable shieldStrength
        // And the local variable/paramater of the same name
        this.shieldStrength = shieldStrength;

    }

    public int getShieldStrength(){
        return this.shieldStrength;
    }

    public void hitDetected(){

        shieldStrength -=25;
        Log.i("Incomiming: ","Bam!!");
        if (shieldStrength == 0){
            destroyShip();
        }

    }

    private void destroyShip(){
        numShips--;
        Log.i("Explosion: ", ""+this.shipName + " destroyed");
    }
*/
}
