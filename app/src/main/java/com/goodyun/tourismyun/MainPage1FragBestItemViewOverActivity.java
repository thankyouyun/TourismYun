package com.goodyun.tourismyun;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainPage1FragBestItemViewOverActivity extends AppCompatActivity {


    TextView tvOver,tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page1_frag_best_item_view_over);

        tvTitle = findViewById(R.id.item_over_tv_title);
        tvTitle.setText(getIntent().getStringExtra("Title"));



        tvOver = findViewById(R.id.item_over_tv);

        tvOver.setText(getIntent().getStringExtra("Over"));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tvOver.setTransitionName("OVER");
        }


    }//onCreate


}
