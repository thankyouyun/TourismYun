package com.goodyun.tourismyun;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;


public class MainPage1Frag extends Fragment {

    AutoScrollViewPager viewPager;
    MainPage1FragBestViewAdapter bestViewAdapter;

    BottomNavigationView bottomNavigationView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page1_frag,container,false);





        ArrayList<String> datas =new ArrayList<>();
        datas.add("승윤");
        datas.add("승윤2");
        datas.add("승윤3");
        datas.add("승윤4");

        viewPager = (AutoScrollViewPager) view.findViewById(R.id.auto_view);
        bestViewAdapter = new MainPage1FragBestViewAdapter(getActivity().getApplicationContext(),datas);
        viewPager.setAdapter(bestViewAdapter);
        viewPager.setInterval(3000);
        viewPager.startAutoScroll();




//        //리사이클러뷰
//        items.add(new String("aaa"));
//        items.add(new String("bbb"));
//        items.add(new String("ccc"));
//        items.add(new String("ddd"));
//        items.add(new String("eee"));
//
//
//        recyclerView= view.findViewById(R.id.recycler);
//        newAdapter= new MainPage1FragNewAdapter(getActivity(), items);
//        recyclerView.setAdapter(newAdapter);
//
//



        return view;
    }//onCreate



}
