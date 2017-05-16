package com.greatcoding.android.courseregisterationhelper;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LouisJH on 2017-05-15.
 */

public class RegistrationRequest extends StringRequest {
    final static private String URL = "http://matched-excuses.000webhostapp.com/UserRegistration.php";
    private Map<String, String> parameters;

    public RegistrationRequest(String userID, String userPassword, String userMajor, String userGender, String userEmail, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userMajor", userMajor);
        parameters.put("userGender", userGender);
        parameters.put("userEmail", userEmail);
    }


    public Map<String, String> getParameters(){
        return parameters;
    }
}
