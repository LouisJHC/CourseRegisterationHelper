package com.greatcoding.android.courseregisterationhelper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by LouisJH on 2017-05-18.
 */

public class CoursesListAdapter extends BaseAdapter {
    private Context context;
    private List<CoursesMain> courseList;

    public CoursesListAdapter(Context context, List<CoursesMain> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.coursesmain, null);
        TextView courseSemester = (TextView) v.findViewById(R.id.courseSemester);
        TextView courseName = (TextView) v.findViewById(R.id.courseName);
        TextView courseTitle = (TextView) v.findViewById(R.id.courseTitle);
        TextView courseProf = (TextView) v.findViewById(R.id.courseProf);
        TextView courseSeats = (TextView) v.findViewById(R.id.courseSeats);
        TextView courseCampus = (TextView) v.findViewById(R.id.courseCampus);

        courseTitle.setText(courseList.get(position).getCourseTitle());
        courseSemester.setText(courseList.get(position).getCourseSemester());
        courseName.setText(courseList.get(position).getCourseName());
        courseTitle.setText(courseList.get(position).getCourseTitle());
        courseProf.setText(courseList.get(position).getCourseProf());
        courseSeats.setText(courseList.get(position).getCourseSeats());
        courseCampus.setText(courseList.get(position).getCourseCampus());


        v.setTag(courseList.get(position).getCourseName());
        return v;
    }
}


