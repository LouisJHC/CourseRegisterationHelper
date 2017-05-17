package com.greatcoding.android.courseregisterationhelper;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout linear;
    Button coursesButton, timeButton, statButton;
    CoursesFragment fragment1;
    TimeFragment fragment2;
    StatFragment fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coursesButton = (Button) findViewById(R.id.coursesButton);
        timeButton = (Button) findViewById(R.id.timeButton);
        statButton = (Button) findViewById(R.id.statButton);
        linear = (LinearLayout) findViewById(R.id.linear);
        fragment1 = new CoursesFragment();
        fragment2 = new TimeFragment();
        fragment3 = new StatFragment();

        coursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear.setVisibility(View.GONE);
                coursesButton.setBackgroundColor(getResources().getColor(R.color.color8));
                timeButton.setBackgroundColor(getResources().getColor(R.color.color9));
                statButton.setBackgroundColor(getResources().getColor(R.color.color9));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment1).commit();
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear.setVisibility(View.GONE);
                coursesButton.setBackgroundColor(getResources().getColor(R.color.color9));
                timeButton.setBackgroundColor(getResources().getColor(R.color.color8));
                statButton.setBackgroundColor(getResources().getColor(R.color.color9));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment2).commit();
            }
        });

        statButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear.setVisibility(View.GONE);
                coursesButton.setBackgroundColor(getResources().getColor(R.color.color9));
                timeButton.setBackgroundColor(getResources().getColor(R.color.color9));
                statButton.setBackgroundColor(getResources().getColor(R.color.color8));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment3).commit();
            }
        });
    }

    private long lastTimeBackPressed;

    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis() - lastTimeBackPressed < 1500){
            finish();
            return;
        }
        Toast.makeText(this, "Please press back button one more time to finish application", Toast.LENGTH_SHORT);
        lastTimeBackPressed = System.currentTimeMillis();
    }
}
