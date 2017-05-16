package com.greatcoding.android.courseregisterationhelper;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
    private Spinner spinner;
    private AlertDialog dialog;
    private boolean validate = false;
    private String userID;
    private String userPassword;
    private String userMajor;
    private String userGender;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        adapter = ArrayAdapter.createFromResource(this, R.array.Major, android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.majorSpinner);

        spinner.setAdapter(adapter);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText pwText = (EditText) findViewById(R.id.pwText);
        final EditText emText= (EditText) findViewById(R.id.emText);
        RadioGroup genGroup = (RadioGroup) findViewById(R.id.genGroup);

        int genGroupID = genGroup.getCheckedRadioButtonId();
        userGender = ((RadioButton) findViewById(genGroupID)).getText().toString();

        genGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton genButton = (RadioButton) findViewById(checkedId);
                userGender = genButton.getText().toString();
            }
        });

        final Button validationButton = (Button) findViewById(R.id.validationButton);
        validationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = idText.getText().toString();

                if(validate){
                    return;
                }
                if(userID.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("ID cannot be blank").setPositiveButton("Confirm", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("ID is available for use").setPositiveButton("Confirm", null).create();
                                dialog.show();
                                idText.setEnabled(false);
                                validate = true;
                                idText.setBackgroundColor(getResources().getColor(R.color.color0));
                                validationButton.setBackgroundColor(getResources().getColor(R.color.color0));
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("ID is not available for use.").setNegativeButton("Confirm", null).create();
                                dialog.show();

                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }

                };
                ValidationRequest validationRequest = new ValidationRequest(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validationRequest);
            }
        });

        Button regButton = (Button) findViewById(R.id.regButton);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID = idText.getText().toString();
                userPassword = pwText.getText().toString();
                userMajor = spinner.getSelectedItem().toString();
                userEmail = emText.getText().toString();


                if (!validate) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("Please check your ID first.").setNegativeButton("Confirm", null).create();
                    dialog.show();
                    return;

                }

                if(userID.equals("")||userPassword.equals("")||userMajor.equals("")||userGender.equals("")||userEmail.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("Please fill all the criteria.").setNegativeButton("Confirm", null).create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("Registration has been successful.").setPositiveButton("Confirm", null).create();
                                dialog.show();
                                finish();
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("Registration has failed.").setNegativeButton("Confirm", null).create();
                                dialog.show();

                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }

                };
                RegistrationRequest registrationRequest = new RegistrationRequest(userID, userPassword, userMajor, userGender, userEmail, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registrationRequest);
            }
        });

    }

    @Override
    protected void onStop(){
        super.onStop();
        if(dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }
}
