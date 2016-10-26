package com.robinkanatzar.android.rck.threadsandasynctaskexample;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // declarations
    private Button button;
    private EditText time;
    private TextView finalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // link to widgets on screen
        time = (EditText) findViewById(R.id.in_time);
        button = (Button) findViewById(R.id.btn_run);
        finalResult = (TextView) findViewById(R.id.tv_result);

        // set on click listener for button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // create a new instance of the AsyncTaskRunner class below
                AsyncTaskRunner runner = new AsyncTaskRunner();

                // get the text that the user input in the editText box
                String sleepTime = time.getText().toString();

                // execute the instance of the AsyncTaxkRunner and pass in the sleep time from the user
                runner.execute(sleepTime);
            }
        });
    }

    // class that extends AsyncTask
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        // declarations
        private String resp;
        ProgressDialog progressDialog; // create an instance of the ProgressDialog class

        // doInBackground - task that is done on worker thread
        // returns a string (response)
        // first calls onPreExecute() method below
        @Override
        protected String doInBackground(String... params) {

            publishProgress("Sleeping..."); // Calls onProgressUpdate()

            // dangerous commands in the try-catch
            try {
                // declare a new int
                int time = Integer.parseInt(params[0])*1000;

                // Thread class, sleep method for a set amount of time
                Thread.sleep(time);

                // sets the response for how long the app slept
                resp = "Slept for " + params[0] + " seconds";

            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }

            // return the response
            return resp;

            // automatically navigate to onPostExecute(resp)
        }


        @Override
        protected void onPostExecute(String result) {

            // execution of result of Long time consuming operation

            // close the progress dialog
            progressDialog.dismiss();

            // set the text on the screen to the result
            finalResult.setText(result);
        }


        // automatically before doInBackground()
        @Override
        protected void onPreExecute() {

            // creates a pop up dialog while the task is processing
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "ProgressDialog",
                    "Wait for "+time.getText().toString()+ " seconds");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            // sets the text of the final result to the udpated status / progress text
            finalResult.setText(text[0]);
        }
    }

    //--------- Multiple thread example


}
