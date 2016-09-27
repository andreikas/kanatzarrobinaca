package com.robinkanatzar.android.aca.notetoself;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class DialogNewNote extends DialogFragment{

    private static final int CAMERA_REQUEST = 123; // TODO new
    private ImageView imageViewConfirm; // TODO new
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 0;
    Bitmap photo;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_new_note, null);

        final EditText editTitle = (EditText) dialogView.findViewById(R.id.editTitle);
        final EditText editDescription = (EditText) dialogView.findViewById(R.id.editDescription);
        final CheckBox checkBoxIdea = (CheckBox) dialogView.findViewById(R.id.checkBoxIdea);
        final CheckBox checkBoxTodo = (CheckBox) dialogView.findViewById(R.id.checkBoxTodo);
        final CheckBox checkBoxImportant = (CheckBox) dialogView.findViewById(R.id.checkBoxImportant);
        Button btnCancel = (Button) dialogView.findViewById(R.id.btnCancel);
        Button btnOK = (Button) dialogView.findViewById(R.id.btnOK);
        Button btnAddPic = (Button) dialogView.findViewById(R.id.buttonAddPic); // TODO new
        imageViewConfirm = (ImageView) dialogView.findViewById(R.id.imageViewPicConfirm); // TODO new

        imageViewConfirm.setVisibility(View.INVISIBLE); // TODO new

        //builder.setView(dialogView).setMessage("Add a new note");
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

                // Create a new note
                Note newNote = new Note();

                // Set its variables to match the users entries on the form
                newNote.setTitle(editTitle.getText().toString());
                newNote.setDescription(editDescription.getText().toString());
                newNote.setIdea(checkBoxIdea.isChecked());
                newNote.setTodo(checkBoxTodo.isChecked());
                newNote.setImportant(checkBoxImportant.isChecked());
                newNote.setImage(photo); // todo new

                // Get a reference to MainActivity
                MainActivity callingActivity = (MainActivity) getActivity();

                // Pass newNote back to MainActivity
                callingActivity.createNewNote(newNote);

                // Quit the dialog
                dismiss();
            }
        });


        btnAddPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(getActivity(), "Permission is not already granted to access camera", Toast.LENGTH_SHORT).show();

                            ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CAMERA},
                            MY_PERMISSIONS_REQUEST_CAMERA);
                        } else {
                    Toast.makeText(getActivity(), "Permission already granted", Toast.LENGTH_SHORT).show();
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }

            }
        });

        return builder.create();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Permission was granted!", Toast.LENGTH_SHORT).show();

                    Intent cameraIntent2 = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent2, CAMERA_REQUEST);

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Toast.makeText(getActivity(), "Permission was denied :(", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            //Toast.makeText(DialogNewNote.getActivity(), "Something weird happened", Toast.LENGTH_SHORT).show();
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO: doesn't recognize RESULT_OK?

        //if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {

            photo = (Bitmap) data.getExtras().get("data");
            //imageViewConfirm.setVisibility(View.VISIBLE); // show test pic
            imageViewConfirm.setImageBitmap(photo);
            imageViewConfirm.setVisibility(View.VISIBLE);
        //}
    }



}
