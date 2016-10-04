package com.robinkanatzar.android.aca.findingfrench.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.robinkanatzar.android.aca.findingfrench.DialogEndOfHunt;
import com.robinkanatzar.android.aca.findingfrench.R;
import com.robinkanatzar.android.aca.findingfrench.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // call the function to set the button click listener
        setButtonClickListenerNew();
        setButtonClickListenerExisting();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // when the user clicks "add a new account"
    private void setButtonClickListenerNew() {
        // set the button click listener

        Button buttonNew = (Button) findViewById(R.id.buttonNew);
        buttonNew.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked create a new account", Toast.LENGTH_SHORT).show();

                // Create a new dialog window
                DialogEndOfHunt dialog = new DialogEndOfHunt();

                // Show the dialog window with the note in it
                dialog.show(getFragmentManager(), "");
            }
        });
    }

    private void setButtonClickListenerExisting() {

        Button buttonExisting = (Button) findViewById(R.id.buttonExisting);

        buttonExisting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Clicked existing account", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
