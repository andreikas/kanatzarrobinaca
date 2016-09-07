package com.robinkanatzar.android.aca.holidayschedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.BitSet;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    /*
    Modify the HolidaySchedule app to allow the user to enter a specific date (Month and Day) and then
    run calculations to determine if that date is a holiday.

    Display the results to the user via a TextView.
     */
    private EditText mDate;
    private Button mButton;
    private TextView mOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDate = (EditText) findViewById(R.id.dateEditText);
        mButton = (Button) findViewById(R.id.submitButton);
        mOutput = (TextView) findViewById(R.id.outputTextView);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // when the button is clicked...
                HolidaySchedule calculate = new HolidaySchedule();

                String month = mDate.getText().toString().substring(0,2);
                String day = mDate.getText().toString().substring(2,4);
                Integer intDate = 0; // the integer version of the date to check

                //Log.d("RCK", "month = " + month + " day = " + day);

                if(Objects.equals(month, "01")) {
                    //01 - 31
                    intDate = Integer.parseInt(day);
                    //Log.d("RCK","Month is 01");
                } else if(Objects.equals(month, "02")) {
                    //02 - 28
                    intDate = 31 + Integer.parseInt(day);
                    //Log.d("RCK","Month is 02");
                } else if(Objects.equals(month, "03")) {
                    //03 - 31
                    intDate = 31 + 28 + Integer.parseInt(day);
                } else if(Objects.equals(month, "04")) {
                    //04 - 30
                    intDate = 31 + 28 + 31 + Integer.parseInt(day);
                } else if(Objects.equals(month, "05")) {
                    //05 - 31
                    intDate = 31 + 28 + 31 + 30 + Integer.parseInt(day);
                } else if(Objects.equals(month, "06")) {
                    //06 - 30
                    intDate = 31 + 28 + 31 + 30 + 31 + Integer.parseInt(day);
                } else if(Objects.equals(month, "07")) {
                    //07 - 31
                    intDate = 31 + 28 + 31 + 30 + 31 + 30 + Integer.parseInt(day);
                } else if(Objects.equals(month, "08")) {
                    //08 - 31
                    intDate = 31 + 28 + 31 + 30 + 31 + 30 + 31 + Integer.parseInt(day);
                } else if(Objects.equals(month, "09")) {
                    //09 - 30
                    intDate = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + Integer.parseInt(day);
                } else if(Objects.equals(month, "10")) {
                    //10 - 31
                    intDate = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + Integer.parseInt(day);
                } else if(Objects.equals(month, "11")) {
                    //11 - 30
                    intDate = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + Integer.parseInt(day);
                } else if(Objects.equals(month, "12")) {
                    //12 - 31
                    intDate = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30 + Integer.parseInt(day);
                }
                //Log.d("RCK", "intDate = " + intDate);

                // set the value of the output text view
                if(calculate.isHoliday(intDate)) {
                    // the date is a holiday
                    mOutput.setText("A HOLIDAY");
                } else if(!calculate.isHoliday(intDate)) {
                    // the date is not a holiday
                    mOutput.setText("NOT A HOLIDAY");
                } else {
                    // some error
                    mOutput.setText("ERROR: Invalid Input");
                }

            }
        });

        /*HolidaySchedule cal = new HolidaySchedule();
        String day = "2";

        if (!day.equals("")) {
            try {
                int whichDay = Integer.parseInt(day);
                if (cal.isHoliday(whichDay)) {
                    System.out.println("Day number " + whichDay +
                            " is a holiday.");
                } else {
                    System.out.println("Day number " + whichDay +
                            " is not a holiday.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Error: " + nfe.getMessage());
            }
        }*/
    }

    public class HolidaySchedule {
        BitSet sked;

        public HolidaySchedule() {
            sked = new BitSet(365);
            int[] holiday = {
                    1, // 0101
                    15, // 0115
                    50, // 50-31 = 19, 0219
                    148, // 148-31-28 = 89, 89-31-30 = 28, 0528
                    185, // +37 to the last, 28+37-31-30 = 0704
                    246, // +61 to last, 31 +30 + 4 =  0904
                    281, // +35, 1024
                    316, // 1113
                    326, // 1123
                    359 }; // 1225
            //int[] holiday = {0101, 0115, 0219, 0528, 0704, 0904, 1024, 1113, 1123, 1225};

            for (int i = 0; i < holiday.length; i++) {
                addHoliday(holiday[i]);
            }
        }

        public void addHoliday(int dayToAdd) {
            sked.set(dayToAdd);
        }

        public boolean isHoliday(int dayToCheck) {
            return sked.get(dayToCheck);
        }
    }


}
