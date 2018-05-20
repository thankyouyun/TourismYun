package com.goodyun.tourismyun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class IntroActivity extends AppCompatActivity {

    ImageView iv;

    Timer timer = new Timer();

    public static boolean loginOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        iv = findViewById(R.id.logo);
        Animation ani = AnimationUtils.loadAnimation(this, R.anim.appear_search);
        iv.startAnimation(ani);

        timer.schedule(task, 2000);
        loginOnOff=false;
        SharedPreferences preferences = getSharedPreferences("LoginData", MODE_PRIVATE);
        if (preferences.getString("id","자동").equals("자동")) {
            loginOnOff = false;
        } else {
            loginOnOff = true;
        }



        Log.e("testlogin",loginOnOff+"");


    }//onCreate

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };


}
