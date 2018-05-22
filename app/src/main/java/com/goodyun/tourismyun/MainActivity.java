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

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    Fragment[] frags = new Fragment[4];
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        frags[0] = new HomeFrag();
        frags[1] = new RoadFrag();
        frags[2] = new MyPageFrag();
        frags[3] = new RecentFrag();

        transaction= getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.change,frags[0]);
        transaction.add(R.id.change,frags[1]);
        transaction.add(R.id.change,frags[2]);
        transaction.add(R.id.change,frags[3]);
        transaction.commit();

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

            frags[0].getView().setVisibility(View.GONE);
            frags[1].getView().setVisibility(View.GONE);
            frags[2].getView().setVisibility(View.GONE);
            frags[3].getView().setVisibility(View.GONE);



            switch (item.getItemId()) {
                case R.id.action_home:
                    frags[0].getView().setVisibility(View.VISIBLE);

                    break;
                case R.id.action_plus:
                    frags[1].getView().setVisibility(View.VISIBLE);

                    break;
                case R.id.action_my:
                    frags[2].getView().setVisibility(View.VISIBLE);

                    break;

                case R.id.action_recent:
                    frags[3].getView().setVisibility(View.VISIBLE);

                    break;
            }//switch

            return true;
        }

    };


}
