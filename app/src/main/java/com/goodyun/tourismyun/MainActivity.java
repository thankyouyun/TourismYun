package com.goodyun.tourismyun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    Fragment changeFrag;
    LinearLayout changeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeLayout = findViewById(R.id.change);

        changeFrag = new MainHomeFrag();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.change, changeFrag).commit();


        //바텀네비게이션뷰
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(onBottomNavigationItemSelectedListener);

    }//onCreate


    public void clickSearch(View v) {

        startActivity(new Intent(MainActivity.this, SearchActivity.class));
        overridePendingTransition(R.anim.appear_search, R.anim.appear_search);
    }


    BottomNavigationView.OnNavigationItemSelectedListener onBottomNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Intent intent;

            switch (item.getItemId()) {
                case R.id.action_home:
                    changeFrag = new MainHomeFrag();

                    break;

                case R.id.action_plus:
                    intent = new Intent(MainActivity.this, RoadActivity.class);
                    startActivity(intent);
                    return true;

                case R.id.action_my:
                    changeFrag = new MyPageFrag();
                    break;

                case R.id.action_recent:
                    intent = new Intent(MainActivity.this, RecentActivity.class);
                    startActivity(intent);

                    return true;

            }//switch
            if (changeFrag != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.change, changeFrag).commit();
            }
            return true;
        }

    };

}
