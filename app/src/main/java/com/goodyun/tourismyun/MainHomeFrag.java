package com.goodyun.tourismyun;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class MainHomeFrag extends Fragment {

    DrawerLayout drawerLayout;

    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;
    TabLayout tabLayout;
    Toolbar toolbar;
    ViewPager pager;
    MainPage1Adapter adapter;

    ImageView iv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_home_frag,container,false);

        iv = view.findViewById(R.id.iv_logo);

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        drawerLayout = view.findViewById(R.id.layout_drawer);

        navigationView = view.findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);

        drawerToggle = new ActionBarDrawerToggle(((AppCompatActivity)getActivity()), drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        

        drawerToggle.syncState();


        tabLayout = view.findViewById(R.id.layout_tab);

        pager = view.findViewById(R.id.pager);
        adapter = new MainPage1Adapter(((AppCompatActivity)getActivity()).getSupportFragmentManager());
        adapter.getItem(0);
        pager.setAdapter(adapter);


        tabLayout.setupWithViewPager(pager);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pager.setCurrentItem(0);
            }
        });
        Log.i("TAG", "main home frag oncraete view");


        return view;
    }//onCreate

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();

        Log.i("TAG", "main home frag resume");
    }

    //네이게이션뷰리스너
    NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            AlertDialog.Builder builder = new AlertDialog.Builder(((AppCompatActivity)getActivity()));
            switch (item.getItemId()) {
                case R.id.menu_no1:

                    builder.setTitle("Link Connect");
                    builder.setIcon(android.R.drawable.ic_dialog_alert);
                    builder.setMessage("VISIT SEOUL 서울가이드북 보기편한 링크를 연결하시겠습니까??");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://korean.visitseoul.net/map-guide-book")));

                        }
                    });


                    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            drawerLayout.closeDrawer(navigationView);
                        }
                    });
                    AlertDialog webdialog = builder.create();
                    webdialog.setCanceledOnTouchOutside(false);
                    webdialog.show();

                    break;

                case R.id.menu_no2:

                    builder.setTitle("Phone Call");
                    builder.setIcon(android.R.drawable.ic_dialog_alert);
                    builder.setMessage("관광통역안내소에 친절한 무료전화를 연결하시겠습니까??");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:1330"));
                            startActivity(intent);


                        }
                    });

                    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            drawerLayout.closeDrawer(navigationView);
                        }
                    });
                    AlertDialog calldialog = builder.create();
                    calldialog.setCanceledOnTouchOutside(false);
                    calldialog.show();

                    break;

                case R.id.menu_no3:

                    builder.setTitle("Link Connect");
                    builder.setIcon(android.R.drawable.ic_dialog_alert);
                    builder.setMessage("VISIT SEOUL 여행필수정보 링크를 연결하시겠습니까??");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://korean.visitseoul.net/essential-info#")));

                        }
                    });

                    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            drawerLayout.closeDrawer(navigationView);
                        }
                    });
                    AlertDialog needdialog = builder.create();
                    needdialog.setCanceledOnTouchOutside(false);
                    needdialog.show();
                    break;

                case R.id.menu_no4:
                    Toast.makeText(((AppCompatActivity)getActivity()), "환경설정", Toast.LENGTH_SHORT).show();

                    break;
                case R.id.menu_no5:

                    builder.setTitle("EXIT");
                    builder.setIcon(android.R.drawable.ic_dialog_alert);
                    builder.setMessage("Do you wanna Quit??");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ((AppCompatActivity)getActivity()).finish();
                        }
                    });

                    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            drawerLayout.closeDrawer(navigationView);
                        }
                    });


                    AlertDialog dialog = builder.create();
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();

                    break;
            }
            drawerLayout.closeDrawer(navigationView);


            return false;
        }

    };//navigation
}
