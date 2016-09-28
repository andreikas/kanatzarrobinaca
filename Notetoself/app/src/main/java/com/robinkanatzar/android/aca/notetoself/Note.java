package com.robinkanatzar.android.aca.notetoself;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Note {

    private String mTitle;
    private String mDescription;
    private boolean mIdea;
    private boolean mTodo;
    private boolean mImportant;
    private Bitmap mImage; // todo new
    private String mImageString;

    private static final String JSON_TITLE = "title";
    private static final String JSON_DESCRIPTION = "description";
    private static final String JSON_IDEA = "idea" ;
    private static final String JSON_TODO = "todo";
    private static final String JSON_IMPORTANT = "important";
    private String JSON_IMAGE_STRING;

    // Constructor
    // Only used when new is called with a JSONObject
    public Note(JSONObject jo) throws JSONException {

        mTitle =  jo.getString(JSON_TITLE);
        mDescription = jo.getString(JSON_DESCRIPTION);
        mIdea = jo.getBoolean(JSON_IDEA);
        mTodo = jo.getBoolean(JSON_TODO);
        mImportant = jo.getBoolean(JSON_IMPORTANT);
        // TODO Tuesday: call method to change string to bitmap
        //mImage = getBitmapFromString(mImageString);
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

        // TODO Tuesday: call method to convert bitmap to string
        Log.d("RCK", "In Note class, inside convertToJSON()");

        mImageString = getStringFromBitmap(mImage);
        Log.d("RCK", "mImageString = " + mImageString);

        //jo.put(JSON_IMAGE_STRING,mImageString);

        return jo;
    }

    // TODO Tuesday: create method to convert string to bitmap
    public Bitmap getBitmapFromString(String jsonString) {
        Log.d("RCK", "In Note class, inside getBitmapFromString()");
        /*
        * This Function converts the String back to Bitmap
        * */
        //byte[] decodedString = Base64.decode(mImage, Base64.DEFAULT);
        //Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        //return decodedByte;

        return null;
    }

    // TODO Tuesday: create method to convert bitmap to string
    public String getStringFromBitmap(Bitmap bitmapPicture) {
        Log.d("RCK", "Inside getStringFromBitmap");

        if (bitmapPicture == null) {//bitmapPicture is null
            // set default value to ic_launcher
            bitmapPicture = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.pineapple);
            Log.d("RCK", "The image is null!");
        }


        String testString = "this is a test string";
        return testString;
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
        //Log.d("RCK", "inside public boolean isIdea, mIdea returned = " + mIdea);
        return mIdea;
    }

    public void setIdea(boolean idea) {
        //Log.d("RCK", "inside public void setIdea, idea set = " + idea);
        mIdea = idea;
    }

    public boolean isTodo() {
        //Log.d("RCK", "inside public boolean isTodo, mTodo returned = " + mTodo);
        return mTodo;
    }

    public void setTodo(boolean todo) {
        //Log.d("RCK", "inside public void setTodo, todo set = " + todo );
        mTodo = todo;
    }

    public boolean isImportant() {
        return mImportant;
    }

    public void setImportant(boolean important) {
        mImportant = important;
    }

    public void setImage(Bitmap photo) {
        mImage = photo;
        Log.d("RCK", "Inside setImage");
    }

    public Bitmap getImage() {
        Log.d("RCK", "Inside getImage");
        return mImage;
    }
}
