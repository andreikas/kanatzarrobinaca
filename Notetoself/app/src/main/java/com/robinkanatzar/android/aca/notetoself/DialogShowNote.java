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

    private Note mNote;
    ImageView mImage;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // All the other code goes here
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_show_note, null);

        //Log.d("RCK", "Right after inflater null line");

        TextView txtTitle = (TextView) dialogView.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) dialogView.findViewById(R.id.txtDescription);
        Button btnEdit = (Button) dialogView.findViewById(R.id.buttonEditNote); // TODO new
        Button btnShare = (Button) dialogView.findViewById(R.id.buttonShare); // TODO new
        mImage = (ImageView) dialogView.findViewById(R.id.imageViewShowImage); // TODO new

        // --------
        // RCK Thurs

        Log.d("RCK", "mNote.getTitle() = " + mNote.getTitle());
        Log.d("RCK", "mNote.getDescription() = " + mNote.getDescription());

        //txtTitle.setText("This is a test title");
        //txtDescription.setText("This is a test description");
        txtTitle.setText(mNote.getTitle());
        txtDescription.setText(mNote.getDescription());
        mImage.setImageBitmap(mNote.getImage()); // todo new

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

        //builder.setView(dialogView).setMessage("Your Note"); // this is cut off on the pop up window

        builder.setView(dialogView);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: call new fragment when edit button clicked
                Toast.makeText(getActivity(), "Clicked on edit note button", Toast.LENGTH_SHORT).show();
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: call something to share the note via e-mail or text
                Toast.makeText(getActivity(), "Clicked on share button", Toast.LENGTH_SHORT).show();

            }
        });


        return builder.create();

    }


    // Receive a note from the MainActivity
    public void sendNoteSelected(Note noteSelected) {
        mNote = noteSelected;
    }

}

