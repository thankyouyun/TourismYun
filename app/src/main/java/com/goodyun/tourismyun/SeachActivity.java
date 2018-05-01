package com.goodyun.tourismyun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SeachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);


    }//create

    public void clickback(View v){

        finish();

    }

    public void clickSearch(View v){

        Toast.makeText(this, "검색한다", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.disappear_search,R.anim.disappear_search);
    }


}
