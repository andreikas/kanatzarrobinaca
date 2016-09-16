package com.robinkanatzar.android.aca.magicball;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // establish buttons and text views
    Button mButtonRandomMessage;
    Button mButtonRollDice;
    TextView mTextViewRandomMessage;
    TextView mTextViewRollDice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // link buttons and text views to actual widgets on screen
        mButtonRandomMessage = (Button) findViewById(R.id.buttonRandomMessage);
        mButtonRollDice = (Button) findViewById(R.id.buttonRollDice);
        mTextViewRandomMessage = (TextView) findViewById(R.id.textViewRandomMessage);
        mTextViewRollDice = (TextView) findViewById(R.id.textViewRollDice);


    }

    public void buttonWasClicked(View view) {
        // when either of the two buttons is clicked...


        // if the random message button was clicked...
            // display a message in the random message text view
            mTextViewRandomMessage.setText("Test message for random message text view");

        // if the roll dice button was clicked...
            // display a number in the roll dice text view
            mTextViewRollDice.setText("Test message for roll dice text view");
    }
}
