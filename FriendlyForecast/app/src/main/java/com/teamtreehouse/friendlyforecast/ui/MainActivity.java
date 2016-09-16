package com.teamtreehouse.friendlyforecast.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.teamtreehouse.friendlyforecast.R;
import com.teamtreehouse.friendlyforecast.db.ForecastDataSource;
import com.teamtreehouse.friendlyforecast.services.Forecast;
import com.teamtreehouse.friendlyforecast.services.ForecastService;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {

    // set the tag for the logging messages to "MainActivity"
    public static final String TAG = MainActivity.class.getSimpleName();

    // create an instance of the ForecastDataSource class
    protected ForecastDataSource mDataSource;

    // create variable names for the existing buttons
    protected Button mInsertButton;
    protected Button mSelectButton;
    protected Button mUpdateButton;
    protected Button mDeleteButton;

    // create a string of doubles for the temperatures
    protected double[] mTemperatures;

    // create variable names for the existing text views
    protected TextView mHighTextView;
    protected TextView mLowTextView;

    // create variables for the highest and lowest temp
    protected long mHighTemp;
    protected long mLowTemp;

    // onCreate - when the app is first opened, do...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActionBar().hide(); // TODO: Why?

        // create a new data source variable,
        // pass in a context (MainActivity.this)
        mDataSource = new ForecastDataSource(MainActivity.this);

        // set the value of the text view variables to the physical text view resource
        mHighTextView = (TextView)findViewById(R.id.textView2);
        mLowTextView = (TextView)findViewById(R.id.textView3);

        // set the value of the button variable to the existing button
        mInsertButton = (Button)findViewById(R.id.insertButton);

        // set an on-click listener for the insert button
        mInsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // when the insert button is clicked...
                // call the load forecast data method
                loadForecastData();
                Log.d("RCK", "The insert button has been clicked");
            }

        });
        // set the value of the select button to the existing button
        mSelectButton = (Button)findViewById(R.id.selectButton);

        // set an on click listener for the select button
        mSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // when the select button is clicked...
                // create a new intent and call it
                // pass in ViewForecastActivity class
                // TODO: figure out exactly what this does
                startActivity(new Intent(MainActivity.this, ViewForecastActivity.class));
            }
        });

        // set the value of the update button to the existing button
        mUpdateButton = (Button)findViewById(R.id.updateButton);

        // set an on click listener for the update button
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // when the update button is clicked...
                // call the updateTemperature function on mDataSource
                // mDataSource is an instance of ForecastDataSource, so the fn is in there
                // pass in 100 as the parameter
                mDataSource.updateTemperature(100);
            }
        });

        // set the value of the delete button to the existing delete button
        mDeleteButton = (Button)findViewById(R.id.deleteButton);

        // set an on click listener for the delete button
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // when the delete button is clicked...
                // call deleteAll on mDataSource
                // function located in the ForecastDataSource class
                mDataSource.deleteAll();
            }
        });

        // establish and associate the text view for branding with the existing text view
        TextView photoCredit = (TextView)findViewById(R.id.textView);
        // set an onclick listener for the text view
        photoCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // when the photo credit is clicked...
                // create a new intent to navigate to a website (flickr)
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.flickr.com/photos/london/71458818"));
                // start the intent (i.e. go to the website)
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // open the data source in onResume
        mDataSource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // close the data source in onPause
        mDataSource.close();
    }

    protected void loadForecastData() {
        // create a new instance of the ForecastService class
        ForecastService service = new ForecastService();

        // call the loadForecastData function on service
        // pass in the callback method mForecastCallback
        service.loadForecastData(mForecastCallback);
        Log.d("RCK", "Inside loadForecastData in MainActivity");
    }

    protected Callback<Forecast> mForecastCallback = new Callback<Forecast>() {
        @Override
        public void success(Forecast forecast, Response response) {
            mTemperatures = new double[forecast.hourly.data.size()];
            for (int i = 0; i < forecast.hourly.data.size(); i++) {
                mTemperatures[i] = forecast.hourly.data.get(i).temperature;
                Log.v(TAG, "Temp " + i + ": " + mTemperatures[i]);
            }

            mDataSource.insertForecast(forecast);
            updateHighAndLow();
            enableOtherButtons();
            Log.d("RCK", "Inside mForecastCallback in MainActivity");
        }

        @Override
        public void failure(RetrofitError error) {
            Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void updateHighAndLow() {
        // TODO: Query for high temp
        // TODO: Query for low temp
        mHighTextView.setText("Upcoming high: " + mHighTemp);
        mLowTextView.setText("Upcoming low: " + mLowTemp);
    }

    private void resetHighAndLow() {
        mHighTemp = 0;
        mLowTemp = 0;
        mHighTextView.setText("");
        mLowTextView.setText("");
    }

    private void enableOtherButtons() {
        mSelectButton.setEnabled(true);
        mUpdateButton.setEnabled(true);
        mDeleteButton.setEnabled(true);
    }
}
