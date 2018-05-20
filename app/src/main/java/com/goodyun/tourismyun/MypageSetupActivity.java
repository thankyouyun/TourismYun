package com.goodyun.tourismyun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MypageSetupActivity extends AppCompatActivity {

    RelativeLayout logout,ask,bers;
    TextView tvLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_page_setup);
        logout = findViewById(R.id.tv_my_logout);
        tvLog =findViewById(R.id.setup_log_tv);
        ask = findViewById(R.id.tv_my_ask);
        bers = findViewById(R.id.tv_my_bers);



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvLog.getText().toString().contains("아웃")) {
                    SharedPreferences preferences = getSharedPreferences("LoginData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.commit();
                    IntroActivity.loginOnOff = false;
                    tvLog.setText("로그인");

                }else{

                    startActivity(new Intent(MypageSetupActivity.this,LogInActivity.class));
                }


            }
        });




    }//onCreate


    @Override
    protected void onResume() {
        super.onResume();

    }
}
