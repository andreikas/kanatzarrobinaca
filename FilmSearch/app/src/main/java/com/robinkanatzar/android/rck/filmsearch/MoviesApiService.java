package com.robinkanatzar.android.rck.filmsearch;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesApiService {

    @GET("/3/movie/popular?api_key=a755518bdaba7680a8b160f485220377")
    Call<Movie.MovieResult> getPopularMovies();
}

