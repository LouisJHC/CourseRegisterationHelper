package com.greatcoding.android.courseregisterationhelper;

/**
 * Created by LouisJH on 2017-05-18.
 */

public class CoursesMain {

    String courseSemester;
    String courseName;
    String courseTitle;
    String courseSeats;
    String courseProf;
    String courseCampus;
    public String getCourseSemester() {
        return courseSemester;
    }

    public void setCourseSemester(String courseSemester) {
        this.courseSemester = courseSemester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseSeats() {
        return courseSeats;
    }

    public void setCourseSeats(String courseSeats) {
        this.courseSeats = courseSeats;
    }

    public String getCourseProf() {
        return courseProf;
    }

    public void setCourseProf(String courseProf) {
        this.courseProf = courseProf;
    }

    public String getCourseCampus() {
        return courseCampus;
    }

    public void setCourseCampus(String courseCampus) {
        this.courseCampus = courseCampus;
    }


    public CoursesMain(String courseSemester, String courseName, String courseTitle, String courseSeats, String courseProf, String courseCampus) {
        this.courseSemester = courseSemester;
        this.courseName = courseName;
        this.courseTitle = courseTitle;
        this.courseSeats = courseSeats;
        this.courseProf = courseProf;
        this.courseCampus = courseCampus;
    }

        public CoursesMain(String courseSemester, String courseName, String courseTitle, String courseCampus) {
            this.courseSemester = courseSemester;
            this.courseName = courseName;
            this.courseTitle = courseTitle;
            this.courseCampus = courseCampus;


    }

    public CoursesMain(String courseSemester, String courseName, String courseTitle, String courseSeats, String courseCampus) {
        this.courseSemester = courseSemester;
        this.courseName = courseName;
        this.courseTitle = courseTitle;
        this.courseSeats = courseSeats;
        this.courseCampus = courseCampus;
    }
}
