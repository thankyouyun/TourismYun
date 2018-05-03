package com.goodyun.tourismyun;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;


public class MainPage1Frag extends Fragment {

    AutoScrollViewPager viewPager;
    MainPage1FragBestViewAdapter bestViewAdapter;

    TextView tvcount;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page1_frag, container, false);
        tvcount = view.findViewById(R.id.best_tv_count);

        viewPager = (AutoScrollViewPager) view.findViewById(R.id.auto_view);


        final ArrayList<String> items = new ArrayList<>();
        items.add("승윤");
        items.add("승윤2");
        items.add("승윤3");
        items.add("승윤4");
        items.add("승윤5");


        bestViewAdapter = new MainPage1FragBestViewAdapter(getActivity().getApplicationContext(), items);
        viewPager.setAdapter(bestViewAdapter);
        viewPager.setInterval(3000);
        viewPager.startAutoScroll();

        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {

                tvcount.setText((viewPager.getCurrentItem() + 1) + " / " + items.size());

            }
        });



        Toast.makeText(getActivity(), "main page1 frag oncreateview", Toast.LENGTH_SHORT).show();

        return view;
    }//onCreate

    @Override
    public void onResume() {
        super.onResume();


    }//ontresum


}
