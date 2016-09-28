package com.robinkanatzar.android.aca.notetoself;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

public class DialogEditNote extends DialogFragment {

    EditText mEditTitle;
    EditText mEditDescription;
    CheckBox mCheckBoxIdea;
    CheckBox mCheckBoxTodo;
    CheckBox mCheckBoxImportant;
    Button btnCancel;
    Button btnOK;
    Button btnChangePic;
    ImageView imageViewPreview;

    String mTitle;
    String mDescription;
    String mImageString;
    Boolean mIsImportant;
    Boolean mIsTodo;
    Boolean mIsIdea;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_note, null);

        // TODO: change all to match layout and names on dialog_edit_note
        mEditTitle = (EditText) dialogView.findViewById(R.id.editTitle2);
        mEditDescription = (EditText) dialogView.findViewById(R.id.editDescription2);
        mCheckBoxIdea = (CheckBox) dialogView.findViewById(R.id.checkBoxIdea2);
        mCheckBoxTodo = (CheckBox) dialogView.findViewById(R.id.checkBoxTodo2);
        mCheckBoxImportant = (CheckBox) dialogView.findViewById(R.id.checkBoxImportant2);
        btnCancel = (Button) dialogView.findViewById(R.id.btnCancel2);
        btnOK = (Button) dialogView.findViewById(R.id.btnOK2);
        btnChangePic = (Button) dialogView.findViewById(R.id.buttonAddPic2);
        imageViewPreview = (ImageView) dialogView.findViewById(R.id.imageViewPicConfirm2);

        // set fields to what was passed in by ShowNote function
        mEditTitle.setText(mTitle);
        mEditDescription.setText(mDescription);
        mCheckBoxTodo.isChecked();
        if (mIsIdea == true) {
            mCheckBoxIdea.isChecked();
        }
        if (mIsTodo) {
            mCheckBoxTodo.isChecked();
        }
        if (mIsImportant) {
            mCheckBoxImportant.isChecked();
        }


        builder.setView(dialogView);

        // Handle the cancel button
        btnCancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // Handle the OK button
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                //TODO change this so it's editing an existing note
                // Create a new note
                Note newNote = new Note();

                // Set its variables to match the users entries on the form
                newNote.setTitle(editTitle.getText().toString());
                newNote.setDescription(editDescription.getText().toString());
                newNote.setIdea(checkBoxIdea.isChecked());
                newNote.setTodo(checkBoxTodo.isChecked());
                newNote.setImportant(checkBoxImportant.isChecked());
                //newNote.setImage(photo);

                // Get a reference to MainActivity
                MainActivity callingActivity = (MainActivity) getActivity();

                // Pass newNote back to MainActivity
                callingActivity.createNewNote(newNote);*/

                // Quit the dialog
                dismiss();
            }
        });


        // TODO handle button to change picture

        return builder.create();

    }

    public void sendValuesToEditNote(String title, String description, String imageString, Boolean isImportant, Boolean isTodo, Boolean isIdea) {
        // sets the class variables
        // with data sent from the ShowNote class
        mTitle = title;
        mDescription = description;
        mImageString = imageString;

        mIsImportant = isImportant;
        mIsTodo = isTodo;
        mIsIdea = isIdea;

        Log.d("RCK", "inside sendValuesToEditNote: " + mTitle + mIsTodo + isTodo);
    }
}
