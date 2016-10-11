package com.robinkanatzar.android.rck.primefinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button mButton;
    EditText mInput;
    TextView mOutput1;
    TextView mOutput2;
    TextView mOutput3;
    int numPrimesRequested;
    int intArray[] = new int[11];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // link widgets on layout
        mButton = (Button) findViewById(R.id.buttonSubmit);
        mInput = (EditText) findViewById(R.id.editTextInput);
        mOutput1 = (TextView) findViewById(R.id.textViewOutput1);


        // set onClickListener for button
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // when button clicked...
                numPrimesRequested = Integer.parseInt(mInput.getText().toString())
                ;
                if (numPrimesRequested > 3 | numPrimesRequested < 1) {
                    mOutput1.setText("ERROR: Value must be between 1 and 3");
                } else {
                    calculatePrimes(); // calculate three prime numbers
                    setReturnString(); // set return string

                }

            }
        });

    }

    public void calculatePrimes() {

        int primeCount = 0;

        for (int i = 1; i < 10; i++) {
            if (isPrime(i)) {
                intArray[primeCount] = i;
                Log.d("RCK", "intarrayprimecount " + primeCount + " value :" + intArray[primeCount]);
                primeCount = primeCount + 1;
            }
        }
    }

    public void setReturnString() {
        if (numPrimesRequested == 1) {
            mOutput1.setText("" + intArray[0]);
        } else if (numPrimesRequested == 2) {
            mOutput1.setText("" + intArray[0] + ", " + intArray[1]);
        } else if (numPrimesRequested == 3) {
            mOutput1.setText("" + intArray[0] + ", " + intArray[1] + ", " + intArray[2]);
        }
    }

    boolean isPrime(long checkNumber) {
        double root = Math.sqrt(checkNumber);

        for (int i = 2; i <= root; i++) {
            if (checkNumber % i == 0) {
                return false;
            }
        }

        if (checkNumber == 1) {
            return false;
        } else {
            return true;
        }
    }
}
