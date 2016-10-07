package com.robinkanatzar.android.rck.googletranslateexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class MainActivity extends AppCompatActivity {

    // TODO tutorial on this website for the microsoft / bing translate api
    // TODO mycodeandlife.wordpress.com/2013/02/05/android-tutorials-language-translator-app/

    // my client secret
    // WS6wPj5oN4V5hKnKl8wqVecryY7ZPhT02g48YY/iLoo=

    // my client id
    // GoogleTranslateExample

    String translatedText = "";
    TextView mTranslated;
    Button mButtonTranslate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Translate.setClientId("GoogleTranslateExample"); //Change this
        //Translate.setClientSecret("WS6wPj5oN4V5hKnKl8wqVecryY7ZPhT02g48YY/iLoo="); //change

        Translate.setKey("WS6wPj5oN4V5hKnKl8wqVecryY7ZPhT02g48YY/iLoo=");

        /*mButtonTranslate = (Button) findViewById(R.id.bTranslate);
        mTranslated = (TextView) findViewById(R.id.tvTranslatedText);

        mButtonTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String text = ((EditText) findViewById(R.id.etUserText)).getText().toString();
                    translatedText = translate(text);
                } catch (Exception e) {
                    e.printStackTrace();
                    translatedText = e.toString();
                }

                mTranslated.setText(translatedText);
            }
        });*/


        ((Button) findViewById(R.id.bTranslate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                class bgStuff extends AsyncTask<Void, Void, Void> {

                    String translatedText = "";
                    @Override
                    protected Void doInBackground(Void... params) {
                        // TODO Auto-generated method stub
                        try {
                            String text = ((EditText) findViewById(R.id.etUserText)).getText().toString();
                            translatedText = translate(text);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            translatedText = e.toString();
                        }

                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        // TODO Auto-generated method stub
                        ((TextView) findViewById(R.id.tvTranslatedText)).setText(translatedText);
                        super.onPostExecute(result);
                    }

                }

                new bgStuff().execute();
            }
        });
    }

    public String translate(String text) throws Exception{

        String translatedText = "";

        translatedText = Translate.execute(text,Language.FRENCH);

        return translatedText;
    }
}
