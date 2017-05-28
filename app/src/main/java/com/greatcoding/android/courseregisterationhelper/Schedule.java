package com.greatcoding.android.courseregisterationhelper;

import android.content.Context;
import android.util.Log;
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
            if (firstYr[0].equals("")) {
                firstYr[0] = scheduleText;

            } else if (firstYr[1].equals("")) {
                firstYr[1] = scheduleText;
            } else if (firstYr[2].equals("")) {
                firstYr[2] = scheduleText;
            } else if (firstYr[3].equals("")) {
                firstYr[3] = scheduleText;
            } else if (firstYr[4].equals("")) {
                firstYr[4] = scheduleText;
            } else if (firstYr[5].equals("")) {
                firstYr[5] = scheduleText;
            }
        }


        if (num == 2 || num1 == 2) {
            if (secondYr[0].equals("")) {
                secondYr[0] = scheduleText;
            } else if (secondYr[1].equals("")) {
                secondYr[1] = scheduleText;
            } else if (secondYr[2].equals("")) {
                secondYr[2] = scheduleText;
            } else if (secondYr[3].equals("")) {
                secondYr[3] = scheduleText;
            } else if (secondYr[4].equals("")) {
                secondYr[4] = scheduleText;
            } else if (secondYr[5].equals("")) {
                secondYr[5] = scheduleText;
            }
        }
        if (num == 3 || num1 == 3) {
            if (thirdYr[0].equals("")) {
                thirdYr[0] = scheduleText;
            } else if (thirdYr[1].equals("")) {
                thirdYr[1] = scheduleText;
            } else if (thirdYr[2].equals("")) {
                thirdYr[2] = scheduleText;
            } else if (thirdYr[3].equals("")) {
                thirdYr[3] = scheduleText;
            } else if (thirdYr[4].equals("")) {
                thirdYr[4] = scheduleText;
            } else if (thirdYr[5].equals("")) {
                thirdYr[5] = scheduleText;
            }
        }

        if (num == 4 || num1 == 4) {
            if (fourthYr[0].equals("")) {
                fourthYr[0] = scheduleText;
            } else if (fourthYr[1].equals("")) {
                fourthYr[1] = scheduleText;
            } else if (fourthYr[2].equals("")) {
                fourthYr[2] = scheduleText;
            } else if (fourthYr[3].equals("")) {
                fourthYr[3] = scheduleText;
            } else if (fourthYr[4].equals("")) {
                fourthYr[4] = scheduleText;
            } else if (fourthYr[5].equals("")) {
                fourthYr[5] = scheduleText;
            }
        }
    }



    public void showSetting(TextView[] firstYr, TextView[] secondYr, TextView[] thirdYr, TextView[] fourthYr, Context context) {
        for (int i = 0; i < 6; i++) {
            if (!this.firstYr[i].equals("")) {
                firstYr[i].setText(this.firstYr[i]);
                firstYr[i].setBackgroundColor(context.getResources().getColor(R.color.color3));
            }
            if (!this.secondYr[i].equals("")) {
                secondYr[i].setText(this.secondYr[i]);
                secondYr[i].setBackgroundColor(context.getResources().getColor(R.color.color3));
            }

            if (!this.thirdYr[i].equals("")) {
                thirdYr[i].setText(this.thirdYr[i]);
                thirdYr[i].setBackgroundColor(context.getResources().getColor(R.color.color3));
            }

            if (!this.fourthYr[i].equals("")) {
                fourthYr[i].setText(this.fourthYr[i]);
                fourthYr[i].setBackgroundColor(context.getResources().getColor(R.color.color3));
            }

        }
    }
}

