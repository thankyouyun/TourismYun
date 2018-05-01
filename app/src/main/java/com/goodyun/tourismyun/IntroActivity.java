package com.goodyun.tourismyun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class IntroActivity extends AppCompatActivity {

    ImageView iv;

    Timer timer= new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        iv =findViewById(R.id.yun_soft);
        Animation ani = AnimationUtils.loadAnimation(this,R.anim.appear_logo);
        iv.startAnimation(ani);

        timer.schedule(task,1000);




    }//onCreate

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            Intent intent = new Intent(IntroActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };



}
