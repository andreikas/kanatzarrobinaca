package com.robinkanatzar.android.rck.filmsearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // declare variables
    private RecyclerView mRecyclerView;
    MoviesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // link to widgets on screen
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // set up recycler view
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // default grid layout manager
        mAdapter = new MoviesAdapter(this); // create adapter
        mRecyclerView.setAdapter(mAdapter); // assign adapter

        // create list array and fill it with movies 0 to 25
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            movies.add(new Movie());
        }

        // set the movie list after the loop
        mAdapter.setMovieList(movies);

        // create a rest adapter with a base URL
        Retrofit restAdapter = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.themoviedb.org/")
                .build();

        MoviesApiService apiService = restAdapter.create(MoviesApiService.class);

        Call<Movie.MovieResult> call = apiService.getPopularMovies();
        call.enqueue(new Callback<Movie.MovieResult>() {
            @Override
            public void onResponse(Call<Movie.MovieResult> call, Response<Movie.MovieResult> response) {
                mAdapter.setMovieList(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Movie.MovieResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void onMovieClicked(Movie movie) {
        Toast.makeText(this, "Some item was clicked!", Toast.LENGTH_SHORT).show();

    }
}
