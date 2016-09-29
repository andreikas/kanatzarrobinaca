package com.robinkanatzar.android.aca.notetoself;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
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
    //DialogEditNote editDialog;

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

        if (mNote.getImageString() != null) {
            Bitmap myBitmapAgain = decodeBase64(mNote.getImageString());
            mImage.setImageBitmap(myBitmapAgain);
        }

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
                //Toast.makeText(getActivity(), "Clicked on edit note button", Toast.LENGTH_SHORT).show();
                // TODO *** call new activity

                // Create a new dialog window
                DialogEditNote dialogEdit = new DialogEditNote();

                dialogEdit.sendValuesToEditNote(mNote.getTitle(), mNote.getDescription(), mNote.getImageString(), mNote.isImportant(), mNote.isTodo(), mNote.isIdea());


                // if title or description is null...
                String sendTitle = mNote.getTitle();
                if (sendTitle == null) {
                    sendTitle = "no title";
                }
                String sendDescription = mNote.getDescription();
                if (sendDescription == null) {
                    sendDescription = "no description";
                }

                // Show the dialog window with the note in it
                dialogEdit.show(getFragmentManager(), "");

                // Quit the dialog
                dismiss();
            }
        });

        // set the on click listener for the share button
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: call something to share the note via e-mail or text
                Toast.makeText(getActivity(), "Clicked on share button", Toast.LENGTH_SHORT).show();
                sendEmail();

                // quit the dialog
                dismiss();
            }
        });

        return builder.create();

    }

    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public void sendEmail() {
        Log.d("RCK", "Inside sendEmail method");

        String subject = mNote.getTitle();
        String message = "Title: " + mNote.getTitle() + "\nDescription: " + mNote.getDescription();

        Intent email = new Intent(Intent.ACTION_SEND);
        //email.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        // need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client"));
    }


    // Receive a note from the MainActivity
    // TODO 15
    public void sendNoteSelected(Note noteSelected) {
        mNote = noteSelected;
    }

}

