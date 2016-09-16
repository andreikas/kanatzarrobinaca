package com.robinkanatzar.android.aca.notetoself;

import android.util.Log;

public class Note {

    private String mTitle;
    private String mDescription;
    private boolean mIdea;
    private boolean mTodo;
    private boolean mImportant;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean isIdea() {
        Log.d("RCK", "inside public boolean isIdea, mIdea returned = " + mIdea);
        return mIdea;
    }

    public void setIdea(boolean idea) {
        Log.d("RCK", "inside public void setIdea, idea set = " + idea);
        mIdea = idea;
    }

    public boolean isTodo() {
        Log.d("RCK", "inside public boolean isTodo, mTodo returned = " + mTodo);
        return mTodo;
    }

    public void setTodo(boolean todo) {
        Log.d("RCK", "inside public void setTodo, todo set = " + todo );
        mTodo = todo;
    }

    public boolean isImportant() {
        return mImportant;
    }

    public void setImportant(boolean important) {
        mImportant = important;
    }
}
