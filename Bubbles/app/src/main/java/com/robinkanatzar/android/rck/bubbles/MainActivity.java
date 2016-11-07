package com.robinkanatzar.android.rck.bubbles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mButtonStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonStartGame = (Button) findViewById(R.id.buttonStartGame);

        mButtonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // when button is pressed, it starts the GameActivity
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);

            }
        });
    }
}
