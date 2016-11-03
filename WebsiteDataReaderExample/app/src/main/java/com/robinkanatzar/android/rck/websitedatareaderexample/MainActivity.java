package com.robinkanatzar.android.rck.websitedatareaderexample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.robinkanatzar.android.rck.websitedatareaderexample.R.id.textView;

public class MainActivity extends AppCompatActivity {

    Button mButton;
    TextView mTextView;
    // TODO 1 START: change refeences of mGapURL to mBaseURL + mMaxCost + mMinCost
    String mFullURL = "http://www.gapfactory.com/browse/category.do?cid=1042792#pageId=0&price=4-11";
    String mBaseURL = "http://www.gapfactory.com/browse/category.do?cid=1042792#pageId=0&price=";

    String mMaxCost = ""; // will set max cost later with user input
    String mMinCost = "4"; // initialize as $4

    String mPieceURL = mBaseURL + mMinCost + "-" + mMaxCost;


    MenuPages mMenuPages = new MenuPages();


    // TODO 1 END


    String mGapURL = "http://robink.jimdo.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(textView);

    }

    public void myClickHandler(View view) {

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            Toast.makeText(this, "Yes network connection", Toast.LENGTH_SHORT).show();
            new DownloadWebpageTask().execute(mGapURL);

        } else {
            // display error
            Toast.makeText(this, "No service", Toast.LENGTH_SHORT).show();
        }

        try {
            mMenuPages.test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class DownloadWebpageTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {

                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            mTextView.setText(result);
        }
    }

    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 50000;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("RCK", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }


}


