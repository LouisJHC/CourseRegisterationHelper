package com.greatcoding.android.courseregisterationhelper;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by LouisJH on 2017-05-24.
 */

public class Schedule {
    private String firstYr[] = new String[6];
    private String secondYr[] = new String[6];
    private String thirdYr[] = new String[6];
    private String fourthYr[] = new String[6];

    public Schedule() {
        for (int i = 0; i < 6; i++) {
            firstYr[i] = "";
            secondYr[i] = "";
            thirdYr[i] = "";
            fourthYr[i] = "";

        }
    }

    public void addSchedule(String scheduleText) {
        if (scheduleText.charAt(4) == '1' || scheduleText.charAt(5) == '1') {
            for (int i = 0; i < scheduleText.length(); i++) {
                firstYr[i] = "class";
            }
            if (scheduleText.charAt(4) == '2' || scheduleText.charAt(5) == '2') {
                for (int i = 0; i < scheduleText.length(); i++) {
                    secondYr[i] = "class";
                }
                if (scheduleText.charAt(4) == '3' || scheduleText.charAt(5) == '3') {
                    for (int i = 0; i < scheduleText.length(); i++) {
                        thirdYr[i] = "class";
                    }

                    if (scheduleText.charAt(4) == '4' || scheduleText.charAt(5) == '4') {
                        for (int i = 0; i < scheduleText.length(); i++) {
                            fourthYr[i] = "class";
                        }
                    }
                }
            }
        }

    }


    public void addSchedule(String scheduleText, String courseTitle, String courseCampus) {
       String title;
       title = "(" + courseTitle + ")";
        if (scheduleText.charAt(4) == '1' || scheduleText.charAt(5) == '1') {
            for (int i = 0; i < scheduleText.length(); i++) {
                firstYr[i] = scheduleText + title + courseCampus;
            }
            if (scheduleText.charAt(4) == '2' || scheduleText.charAt(5) == '2') {
                for (int i = 0; i < scheduleText.length(); i++) {
                    secondYr[i] = scheduleText + title + courseCampus;
                }
                if (scheduleText.charAt(4) == '3' || scheduleText.charAt(5) == '3') {
                    for (int i = 0; i < scheduleText.length(); i++) {
                        thirdYr[i] = scheduleText + title + courseCampus;
                    }

                    if (scheduleText.charAt(4) == '4' || scheduleText.charAt(5) == '4') {
                        for (int i = 0; i < scheduleText.length(); i++) {
                            fourthYr[i] = scheduleText + title + courseCampus;
                        }
                    }
                }
            }
        }

    }

    public void showSetting(TextView[] firstYr, TextView[] secondYr, TextView[] thirdYr, TextView[] fourthYr, Context context){
        for(int i=0; i < 6; i++){
            if(!this.firstYr[i].equals("")){
                firstYr[i].setText(this.firstYr[i]);
                firstYr[i].setBackgroundColor(context.getResources().getColor(R.color.color3));
            }
            if(!this.secondYr[i].equals("")){
                secondYr[i].setText(this.secondYr[i]);
                secondYr[i].setBackgroundColor(context.getResources().getColor(R.color.color3));
            }
            if(!this.thirdYr[i].equals("")){
                thirdYr[i].setText(this.thirdYr[i]);
                thirdYr[i].setBackgroundColor(context.getResources().getColor(R.color.color3));
            }
            if(!this.fourthYr[i].equals("")){
                fourthYr[i].setText(this.fourthYr[i]);
                fourthYr[i].setBackgroundColor(context.getResources().getColor(R.color.color3));
            }

        }
    }
}

