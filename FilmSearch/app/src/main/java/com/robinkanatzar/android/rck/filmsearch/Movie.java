package com.robinkanatzar.android.rck.filmsearch;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {

    @SerializedName("poster_path")
    private String mPoster;

    @SerializedName("overview")
    private String mDescription;

    @SerializedName("backdrop_path")
    private String mBackdrop;

    private String mTitle;
    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";
    
    public Movie() {}


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getPoster() {
        return TMDB_IMAGE_PATH + mPoster;

    }

    public void setPoster(String poster) {

        mPoster = poster;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getBackdrop() {
        return TMDB_IMAGE_PATH  + mBackdrop;

    }

    public void setBackdrop(String backdrop) {
        mBackdrop = backdrop;
    }

    public static class MovieResult {
        private List<Movie> results;

        public List<Movie> getResults() {
            return results;
        }
    }

}
