package com.robinkanatzar.android.aca.notetoself;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DialogShowNote extends DialogFragment {

    private Note mNote;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // All the other code goes here
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_show_note, null);

        //Log.d("RCK", "Right after inflater null line");

        TextView txtTitle = (TextView) dialogView.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) dialogView.findViewById(R.id.txtDescription);

        // --------
        // RCK Thurs

        Log.d("RCK", "mNote.getTitle() = " + mNote.getTitle());
        Log.d("RCK", "mNote.getDescription() = " + mNote.getDescription());

        //txtTitle.setText("This is a test title");
        //txtDescription.setText("This is a test description");
        txtTitle.setText(mNote.getTitle());
        txtDescription.setText(mNote.getDescription());

        // --------

        ImageView ivImportant = (ImageView) dialogView.findViewById(R.id.imageViewImportant);
        ImageView ivTodo = (ImageView) dialogView.findViewById(R.id.imageViewTodo);
        ImageView ivIdea = (ImageView) dialogView.findViewById(R.id.imageViewIdea);

        if (!mNote.isImportant()){
            //ivImportant.setVisibility(View.GONE);
            ivImportant.setVisibility(View.INVISIBLE);
        }

        if (!mNote.isTodo()){
            //ivTodo.setVisibility(View.GONE);
            ivTodo.setVisibility(View.INVISIBLE);
        }

        if (!mNote.isIdea()){
            //ivIdea.setVisibility(View.GONE);
            ivIdea.setVisibility(View.INVISIBLE);
        }

        Button btnOK = (Button) dialogView.findViewById(R.id.btnOK);

        builder.setView(dialogView).setMessage("Your Note"); // this is cut off on the pop up window

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return builder.create();


    }


    // Receive a note from the MainActivity
    public void sendNoteSelected(Note noteSelected) {
        mNote = noteSelected;
    }

}

