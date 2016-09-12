package com.robinkanatzar.android.aca.accessscopethisandstatic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Fighter aFighter = new Fighter();
        Bomber aBomber = new Bomber();

        aBomber.shipName = "Newell Bomber";
        aFighter.shipName = "Meier Fighter";

        Log.i("aFighter Shield:", ""+ aFighter.getShieldStrength());
        Log.i("aBomber Shield:", ""+ aBomber.getShieldStrength());

        aBomber.fireWeapon();
        aFighter.fireWeapon();

        aBomber.hitDetected();
        aBomber.hitDetected();
        aBomber.hitDetected();
        aBomber.hitDetected();

        /*
        AlienShip girlShip = new AlienShip();
        AlienShip boyShip = new AlienShip();

        girlShip.shipName = "Corrine Yu";
        boyShip.shipName = "Andre LaMothe";

        Log.i("girlShip strngth: ", "" + girlShip.getShieldStrength());

        Log.i("boyShip shieldStrngth: ", "" + boyShip.getShieldStrength());

        boyShip.hitDetected();
        boyShip.hitDetected();
        boyShip.hitDetected();

        Log.i("girlShip Strngth: ", "" + girlShip.getShieldStrength());

        Log.i("boyShip shieldStrngth: ", "" + boyShip.getShieldStrength());

        boyShip.hitDetected(); //ahhh

        Log.i("girlShip Strngth: ", "" + girlShip.getShieldStrength());

        Log.i("boyShip shieldStrngth: ", "" + boyShip.getShieldStrength());
        Log.i("numShips: ", "" +    AlienShip.getNumShips());
        */


    }
}
