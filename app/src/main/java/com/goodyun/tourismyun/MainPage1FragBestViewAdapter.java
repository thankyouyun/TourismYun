package com.goodyun.tourismyun;

import android.content.Context;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;


import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MainPage1FragBestViewAdapter extends PagerAdapter {


    Context context;
    ArrayList<String> items;

    public MainPage1FragBestViewAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        //뷰페이지 슬라이딩 할 레이아웃 인플레이션
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.main_page1_frag_best_viewpager, null);
        TextView view = v.findViewById(R.id.tv);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);

    }

    @Override
    public int getCount() {


        return items.size();


    }





    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
