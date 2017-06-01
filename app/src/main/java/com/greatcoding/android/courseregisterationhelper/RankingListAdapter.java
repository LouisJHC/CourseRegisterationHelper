package com.greatcoding.android.courseregisterationhelper;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

/**
 * Created by LouisJH on 2017-05-31.
 */

public class RankingListAdapter extends BaseAdapter {
    private Context context;
    private List<CoursesMain> coursesList;
    private Fragment parent;





    public RankingListAdapter(Context context, List<CoursesMain> coursesList, Fragment parent) {
        this.context = context;
        this.coursesList = coursesList;
        this.parent = parent;
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
        View v = View.inflate(context, R.layout.ranking, null);
        TextView courseSemester = (TextView) v.findViewById(R.id.courseSemester);
        TextView courseName = (TextView) v.findViewById(R.id.courseName);
        TextView courseTitle = (TextView) v.findViewById(R.id.courseTitle);
        TextView courseProf = (TextView) v.findViewById(R.id.courseProf);
        TextView courseSeats = (TextView) v.findViewById(R.id.courseSeats);
        TextView courseCampus = (TextView) v.findViewById(R.id.courseCampus);
        TextView rankingTextView = (TextView) v.findViewById(R.id.rankingTextView);


        courseSemester.setText(coursesList.get(position).getCourseSemester());
        courseName.setText(coursesList.get(position).getCourseName());
        courseTitle.setText(coursesList.get(position).getCourseTitle());
        courseSeats.setText(coursesList.get(position).getCourseSeats());
        courseCampus.setText(coursesList.get(position).getCourseCampus());
        rankingTextView.setText((position + 1) + "");
        if(position != 0){
            rankingTextView.setBackgroundColor(parent.getResources().getColor(R.color.color3));
        }

        if(coursesList.get(position).getCourseProf().equals("")){
            courseProf.setText("N/A");
        }else{
            courseProf.setText(coursesList.get(position).getCourseProf());
        }


        v.setTag(coursesList.get(position).getCourseName());
        return v;
    }
}


