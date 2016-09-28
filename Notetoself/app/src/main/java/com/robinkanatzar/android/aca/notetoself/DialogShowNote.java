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
import android.widget.Toast;

public class DialogShowNote extends DialogFragment {

    // TODO 13 - default constructor

    private Note mNote;
    ImageView mImage;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // TODO 17
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_show_note, null); // use dialog_show_note layout

        // set fields on dialog_show_note to items
        TextView txtTitle = (TextView) dialogView.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) dialogView.findViewById(R.id.txtDescription);
        Button btnEdit = (Button) dialogView.findViewById(R.id.buttonEditNote);
        Button btnShare = (Button) dialogView.findViewById(R.id.buttonShare);
        mImage = (ImageView) dialogView.findViewById(R.id.imageViewShowImage);
        Button btnOK = (Button) dialogView.findViewById(R.id.btnOK);

        // set values of each field
        txtTitle.setText(mNote.getTitle());
        txtDescription.setText(mNote.getDescription());
        mImage.setImageBitmap(mNote.getImage());

        // set values of the icons for important, to do, and idea
        ImageView ivImportant = (ImageView) dialogView.findViewById(R.id.imageViewImportant);
        ImageView ivTodo = (ImageView) dialogView.findViewById(R.id.imageViewTodo);
        ImageView ivIdea = (ImageView) dialogView.findViewById(R.id.imageViewIdea);

        // hide icons based on the Note properties
        if (!mNote.isImportant()){
            ivImportant.setVisibility(View.INVISIBLE);
        }
        if (!mNote.isTodo()){
            ivTodo.setVisibility(View.INVISIBLE);
        }
        if (!mNote.isIdea()){
            ivIdea.setVisibility(View.INVISIBLE);
        }

        // set the dialog view
        builder.setView(dialogView);

        // set on click listener for the ok button
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // simply close out of the show note dialog
            }
        });

        // set the on click listener for the edit button
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: call new fragment when edit button clicked
                Toast.makeText(getActivity(), "Clicked on edit note button", Toast.LENGTH_SHORT).show();
            }
        });

        // set the on click listener for the share button
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: call something to share the note via e-mail or text
                Toast.makeText(getActivity(), "Clicked on share button", Toast.LENGTH_SHORT).show();
                sendEmail();
            }
        });

        return builder.create();

    }

    public void sendEmail() {
        Log.d("RCK", "Inside sendEmail method");


    }


    // Receive a note from the MainActivity
    // TODO 15
    public void sendNoteSelected(Note noteSelected) {
        mNote = noteSelected;
    }

}

