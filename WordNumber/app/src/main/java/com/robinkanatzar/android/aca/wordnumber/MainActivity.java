package com.robinkanatzar.android.aca.wordnumber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
/*
1. user input words "one" through "ten" (EditView)
2. user presses button (Button)
3. output on screen the integer 1-10 (TextView)

Use a switch statement for the conversion
switch(expression){
    case value :
       //Statements
       break; //optional
    case value :
       //Statements
       break; //optional
    //You can have any number of case statements.
    default : //Optional
       //Statements
}
*/

    //declare variables
    private Button mButton; //convertButton
    private EditText mEditText; //numberEditView
    private TextView mTextView; //numberTextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set values of variables
        mButton = (Button) findViewById(R.id.convertButton);
        mEditText = (EditText) findViewById(R.id.numberEditView);
        mTextView = (TextView) findViewById(R.id.numberTextView);

        //set on click listener for the convert button
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = mEditText.getText().toString();
                mTextView.setText(number);
            }
        });


    }
}
