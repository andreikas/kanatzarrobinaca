package com.robinkanatzar.android.rck.databsepracticefortabou;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mFrenchWord;
    TextView mTaboo1;
    TextView mTaboo2;
    TextView mTaboo3;
    TextView mTaboo4;
    TextView mTaboo5;
    Button mButtonGetNewWord;

    DataManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dm = new DataManager(this);

        mFrenchWord = (TextView) findViewById(R.id.frenchWord);
        mTaboo1 = (TextView) findViewById(R.id.taboo1);
        mTaboo2 = (TextView) findViewById(R.id.taboo2);
        mTaboo3 = (TextView) findViewById(R.id.taboo3);
        mTaboo4 = (TextView) findViewById(R.id.taboo4);
        mTaboo5 = (TextView) findViewById(R.id.taboo5);
        mButtonGetNewWord = (Button) findViewById(R.id.btnGetNewWord);
        mButtonGetNewWord.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        showNewWord(dm.getNewWord());
    }

    public void showNewWord(Cursor c) {

        String c1 = "";
        String c2 = "";
        String c3 = "";
        String c4 = "";
        String c5 = "";
        String c6 = "";

        c1 = c.getString(1);
        c2 = c.getString(2);
        c3 = c.getString(3);
        c4 = c.getString(4);
        c5 = c.getString(5);
        c6 = c.getString(6);

        Log.d("RCK", "c1-6: " + c1 + c2 + c3 + c4 + c5 + c6);

        mFrenchWord.setText(c1);
        mTaboo1.setText(c2);
        mTaboo2.setText(c3);
        mTaboo3.setText(c4);
        mTaboo4.setText(c5);
        mTaboo5.setText(c6);
    }
}


