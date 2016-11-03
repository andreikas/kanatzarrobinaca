package com.robinkanatzar.android.rck.trelloexample;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface gettrello {

    @GET("/members/{user}")      //here is the other url part.best way is to start using /
    public void getTrelloFeed(@Path("user") String user, Callback<trellomodel> response);     //string user is for passing values from edittext for eg: user=basil2style,google
    //response is the response from the server which is now in the POJO
}
