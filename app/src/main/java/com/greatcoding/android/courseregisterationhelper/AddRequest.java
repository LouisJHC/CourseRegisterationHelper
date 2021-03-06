package com.greatcoding.android.courseregisterationhelper;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LouisJH on 2017-05-15.
 */

public class AddRequest extends StringRequest {
    final static private String URL = "http://matched-excuses.000webhostapp.com/AddCourse.php";
    private Map<String, String> parameters;

    public AddRequest(String userID, String courseName, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("courseName", courseName);
    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
