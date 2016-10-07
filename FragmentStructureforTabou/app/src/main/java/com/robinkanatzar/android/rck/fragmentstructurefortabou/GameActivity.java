package com.robinkanatzar.android.rck.fragmentstructurefortabou;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = new CardFragment();
        fragmentTransaction.replace(R.id.gameFrame, fragment);
        fragmentTransaction.commit();

    }

    public void clickNextCardRight(View v) {
        Toast.makeText(GameActivity.this, "You got it right!", Toast.LENGTH_SHORT).show();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment switchFragment = new SwitchFragment();
        fragmentTransaction.replace(R.id.gameFrame, switchFragment);
        fragmentTransaction.commit();

    }

    public void clickNextCardWrong(View v) {
        Toast.makeText(GameActivity.this, "You got it wrong...", Toast.LENGTH_SHORT).show();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment switchFragment = new SwitchFragment();
        fragmentTransaction.replace(R.id.gameFrame, switchFragment);
        fragmentTransaction.commit();
    }

    public void clickSwitchComplete(View v) {
        Toast.makeText(GameActivity.this, "Switch is complete!", Toast.LENGTH_SHORT).show();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment cardFragment = new CardFragment();
        fragmentTransaction.replace(R.id.gameFrame, cardFragment);
        fragmentTransaction.commit();

    }

    public void endGame() {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment endGameFragment = new EndGameFragment();
        fragmentTransaction.replace(R.id.gameFrame, endGameFragment);
        fragmentTransaction.commit();

    }

    public void translateWord(View v) {
        // Create a new dialog window
        TranslationFragment dialogTranslate = new TranslationFragment();

        // Show the dialog window with the note in it
        dialogTranslate.show(getFragmentManager(), "");

    }


}
