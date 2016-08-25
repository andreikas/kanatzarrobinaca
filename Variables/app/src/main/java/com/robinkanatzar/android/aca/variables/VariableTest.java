package com.robinkanatzar.android.aca.variables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class VariableTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variable_test);

        //declare variables
        String rstring;
        float rfloat;
        int rint;
        double rdouble;
        boolean rbool;
        char rchar;
        short rshort;
        byte rbyte;

        //assign values to each variable

        //print out the values of each variable
        System.out.println("string: " + rstring);
        System.out.println("float: " + rfloat);
        System.out.println("int: " + rint);
        System.out.println("double: " + rdouble);
        System.out.println("boolean: " + rbool);
        System.out.println("char: " + rchar);
        System.out.println("short: " + rshort);
        System.out.println("byte: " + rbyte);

    }
}
