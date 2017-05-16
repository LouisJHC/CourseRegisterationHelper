package com.greatcoding.android.courseregisterationhelper;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LouisJH on 2017-05-15.
 */

public class ValidationRequest extends StringRequest {
    final static private String URL = "http://matched-excuses.000webhostapp.com/UserValidation.php";
    private Map<String, String> parameters;

    public ValidationRequest(String userID, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
    }


    public Map<String, String> getParameters(){
        return parameters;
    }
}