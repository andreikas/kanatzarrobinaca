package com.robinkanatzar.android.rck.trelloexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    // resourece http://themakeinfo.com/2015/04/retrofit-android-tutorial/

    Button click;
    TextView tv;
    EditText edit_user;
    ProgressBar pbar;
    String API = "https://api.github.com"; //BASE URL

    String TrelloAPI = "https://api.trello.com/1/";
    Button clickTrello;
    TextView tvTrello;
    EditText editTrello;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.tv);
        edit_user = (EditText) findViewById(R.id.edit);
        pbar = (ProgressBar) findViewById(R.id.pb);
        pbar.setVisibility(View.INVISIBLE);

        clickTrello = (Button) findViewById(R.id.buttonTrello);
        tvTrello = (TextView) findViewById(R.id.tvTrello);
        editTrello = (EditText) findViewById(R.id.editTrello);

        clickTrello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trelloUser = editTrello.getText().toString();

                RestAdapter restAdapterTrello = new RestAdapter.Builder()
                        .setEndpoint(TrelloAPI).build();

                gettrello trello = restAdapterTrello.create(gettrello.class);

                trello.getTrelloFeed(trelloUser,new Callback<trellomodel>() {
                    @Override
                    public void success(trellomodel trellomodel, Response response) {
                        //we get json object from github server to our POJO or model class

                        tvTrello.setText(
                                "Bio :"+ trellomodel.getBio() +
                                "\nFull name :" + trellomodel.getFullName());

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        tvTrello.setText(error.getMessage());

                    }
                });
            }
        });

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edit_user.getText().toString();
                pbar.setVisibility(View.VISIBLE);

                //Retrofit section start from here...
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint(API).build();                                        //create an adapter for retrofit with base url

                gitapi git = restAdapter.create(gitapi.class);                            //creating a service for adapter with our GET class

                //Now ,we need to call for response
                //Retrofit using gson for JSON-POJO conversion

                git.getFeed(user,new Callback<gitmodel>() {
                    @Override
                    public void success(gitmodel gitmodel, Response response) {
                        //we get json object from github server to our POJO or model class

                        tv.setText("Github Name :"+gitmodel.getName()+"\nWebsite :"+gitmodel.getBlog()+"\nCompany Name :"+gitmodel.getCompany()+"\nEmail: "+gitmodel.getEmail());

                        pbar.setVisibility(View.INVISIBLE);                               //disable progressbar
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        tv.setText(error.getMessage());
                        pbar.setVisibility(View.INVISIBLE);                               //disable progressbar
                    }
                });
            }
        });

    }


}
