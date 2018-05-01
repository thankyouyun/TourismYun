package com.goodyun.tourismyun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SeachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);


    }//create

    public void clickback(View v){

        finish();

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.disappear_search,R.anim.disappear_search);
    }
}
