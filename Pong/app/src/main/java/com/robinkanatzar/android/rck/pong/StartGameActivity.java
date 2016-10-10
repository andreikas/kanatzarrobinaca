package com.robinkanatzar.android.rck.pong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class StartGameActivity extends AppCompatActivity {

    TextView mLoserTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        mLoserTextView = (TextView) findViewById(R.id.loserTextView);
        mLoserTextView.setText("");
    }

    public void onNewGame(int i) {
        setContentView(R.layout.activity_start_game);
    }

    public void clickStartGame(View v) {
        // start a game when the user clicks the button

        // call main activity
        Intent intent = new Intent(StartGameActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void setLoserText() {
        mLoserTextView.setText("You lost. Play again?");
    }
}
