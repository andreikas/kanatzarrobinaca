package com.robinkanatzar.android.aca.codekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
/*
Modify the CodeKeeper app to accept user input via an EditText widget.
Display the list of codes to the user via a TextView
 */
    private EditText mEditText;
    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.addButton);
        mEditText = (EditText) findViewById(R.id.editText);
        mTextView = (TextView) findViewById(R.id.textView);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // when the button is clicked...
                String newString = mEditText.getText().toString();
                String[] userStrings = {"omega", "beta", "omicron", newString};
                CodeKeeper keeper = new CodeKeeper(userStrings);
            }
        });
    }

    public class CodeKeeper {
        ArrayList list;
        String[] codes = {"lambda", "gamma", "delta", "zeta"};

        public CodeKeeper(String[] userCodes) {
            list = new ArrayList();
            String outputString = "alpha";

            // load built-in codes
            for (int i = 0; i < codes.length; i++) {
                addCode(codes[i]);
            }

            // load user codes
            for (int j = 0; j < userCodes.length; j++) {
                addCode(userCodes[j]);
            }

            // display all the codes
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                String output = (String) iterator.next();
                //System.out.println(output);
                // change this to display on a TextView
                //mTextView.setText(output);
                outputString = outputString + ", " + output;
            }
            //System.out.println(outputString);
            mTextView.setText(outputString);
        }

        private void addCode(String code) {
            if (!list.contains(code)) {
                list.add(code);
            }
        }
    }
}
