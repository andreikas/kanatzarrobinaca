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
                number = number.toLowerCase();
                //mTextView.setText(number);

                //determine which integer to print
                switch(number.charAt(0)) {
                    case 'o':
                        if(number.charAt(1)=='n') {
                            // the number is one
                            mTextView.setText(1 + "");
                        } else {
                            mTextView.setText("ERROR: Invalid Entry");
                        }
                        break;
                    case 't':
                        if(number.charAt(1)=='w') {
                            // the number is two
                            mTextView.setText(2 + "");
                        } else if(number.charAt(1)=='h') {
                            // the number is three
                            mTextView.setText(3 + "");
                        } else if(number.charAt(1)=='e') {
                            // the number is ten
                            mTextView.setText(10 + "");
                        } else {
                            mTextView.setText("ERROR: Invalid Entry");
                        }
                        break;
                    case 'f':
                        if(number.charAt(1)=='o') {
                            // the number is four
                            mTextView.setText(4 + "");
                        } else if(number.charAt(1)=='i') {
                            // the number is five
                            mTextView.setText(5 + "");
                        } else {
                            mTextView.setText("ERROR: Invalid Entry");
                        }
                        break;
                    case 's':
                        if(number.charAt(1)=='i') {
                            // the number is six
                            mTextView.setText(6 + "");
                        } else if(number.charAt(1)=='e') {
                            // the number is seven
                            mTextView.setText(7 + "");
                        } else {
                            mTextView.setText("ERROR: Invalid Entry");
                        }
                        break;
                    case 'e':
                        if(number.charAt(1)=='i') {
                            // the number is eight
                            mTextView.setText(8 + "");
                        } else {
                            mTextView.setText("ERROR: Invalid Entry");
                        }
                        break;
                    case 'n':
                        if(number.charAt(1)=='i') {
                            // the number is nine
                            mTextView.setText(9 + "");
                        } else {
                            mTextView.setText("ERROR: Invalid Entry");
                        }
                        break;
                    default:
                        mTextView.setText("ERROR: Invalid Entry");
                }
            }
        });


    }
}
