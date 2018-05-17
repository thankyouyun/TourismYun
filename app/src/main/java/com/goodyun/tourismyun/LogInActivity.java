package com.goodyun.tourismyun;


import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LogInActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    Object loginObject = null;

    Button CustomloginButton, uploadcheck, loadcheck;

    JSONObject jsonData;

    String loginId, loginName, loginEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        uploadcheck = findViewById(R.id.btn_login_uploadcheck);
        uploadcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadStorageLoginData();
            }
        });

        loadcheck = findViewById(R.id.btn_login_loadcheck);
        loadcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadDB();
            }
        });

        callbackManager = CallbackManager.Factory.create();
//        페이스북 기본로그인버튼
//        loginButton = findViewById(R.id.login_button);


        CustomloginButton = (Button) findViewById(R.id.btn_login_facebook);
        CustomloginButton.setOnClickListener(loginListener);


    }//onCreate

//    페이스북 기본로그인버튼
//    public void setFacebookLoginBtn() {
//        loginButton.setReadPermissions("email", "public_profile");
//
//        // Callback registration
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//
//            public void onSuccess(LoginResult loginResult) {
//
//                GraphRequest request = GraphRequest.newMeRequest(
//                        loginResult.getAccessToken(),
//                        new GraphRequest.GraphJSONObjectCallback() {
//                            @Override
//                            public void onCompleted(JSONObject object, GraphResponse response) {
//                                LogInActivity.this.loginObject = object;
//
//                                Log.d("my", object.toString());
//
//
//                            }
//                        });
//
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id,name,email");
//                request.setParameters(parameters);
//                request.executeAsync();
//
//            }
//
//
//            @Override
//            public void onCancel() {
//                // App code
//
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                // App code
//            }
//        });
//    }

    View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LoginManager.getInstance().logInWithReadPermissions(LogInActivity.this, Arrays.asList("public_profile", "email"));
            LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    GraphRequest request = GraphRequest.newMeRequest(
                            loginResult.getAccessToken(),
                            new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(JSONObject object, GraphResponse response) {
                                    LogInActivity.this.loginObject = object;

                                    jsonData = object;
                                    jsonParser();
                                    saveStorageLoginData();

                                    uploadDB();
                                    Log.e("custom", object.toString());
                                }
                            });

                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,name,email");
                    request.setParameters(parameters);
                    request.executeAsync();


                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onError(FacebookException error) {

                }
            });
        }
    };


    public void jsonParser() {

        try {
            loginId = jsonData.getString("id");
            loginName = jsonData.getString("name");
            loginEmail = jsonData.getString("email");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void saveStorageLoginData() {
        SharedPreferences preferences = getSharedPreferences("LoginData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("id", loginId);
        editor.putString("name", loginName);
        editor.putString("email", loginEmail);

        editor.commit();
    }

    public void loadStorageLoginData() {
        SharedPreferences preferences = getSharedPreferences("LoginData", MODE_PRIVATE);
        loginId = preferences.getString("id", "로그인 정보 없음");
        loginName = preferences.getString("name", "로그인 정보 없음");
        loginEmail = preferences.getString("email", "로그인 정보 없음");
        AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this);
        builder.setMessage(loginId + " /  " + loginName + " / " + loginEmail);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    public void uploadDB() {

        String serverUrl = "http://toutt.dothome.co.kr/tour/insertDB.php";

        SimpleMultiPartRequest multiPartRequest = new SimpleMultiPartRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                new AlertDialog.Builder(LogInActivity.this).setMessage(response).setPositiveButton("OK", null).create().show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LogInActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        multiPartRequest.addStringParam("id", loginId);
        multiPartRequest.addStringParam("name", loginName);
        multiPartRequest.addStringParam("email", loginEmail);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(multiPartRequest);

    }//upload

    public void downloadDB() {

        String serverUrl = "http://toutt.dothome.co.kr/tour/login.php";

        SimpleMultiPartRequest multiPartRequest = new SimpleMultiPartRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    new AlertDialog.Builder(LogInActivity.this).setMessage(response).setPositiveButton("OK", null).create().show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        multiPartRequest.addStringParam("email", loginEmail);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(multiPartRequest);

    }

}


