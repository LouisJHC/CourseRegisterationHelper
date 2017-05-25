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



    public void addSchedule(String scheduleText, String courseTitle, String courseCampus) {
        int num = Character.getNumericValue(scheduleText.charAt(4));
        int num1 = Character.getNumericValue(scheduleText.charAt(5));

        if (num == 1 || num1 == 1) {
            for(int i = 0; i < 6;i++){
                if(firstYr[i].equals("")) {
                    firstYr[i] = scheduleText + courseTitle;
                    break;
                }
            }
        }


        if (num == 2 || num1 == 2) {
            for(int i = 0; i < 6;i++){
                if(secondYr[i].equals("")) {
                    secondYr[i] = scheduleText + courseTitle;
                    break;
                }
            }
        }

        if (num == 3 || num1 == 3) {
            for(int i = 0; i < 6;i++){
                if(thirdYr[i].equals("")){
                    thirdYr[i] = scheduleText + courseTitle;
                    break;
                }
            }
        }


        if (num == 4 || num1 == 4) {
            for(int i = 0; i < 6;i++){
                if(fourthYr[i].equals("")) {
                    fourthYr[i] = scheduleText + courseTitle;
                    break;
                }
            }
        }



    }




    public void showSetting(TextView[] firstYr, TextView[] secondYr, TextView[] thirdYr, TextView[] fourthYr, Context context){
        for(int i=0; i < 6; i++){
            if(!this.firstYr[i].equals("")) {
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

