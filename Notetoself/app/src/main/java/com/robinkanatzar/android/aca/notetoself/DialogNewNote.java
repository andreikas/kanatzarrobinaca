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
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class DialogNewNote extends DialogFragment{

    private static final int CAMERA_REQUEST = 123; // TODO new
    private ImageView imageViewConfirm; // TODO new
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 0;
    Bitmap photo;
    String mImageString;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // TODO 19

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_new_note, null); // use layout dialog_new_note

        // declare and link fields to the layout items
        final EditText editTitle = (EditText) dialogView.findViewById(R.id.editTitle);
        final EditText editDescription = (EditText) dialogView.findViewById(R.id.editDescription);
        final CheckBox checkBoxIdea = (CheckBox) dialogView.findViewById(R.id.checkBoxIdea);
        final CheckBox checkBoxTodo = (CheckBox) dialogView.findViewById(R.id.checkBoxTodo);
        final CheckBox checkBoxImportant = (CheckBox) dialogView.findViewById(R.id.checkBoxImportant);
        Button btnCancel = (Button) dialogView.findViewById(R.id.btnCancel);
        Button btnOK = (Button) dialogView.findViewById(R.id.btnOK);
        Button btnAddPic = (Button) dialogView.findViewById(R.id.buttonAddPic); // TODO new
        imageViewConfirm = (ImageView) dialogView.findViewById(R.id.imageViewPicConfirm); // TODO new


        // set the confirm image to invisible by default, so does not appear if no picture added
        imageViewConfirm.setVisibility(View.INVISIBLE);

        // set the dialog view so it shows up
        builder.setView(dialogView);

        // set on click listener for the cancel button
        btnCancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // simply dismiss the dialog view if user clicks cancel
            }
        });

        // set on click listener for ok button
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO - check if fields are empty and do not add. Prevents adding blank notes

                // Create a new instance of the Note class
                Note newNote = new Note();

                // Set its variables to match the users entries on the form
                newNote.setTitle(editTitle.getText().toString());
                newNote.setDescription(editDescription.getText().toString());
                newNote.setIdea(checkBoxIdea.isChecked());
                newNote.setTodo(checkBoxTodo.isChecked());
                newNote.setImportant(checkBoxImportant.isChecked());

                // TODO: send image as string
                newNote.setImageString(mImageString);


                // Get a reference to MainActivity
                MainActivity callingActivity = (MainActivity) getActivity();

                // Pass newNote back to MainActivity
                callingActivity.createNewNote(newNote); // TODO 20

                // Quit the dialog
                dismiss();
            }
        });

        // TODO 23
        // set the on click listener for the add picture button
        btnAddPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                            //Toast.makeText(getActivity(), "Permission is not already granted to access camera", Toast.LENGTH_SHORT).show();
                            ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CAMERA},
                            MY_PERMISSIONS_REQUEST_CAMERA);
                        } else {
                    //Toast.makeText(getActivity(), "Permission already granted", Toast.LENGTH_SHORT).show();
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);  // calls method OnActivityResult
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
                    //Toast.makeText(getActivity(), "Permission was granted!", Toast.LENGTH_SHORT).show();
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
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        photo = (Bitmap) data.getExtras().get("data"); // grabs the image from the intent
        imageViewConfirm.setImageBitmap(photo); // set image view to the image
        imageViewConfirm.setVisibility(View.VISIBLE); // set image view to visible

        String myBase64Image = encodeToBase64(photo, Bitmap.CompressFormat.JPEG, 100);
        mImageString = myBase64Image;
        //Log.d("RCK", "bitmap as string: " + myBase64Image);

    }

    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality)
    {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }
}
