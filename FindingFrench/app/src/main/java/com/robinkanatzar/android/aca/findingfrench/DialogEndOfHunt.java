package com.robinkanatzar.android.aca.findingfrench;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class DialogEndOfHunt extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MY_DIALOG);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d!=null){
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.end_of_hunt, container, false);

        Button mButtonGoHome = (Button) root.findViewById(R.id.buttonGoHome);

        mButtonGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("RCK", "inside on click listener of DialogEndOfHunt");
                dismiss();

            }
        });

        return root;
    }

}
