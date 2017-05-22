package com.greatcoding.android.courseregisterationhelper;

import android.content.Context;
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

import org.json.JSONObject;

import java.util.List;

/**
 * Created by LouisJH on 2017-05-18.
 */

public class CoursesListAdapter extends BaseAdapter {
    private Context context;
    private List<CoursesMain> courseList;
    private Fragment parent;


    public CoursesListAdapter(Context context, List<CoursesMain> courseList, Fragment parent) {
        this.context = context;
        this.courseList = courseList;
        this.parent = parent;
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
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

        Button addButton = (Button) v.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = MainActivity.userID;
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                                AlertDialog dialog = builder.setMessage("The following course is successfully added.").setPositiveButton("Confirm", null).create();
                                dialog.show();
                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                                AlertDialog dialog = builder.setMessage("The following course has not been added.").setNegativeButton("Confirm", null).create();
                                dialog.show();

                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }

                };
                AddRequest addRequest = new AddRequest(userID, courseList.get(position).getCourseName() + "", responseListener);
                RequestQueue queue = Volley.newRequestQueue(parent.getContext());
                queue.add(addRequest);



            }

        });
        return v;
    }
}


