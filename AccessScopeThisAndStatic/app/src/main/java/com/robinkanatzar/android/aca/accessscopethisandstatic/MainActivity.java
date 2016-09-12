package com.robinkanatzar.android.aca.accessscopethisandstatic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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



    }
}
