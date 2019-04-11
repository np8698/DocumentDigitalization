package com.advanced.ocr.calculation;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity{

    private final static String TAG = LoginActivity.class.getSimpleName();

    private EditText etPassword;
    private EditText etUsername;

    private Toast mToast = null;


    private String sUsername;
    private String sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialUISetup();

    }

    private void initialUISetup() {

        etPassword = findViewById(R.id.etPassword);
        etUsername = findViewById(R.id.etUsername);

    }

    public void logInButton(View view) {

        sPassword = etPassword.getText().toString().trim();
        sUsername = etUsername.getText().toString().trim();

        if (sUsername.isEmpty()) {
            showToast("Please Enter Username");
            return;
        }

        if (sPassword != null && !sPassword.isEmpty()) {
            if (sPassword.length() < 6) {
                showToast("Password length should be atleast 6 characters");
                return;
            }
        } else {
            showToast("Please Enter Password");
            return;
        }
        Log.d(TAG, "logInButton: abcd");

        logInRequest();

    }

    private void logInRequest(){

        Log.d(TAG, "logInRequest: ");

        String  tag_string_req = "string_req";

        String url = "http://192.168.0.108:57700/login";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("response",response);
                            JSONObject mobject = new JSONObject(response);

                            int iRespnseCode = mobject.getInt("response_code");

                            if(iRespnseCode==100) {
                                showToast(iRespnseCode+"");

                                Intent intent = new Intent(LoginActivity.this, ScanAndAllDocActivity.class);
                                startActivity(intent);
                            }
                            else{
                                showToast("Wrong Username/Password");
                            }

                        } catch (JSONException e) {
                            Log.d("Catch",e.toString());

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error",error.toString());

                        showToast("There is some Error!!");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", sUsername);
                params.put("password", sPassword);

                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, tag_string_req);
    }

    private void showToast(String sToast) {

        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, sToast, Toast.LENGTH_SHORT);
        mToast.show();

    }

}
