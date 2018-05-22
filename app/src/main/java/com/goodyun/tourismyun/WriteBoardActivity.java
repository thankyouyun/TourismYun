package com.goodyun.tourismyun;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.util.Date;

public class WriteBoardActivity extends AppCompatActivity {

    EditText writeLo, writeTitle,writePlace;
    TextView tvCalendar;
    int year, mon, day;
    String loginId, loginName, loginEmail,createTime;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_board);

        tvCalendar = findViewById(R.id.write_calendar);
        writeLo = findViewById(R.id.write_edit);
        writeTitle = findViewById(R.id.write_title);
        writePlace = findViewById(R.id.write_place);


        createTime = DateFormat.getDateTimeInstance().format(new Date());




        loadStorageLoginData();


    }//onCreate


    public void clickCalendar(View v) {
        Intent intent = new Intent(WriteBoardActivity.this, CalendarActivity.class);
        startActivityForResult(intent, 1);

    }//calendar


    public void loadStorageLoginData() {

        preferences = getSharedPreferences("LoginData", MODE_PRIVATE);
        loginId = preferences.getString("id", "null");
        loginName = preferences.getString("name", "null");
        loginEmail = preferences.getString("email", "null");
    }//load


    public void clickRegister(View v) {
        String st = writeTitle.getText().toString();
        st.replace(" ", "");
        String sp = writePlace.getText().toString();
        sp.replace(" ","");

        if(sp.equals("")) {
            Toast.makeText(WriteBoardActivity.this, "여행장소를 입력해주세요 :)", Toast.LENGTH_SHORT).show();
        }else {

            if (st.equals("")) {
                Toast.makeText(WriteBoardActivity.this, "제목을 입력해주세요 :)", Toast.LENGTH_SHORT).show();
            } else {
                if (writeLo.getText().toString().equals("")) {
                    Toast.makeText(WriteBoardActivity.this, "한마디를 입력해주세요 :)", Toast.LENGTH_SHORT).show();
                } else {

                    if (year == 0) {
                        Toast.makeText(WriteBoardActivity.this, "날짜를 입력해주세요 :)", Toast.LENGTH_SHORT).show();
                    } else {
                        String serverUrl = "http://toutt.dothome.co.kr/tour/boardDB.php";

                        SimpleMultiPartRequest multiPartRequest = new SimpleMultiPartRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                //응답 메세지가 reponse이다 결과값..ex) email,name ...
                                Toast.makeText(WriteBoardActivity.this, response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(WriteBoardActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                        multiPartRequest.addStringParam("id", loginId);
                        multiPartRequest.addStringParam("title", writeTitle.getText().toString());
                        multiPartRequest.addStringParam("datecr", createTime);
                        multiPartRequest.addStringParam("name", loginName);
                        multiPartRequest.addStringParam("date", year + "." + mon + "." + day);
                        multiPartRequest.addStringParam("place",writePlace.getText().toString());
                        multiPartRequest.addStringParam("talk", writeLo.getText().toString());


                        RequestQueue requestQueue = Volley.newRequestQueue(this);

                        requestQueue.add(multiPartRequest);
                        finish();
                    }//날짜 선택 했는가
                }//텍스트 입력했는가
            }//제목 입력했는가
        }//장소 입력했는가
    }//register


    public void clickFinish(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(WriteBoardActivity.this);
        builder.setTitle("Exit");
        builder.setIcon(R.drawable.finish_fab);
        builder.setMessage("한마디 적기를 나가시겠습니까? \n(결과는 저장되지 않습니다)");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });


        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog webdialog = builder.create();
        webdialog.setCanceledOnTouchOutside(false);
        webdialog.show();
    }//finish


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            year = data.getIntExtra("Year", 0);
            mon = data.getIntExtra("Month", 0);
            day = data.getIntExtra("Day", 0);
            tvCalendar.setText("선택날짜 : " + year + "년 " + mon + "월 " + day + "일");

        }
    }//onResult

}//main
