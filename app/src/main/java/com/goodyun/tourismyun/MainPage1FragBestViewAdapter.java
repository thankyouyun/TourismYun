package com.goodyun.tourismyun;

import android.content.Context;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;


import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MainPage1FragBestViewAdapter extends PagerAdapter {


    Context context;
    ArrayList<MainPage1FragMiddlesItem> items;
    TextView tvTitle;
    ImageView bestBack;

    public MainPage1FragBestViewAdapter(Context context, ArrayList<MainPage1FragMiddlesItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {

        //뷰페이지 슬라이딩 할 레이아웃 인플레이션
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.main_page1_frag_best_viewpager, container, false);


        tvTitle = v.findViewById(R.id.tv_best_title);
        bestBack = v.findViewById(R.id.best_back_img);

        final MainPage1FragMiddlesItem item;

        item = items.get(position);
        tvTitle.setText(item.getTitle());
        if (item.getImg() == null) {

            bestBack.setVisibility(View.GONE);

        } else {
            bestBack.setVisibility(View.VISIBLE);
            Glide.with(v).load(item.getImg()).into(bestBack);
        }

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MainPage1FragBestItemViewActivity.class);
                intent.putExtra("Id",item.getId());
                intent.putExtra("Title",item.getTitle());
                intent.putExtra("Img",item.getImg());
                intent.putExtra("MapX",item.getMapX());
                intent.putExtra("MapY",item.getMapY());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });


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
        return view == object;
    }





}
