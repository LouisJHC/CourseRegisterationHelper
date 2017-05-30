package com.greatcoding.android.courseregisterationhelper;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LouisJH on 2017-05-29.
 */

public class StatisticsListAdapter extends BaseAdapter {
    private Context context;
    private List<CoursesMain> coursesList;
    private Fragment parent;
    private String userID = MainActivity.userID;






    public StatisticsListAdapter(Context context, List<CoursesMain> coursesList, Fragment parent) {
        this.context = context;
        this.coursesList = coursesList;
        this.parent = parent;
    }


    public double parse(String rate) {
        if (rate.contains("/")) {
            String[] rate1 = rate.split("/");
            return (Double.parseDouble(rate1[0])*100) / Double.parseDouble(rate1[1]);
        }else{
                return 0;
        }
    }


    @Override
    public int getCount() {
        return coursesList.size();
    }

    @Override
    public Object getItem(int position) {
        return coursesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View v = View.inflate(context, R.layout.statisticsmain, null);
        TextView courseSemester = (TextView) v.findViewById(R.id.courseSemester);
        TextView courseName = (TextView) v.findViewById(R.id.courseName);
        TextView courseTitle = (TextView) v.findViewById(R.id.courseTitle);
        TextView courseSeats = (TextView) v.findViewById(R.id.courseSeats);
        TextView courseCampus = (TextView) v.findViewById(R.id.courseCampus);
        TextView courseRate = (TextView) v.findViewById(R.id.courseRate);

        courseTitle.setText(coursesList.get(position).getCourseTitle());
        courseSemester.setText(coursesList.get(position).getCourseSemester());
        courseName.setText(coursesList.get(position).getCourseName());
        courseTitle.setText(coursesList.get(position).getCourseTitle());
        courseSeats.setText(coursesList.get(position).getCourseSeats());
        courseCampus.setText(coursesList.get(position).getCourseCampus());



        Double rate = parse(coursesList.get(position).getCourseSeats());
        int rate1 = rate.intValue();

        courseRate.setText("Competition rate:" + rate1 + "%");

        if(rate < 30){
            courseRate.setText("Competition rate: [Low] = " + rate1 + "%");
            courseRate.setTextColor(parent.getResources().getColor(R.color.color10));
        }else if (rate <= 70){
            courseRate.setText("Competition rate: [Medium] = " + rate1 + "%");
            courseRate.setTextColor(parent.getResources().getColor(R.color.color7));
        }else if (rate <= 100){
            courseRate.setText("Competition rate: [High] = " + rate1 + "%");
            courseRate.setTextColor(parent.getResources().getColor(R.color.color4));
        }


        v.setTag(coursesList.get(position).getCourseName());
        return v;
    }
}


