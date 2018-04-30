package com.goodyun.tourismyun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    ActionBarDrawerToggle drawerToggle;
    TabLayout tabLayout;
    Toolbar toolbar;
    ViewPager pager;
    MainPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.layout_drawer);
        navigationView = findViewById(R.id.nav);
        bottomNavigationView = findViewById(R.id.bottom_nav);


        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);

        drawerToggle.syncState();


        getSupportActionBar().setTitle("하이");
        tabLayout = findViewById(R.id.layout_tab);
        pager = findViewById(R.id.pager);
        adapter = new MainPageAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        tabLayout.setupWithViewPager(pager);

        getSupportActionBar().setTitle("추천코스");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getText().equals("추천코스")){
                    getSupportActionBar().setTitle("추천코스");
                }else if(tab.getText().equals("Talk")){
                    getSupportActionBar().setTitle("Talk");
                }else if(tab.getText().equals("이달의장소")){
                    getSupportActionBar().setTitle("이달의장소");
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }



        });


        //바텀네비게이션뷰
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

    }//onCreate

    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Intent intent;

            switch (item.getItemId()) {
                case R.id.action_home:

                    return true;

                case R.id.action_plus:
                    intent = new Intent(MainActivity.this, RoadActivity.class);
                    startActivity(intent);
                    return true;

                case R.id.action_my:
                    intent = new Intent(MainActivity.this, MyPageActivity.class);
                    startActivity(intent);
                    return true;

                case R.id.action_recent:
                    intent = new Intent(MainActivity.this, RecentActivity.class);
                    startActivity(intent);

                    return true;

            }//switch
            return true;
        }

    };

}
