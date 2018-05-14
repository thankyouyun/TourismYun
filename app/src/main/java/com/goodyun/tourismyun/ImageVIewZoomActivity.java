package com.goodyun.tourismyun;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ImageVIewZoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_zoom);

        ImageView iv = findViewById(R.id.iv);
        TextView tv = findViewById(R.id.tv);
        Intent intent= getIntent();
        String title= intent.getStringExtra("Title");
        String img= intent.getStringExtra("Img");

        Glide.with(this).load(img).into(iv);
        tv.setText(title);


        //iv에게 Transition의 Pair를 위한 이름 부여
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            iv.setTransitionName("IMG");
        }

    }


}
