package com.robinkanatzar.android.aca.santa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SantaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_santa);
    }

    // for buttons, onClick = processClicks
    public void processClicks(View display) {
        //Log.d("RCK", "View = " + display);
        // display returns id/imageButton and a long string before that

        Intent action = null;
        int id = display.getId(); // returns id of button clicked (parses the long display value above)
            //imageButton1, 2, 3

        switch (id) {
            case(R.id.imageButton):
                // this is the dialer button

                // Intent (action to take, data associated with action)
                action = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:877-446-6723"));
                break;
            case(R.id.imageButton2):
                // this is the browse button
                action = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.noradsanta.org"));
                break;
            case(R.id.imageButton3):
                // this is the map button
                action = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=101 Saint Nicholas Dr.," + " North Pole, AK"));
                break;
            default:
                break;
        }

        startActivity(action); // uses intent to start the next activity
    }
}
