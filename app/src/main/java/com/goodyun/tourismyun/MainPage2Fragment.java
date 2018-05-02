package com.goodyun.tourismyun;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

import java.lang.reflect.Member;
import java.util.ArrayList;

public class MainPage2Fragment extends Fragment {
    ArrayList<Items> members= new ArrayList<>();
    ListView listView;
    MainPage2FragAdapter adapter;
    ScrollView scrollView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page2_fragment,container,false);



        members.add(new Items(R.drawable.korea,"김승윤","서울 성북구"));
        members.add(new Items(R.drawable.korea,"김승윤2","서울 도봉구"));
        members.add(new Items(R.drawable.korea,"김승윤3","서울 노원구"));
        members.add(new Items(R.drawable.korea,"김승윤4","서울 중구"));
        members.add(new Items(R.drawable.korea,"김승윤5","서울 강남구"));
        members.add(new Items(R.drawable.korea,"김승윤6","서울 강동구"));
        members.add(new Items(R.drawable.korea,"김승윤7","서울 강서구"));
        members.add(new Items(R.drawable.korea,"김승윤8","서울 동대문구"));
        members.add(new Items(R.drawable.korea,"김승윤9","서울 성동구"));
        members.add(new Items(R.drawable.korea,"김승윤0","서울 강북구"));



        listView= view.findViewById(R.id.list_view);

//        listView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                scrollView.requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });
        adapter= new MainPage2FragAdapter(members, getLayoutInflater() );
        listView.setAdapter(adapter);
        return view;
    }//onCreate




}
