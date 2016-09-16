package com.robinkanatzar.android.aca.notetoself;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // new member variable mNoteAdapter
    private NoteAdapter mNoteAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // define mNoteAdapter as a new instance of NoteAdapter
        mNoteAdapter = new NoteAdapter();

        // create a new ListView and link it to the listView on the screen
        ListView listNote = (ListView) findViewById(R.id.listView);

        // set the adapter for the listView
        listNote.setAdapter(mNoteAdapter);

        // Set the on click listener for the listView
        // Handle clicks on the ListView
        listNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int whichItem, long id) {
                // TODO: come back her to figure this method out
                /*
                  Create  a temporary Note
                  Which is a reference to the Note
                  that has just been clicked
                */
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

        return super.onOptionsItemSelected(item);
    }

    public class NoteAdapter extends BaseAdapter {

        List<Note> noteList = new ArrayList<Note>();

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
                // TODO: null pointer exception, function on a null object
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
