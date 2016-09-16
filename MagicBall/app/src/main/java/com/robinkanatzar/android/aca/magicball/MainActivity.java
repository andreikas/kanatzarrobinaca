package com.robinkanatzar.android.aca.magicball;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MagicBall mMagicBallMessage = new MagicBall();
    private Dice mDiceRoll = new Dice();

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

        if (view.getId() == R.id.buttonRandomMessage) {
            // if the random message button was clicked...
            // display a message in the random message text view
            String message = mMagicBallMessage.getMessage();
            mTextViewRandomMessage.setText(message);
        } else if (view.getId() == R.id.buttonRollDice) {
            // if the roll dice button was clicked...
            // display a number in the roll dice text view
            int roll = mDiceRoll.getDiceRoll();
            mTextViewRollDice.setText("You rolled a total of: " + roll);
        }
    }
}
