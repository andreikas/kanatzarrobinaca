package com.robinkanatzar.android.aca.notetoself;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Note {

    private String mTitle;
    private String mDescription;
    private boolean mIdea;
    private boolean mTodo;
    private boolean mImportant;

    private static final String JSON_TITLE = "title";
    private static final String JSON_DESCRIPTION = "description";
    private static final String JSON_IDEA = "idea" ;
    private static final String JSON_TODO = "todo";
    private static final String JSON_IMPORTANT = "important";


    // Constructor
    // Only used when new is called with a JSONObject
    public Note(JSONObject jo) throws JSONException {

        mTitle =  jo.getString(JSON_TITLE);
        mDescription = jo.getString(JSON_DESCRIPTION);
        mIdea = jo.getBoolean(JSON_IDEA);
        mTodo = jo.getBoolean(JSON_TODO);
        mImportant = jo.getBoolean(JSON_IMPORTANT);
    }
    // Now we must provide an empty default constructor
    // for when we create a Note as we provide a
    // specialized constructor that must be used.
    public Note (){

    }

    public JSONObject convertToJSON() throws JSONException{

        JSONObject jo = new JSONObject();

        jo.put(JSON_TITLE, mTitle);
        jo.put(JSON_DESCRIPTION, mDescription);
        jo.put(JSON_IDEA, mIdea);
        jo.put(JSON_TODO, mTodo);
        jo.put(JSON_IMPORTANT, mImportant);

        return jo;
    }



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
