package com.greatcoding.android.courseregisterationhelper;

/**
 * Created by LouisJH on 2017-06-14.
 */

public class Notice {

    String notice;
    String date;
    String name;

    public Notice(String notice, String date, String name) {
        this.notice = notice;
        this.date = date;
        this.name = name;
    }

    public String getNotice(){
        return notice;
    }

    public void setNotice(String notice){
        this.notice = notice;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
