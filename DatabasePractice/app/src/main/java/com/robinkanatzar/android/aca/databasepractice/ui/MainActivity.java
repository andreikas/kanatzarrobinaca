package com.robinkanatzar.android.aca.databasepractice.ui;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.robinkanatzar.android.aca.databasepractice.R;
import com.robinkanatzar.android.aca.databasepractice.db.PracticeDataSource;

public class MainActivity extends AppCompatActivity {
/*
    User inputs notes into database and can retrieve them later.
    NAME (of person)
    NOTES (about person)

    User can query the notes field and pull specific records

    => Base of the networking app idea
 */

    // create an instance of the PracticeDataSource class
    protected PracticeDataSource mDataSource;


    protected Button mInsertButton;
    protected Button mSelectButton;
    protected Button mUpdateButton;
    protected Button mDeleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataSource = new PracticeDataSource(MainActivity.this);

        Log.d("RCK", "Made it to onCreate");

        mInsertButton = (Button) findViewById(R.id.buttonInsert);

        mInsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // when the insert button is clicked...

                String name = "test name";
                String description = "test description";
                mDataSource.insertEntry(name, description);

                // TODO can't get second entry to show in cursor dump
                //mDataSource.insertEntry("Robin", "Sparkles");
                Toast.makeText(MainActivity.this, "You clicked the insert button", Toast.LENGTH_SHORT).show();

            }
        });

        mSelectButton = (Button) findViewById(R.id.buttonSelect);

        mSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // when the select button is clicked...
                Toast.makeText(MainActivity.this, "You clicked the select button", Toast.LENGTH_SHORT).show();

                Cursor cursor = mDataSource.selectAllEntries();
                Log.d("RCK", "dumpCursorToString in Main Activity: " + DatabaseUtils.dumpCursorToString(cursor));

                // TODO: print the select on the screen
            }
        });

        mUpdateButton = (Button) findViewById(R.id.buttonUpdate);

        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // when the updated button is clicked...
                Toast.makeText(MainActivity.this, "You clicked the Update button", Toast.LENGTH_SHORT).show();

                mDataSource.updateTemperature("Testing123name"); // TODO: update param passed
            }
        });

        mDeleteButton = (Button) findViewById(R.id.buttonDelete);

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // when the delete button is clicked...
                Toast.makeText(MainActivity.this, "You clicked the delete button", Toast.LENGTH_SHORT).show();

                mDataSource.deleteAll();
            }
        });


    }

    @Override
    protected void onResume() {
        Log.d("RCK", "Made it to onResume");
        super.onResume();

        mDataSource.open();

    }

    @Override
    protected void onPause() {
        super.onPause();

        mDataSource.close();
    }

}
