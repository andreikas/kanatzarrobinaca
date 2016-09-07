package com.robinkanatzar.android.aca.comicbooks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
/*
Modify the ComicBooks app to allow the user to enter in the initial values for a comic book.
You’ll need to use an EditText for each of the variables (title, issue number, condition, base price).

Display the data back to the user via a TextView.
 */
    private EditText mTitle;
    private EditText mIssueNum;
    private EditText mCondition;
    private EditText mBasePrice;
    private Button mButton;
    private TextView mOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = (EditText) findViewById(R.id.titleEditText);
        mIssueNum = (EditText) findViewById(R.id.issueEditText);
        mCondition = (EditText) findViewById(R.id.conditionEditText);
        mBasePrice = (EditText) findViewById(R.id.baseEditText);
        mButton = (Button) findViewById(R.id.submitButton);
        mOutput = (TextView) findViewById(R.id.outputTextView);

        // Set up hash map
        final HashMap quality = new HashMap();

        float price1 = 3.00F;
        quality.put("mint", price1);

        float price2 = 2.00F;
        quality.put("near mint", price2);

        float price3 = 1.50F;
        quality.put("very fine", price3);

        float price4 = 1.00F;
        quality.put("fine", price4);

        float price5 = 0.50F;
        quality.put("good", price5);

        float price6 = 0.25F;
        quality.put("poor", price6);

        // initialize variables
        mTitle.setText("default tile");
        mIssueNum.setText("default issue number");
        mCondition.setText("poor");
        mBasePrice.setText("1.0");

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // when the button is clicked...

                Comic userComic = new Comic(mTitle.getText().toString(), mIssueNum.getText().toString(), mCondition.getText().toString(), Float.parseFloat(mBasePrice.getText().toString()));
                userComic.setPrice((Float) quality.get(userComic.condition));

                mOutput.setText("The adjusted price is: $" + userComic.getPrice());
            }
        });


        /*
        // set up a collection
        Comic[] comix = new Comic[3];  // Set up a Comic array that will hold 3 comics

        // Add comics to the collection
        comix[0] = new Comic("Amazing Spider-Man", "1A", "very fine", 12_000.00F);
        comix[0].setPrice( (Float) quality.get(comix[0].condition));

        comix[1] = new Comic("The Incredible Hulk", "181", "near mint", 680.00F);
        comix[1].setPrice( (Float) quality.get(comix[1].condition));

        comix[2] = new Comic("Cerebus", "1A", "good", 190.00F);
        comix[2].setPrice( (Float) quality.get(comix[2].condition));

        for (int i = 0; i < comix.length; i++) {
            System.out.println("Title: " + comix[i].title);
            System.out.println("Issue: " + comix[i].issueNumber);
            System.out.println("Condition: " + comix[i].condition);
            System.out.println("Price: $" + comix[i].price + "\n");
        }*/


    }

    class Comic {
        String title;
        String issueNumber;
        String condition;
        float basePrice;
        float price;

        Comic(String inTitle, String inIssueNumber, String inCondition, float inBasePrice) {
            title = inTitle;
            issueNumber = inIssueNumber;
            condition = inCondition;
            basePrice = inBasePrice;
        }

        void setPrice(float factor) {
            price = basePrice * factor;

        }

        public float getPrice() {
            return price;
        }
    }
}
