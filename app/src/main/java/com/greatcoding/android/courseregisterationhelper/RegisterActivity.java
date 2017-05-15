package com.greatcoding.android.courseregisterationhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        adapter = ArrayAdapter.createFromResource(this, R.array.Major, android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.majorSpinner);

        spinner.setAdapter(adapter);
    }
}
