package com.robinkanatzar.android.rck.websitedatareaderexample;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

import bobik.BobikClient;
import bobik.BobikException;
import bobik.Job;
import bobik.JobListener;

import static android.R.attr.data;

public class OtherExample {

    static String YOUR_AUTH_KEY = "AUDAifLPPbx12FxpDsUE";

    BobikClient bobik = new BobikClient("YOUR_AUTH_KEY");

    JSONObject request = new JSONObject();

    String[] newURL = new String[]{"amazon.com", "google.com"};


    for (String singleURL : newURL) {
        request.put("url", newURL);
        Log.d("RCK", newURL.toString());
    }

    request.put("urls", newURL);

    for (String url : new String[]{"amazon.com", "google.com"})
            request.accumulate("urls", url);

    for (String query : new String[]{"//a/@href", "return $('.logo').length"})
            request.accumulate("queries", query);


    Job job = bobik.scrape(request, new JobListener() {
        public void onSuccess(JSONObject scraped_data) {
            Log.d("RCK", "Received data: " + scraped_data);
        }

        public void onProgress(float currentProgress) {
            System.out.println("Current progress is " + currentProgress*100 + "%");
        }

        public void onErrors(Collection<String> errors){
            for (String s : errors)
                System.err.println("Error for job " + job.id() + ": " + s);
        }
    });

    public OtherExample() throws InterruptedException, ExecutionException, BobikException, JSONException, IOException {
    }
}
