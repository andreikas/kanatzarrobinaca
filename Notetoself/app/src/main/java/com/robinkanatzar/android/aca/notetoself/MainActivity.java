package com.robinkanatzar.android.aca.notetoself;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Animation mAnimFlash;
    Animation mFadeIn;
    int mIdBeep = -1;
    SoundPool mSp;


    // new member variable mNoteAdapter
    private NoteAdapter mNoteAdapter;
    private boolean mSound;
    private int mAnimOption;
    private SharedPreferences mPrefs;

    protected void onPause() {
        super.onPause();

        mNoteAdapter.saveNotes();
    }

    @Override
    protected void onResume(){
        super.onResume();

        mPrefs = getSharedPreferences("Note to self", MODE_PRIVATE);
        mSound  = mPrefs.getBoolean("sound", true);
        mAnimOption = mPrefs.getInt("anim option", SettingsActivity.FAST);

        mAnimFlash = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.flash);
        mFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        // Set the rate of flash based on settings
        if(mAnimOption == SettingsActivity.FAST){

            mAnimFlash.setDuration(100);
            Log.i("anim = ",""+ mAnimOption);
        }else if(mAnimOption == SettingsActivity.SLOW){

            Log.i("anim = ",""+ mAnimOption);
            mAnimFlash.setDuration(1000);
        }

        mNoteAdapter.notifyDataSetChanged();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate our sound pool
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            mSp = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            mSp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }


        try{
            // Create objects of the 2 required classes
            AssetManager assetManager = this.getAssets();
            AssetFileDescriptor descriptor;

            // Load our fx in memory ready for use
            descriptor = assetManager.openFd("beep.ogg");
            mIdBeep = mSp.load(descriptor, 0);


        }catch(IOException e){
            // Print an error message to the console
            Log.e("error", "failed to load sound files");
        }


        // define mNoteAdapter as a new instance of NoteAdapter
        mNoteAdapter = new NoteAdapter();

        // create a new ListView and link it to the listView on the screen
        ListView listNote = (ListView) findViewById(R.id.listView);

        // set the adapter for the listView
        listNote.setAdapter(mNoteAdapter);

        // So we can long click it
        listNote.setLongClickable(true);

        // Now to detect long clicks and delete the note
        listNote.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> adapter, View view,
                                           int whichItem, long id) {

                // Ask NoteAdapter to delete this entry
                mNoteAdapter.deleteNote(whichItem);

                return true;
            }
        });


        // Set the on click listener for the listView
        // Handle clicks on the ListView
        listNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int whichItem, long id) {
                /*
                  Create  a temporary Note
                  Which is a reference to the Note
                  that has just been clicked
                */

                if(mSound) {
                    mSp.play(mIdBeep, 1, 1, 0, 0, 1);
                }

                Note tempNote = mNoteAdapter.getItem(whichItem);

                // Create a new dialog window
                DialogShowNote dialog = new DialogShowNote();
                // Send in a reference to the note to be shown
                dialog.sendNoteSelected(tempNote);

                // Show the dialog window with the note in it
                dialog.show(getFragmentManager(), "");

            }
        });

    }

    public void createNewNote(Note n){

        // add a note n to the note adapter
        mNoteAdapter.addNote(n);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // establish an id and set it to the clicked item id
        int id = item.getItemId();

        // if the id is the action_add button (plus)
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {

            // when the plus is clicked...
            // create a new instance of DialogNewNote
            DialogNewNote dialog = new DialogNewNote();
            dialog.show(getFragmentManager(), "");
            return true;

        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public class NoteAdapter extends BaseAdapter {

        private JSONSerializer mSerializer;
        List<Note> noteList = new ArrayList<Note>();

        public NoteAdapter(){

            mSerializer = new JSONSerializer("NoteToSelf.json",
                    MainActivity.this.getApplicationContext());

            try {
                noteList = mSerializer.load();
            } catch (Exception e) {
                noteList = new ArrayList<Note>();
                Log.e("Error loading notes: ", "", e);
            }

        }

        public void deleteNote(int n){

            noteList.remove(n);
            notifyDataSetChanged();

        }


        public void saveNotes(){
            try{
                mSerializer.save(noteList);

            }catch(Exception e){
                Log.e("Error Saving Notes","", e);
            }
        }

        @Override
        public int getCount() {
            // returns the size of the note list as an integer
            return noteList.size();
        }


        @Override
        public Note getItem(int whichItem) {
            // pass in the integer for the item
            // return the Note object
            return noteList.get(whichItem);
        }

        @Override
        public long getItemId(int whichItem) {
            // pass in the item int
            // return the item id
            return whichItem;
        }

        @Override
        public View getView(int whichItem, View view, ViewGroup viewGroup)   {

            // Implement this method next
            // Has view been inflated already
            if(view == null){

                // If not, do so here
                // First create a LayoutInflater
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                // Now instantiate view using inflater.inflate
                // using the listitem layout
                view = inflater.inflate(R.layout.listitem, viewGroup,false);
                // The false parameter is neccessary
                // because of the way that we want to use listitem

            }// End if

            // Grab a reference to all our TextView and ImageView widgets
            TextView txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            TextView txtDescription = (TextView) view.findViewById(R.id.txtDescription);
            ImageView ivImportant = (ImageView) view.findViewById(R.id.imageViewImportant);
            ImageView ivTodo = (ImageView) view.findViewById(R.id.imageViewTodo);
            ImageView ivIdea = (ImageView) view.findViewById(R.id.imageViewIdea);

            // Hide any ImageView widgets that are not relevant
            Note tempNote = noteList.get(whichItem);

            // To animate or not to animate
            if (tempNote.isImportant() && mAnimOption != SettingsActivity.NONE ) {

                view.setAnimation(mAnimFlash);

            }else{
                view.setAnimation(mFadeIn);
            }


            /* RCK Friday commented out
            if (!tempNote.isImportant()){
                //ivImportant.setVisibility(View.GONE);
                ivImportant.setVisibility(View.INVISIBLE);
                Log.d("RCK", "Inside !tempNote.isImportant(), hiding important icon");
            }

            if (!tempNote.isTodo()){
                //ivTodo.setVisibility(View.GONE);
                ivTodo.setVisibility(View.INVISIBLE);
                Log.d("RCK", "Inside !tempNote.isTodo(), hiding TD icon");
            }

            if (!tempNote.isIdea()){
                //ivIdea.setVisibility(View.GONE);

                ivIdea.setVisibility(View.INVISIBLE);
                Log.d("RCK", "Inside !tempNote.isIdea(), hiding idea icon");
                Log.d("RCK", "Inside !tempNote.isIdea(), !tempNote.isIdea() = " + !tempNote.isIdea());
            } */

            // RCK Friday

            // Set all icons to visible
            ivImportant.setVisibility(View.VISIBLE);
            ivTodo.setVisibility(View.VISIBLE);
            ivIdea.setVisibility(View.VISIBLE);

            if(!tempNote.isImportant() == true && !tempNote.isTodo() == true && !tempNote.isIdea() == true) {
                // important is not checked
                ivImportant.setVisibility(View.INVISIBLE);
                ivTodo.setVisibility(View.INVISIBLE);
                ivIdea.setVisibility(View.INVISIBLE);
            } else if (!tempNote.isImportant() == false && !tempNote.isTodo() == false && !tempNote.isIdea() == false) {
                // keep visible

            } else if (!tempNote.isImportant() == false && !tempNote.isTodo() == true && !tempNote.isIdea() == true) {
                // only important is checked
                ivTodo.setVisibility(View.INVISIBLE);
                ivIdea.setVisibility(View.INVISIBLE);
            } else if (!tempNote.isImportant() == true && !tempNote.isTodo() == false && !tempNote.isIdea() == true) {
                // only to do is checked
                ivImportant.setVisibility(View.INVISIBLE);
                ivIdea.setVisibility(View.INVISIBLE);
            } else if (!tempNote.isImportant() == true && !tempNote.isTodo() == true && !tempNote.isIdea() == false) {
                // only idea is checked
                ivImportant.setVisibility(View.INVISIBLE);
                ivTodo.setVisibility(View.INVISIBLE);
            } else if (!tempNote.isImportant() == true && !tempNote.isTodo() == false && !tempNote.isIdea() == false) {
                // to do and idea checked
                ivImportant.setVisibility(View.INVISIBLE);

            } else if (!tempNote.isImportant() == false && !tempNote.isTodo() == true && !tempNote.isIdea() == false) {
                // idea and important checked
                ivTodo.setVisibility(View.INVISIBLE);

            } else if (!tempNote.isImportant() == false && !tempNote.isTodo() == false && !tempNote.isIdea() == true) {
                // to do and important checked
                ivIdea.setVisibility(View.INVISIBLE);
            }

            // END RCK Friday

            // Add the text to the heading and description
            txtTitle.setText(tempNote.getTitle());
            txtDescription.setText(tempNote.getDescription());

            return view;
        }

        public void addNote(Note n){

            noteList.add(n);
            notifyDataSetChanged();

        }

    }



}
