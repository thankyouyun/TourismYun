package com.goodyun.tourismyun;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MainPage1FragMiddleItemViewAdapter extends PagerAdapter {


    Context context;
    ArrayList<String> items;
    TextView tvTitle;
    ImageView bestBack;
    RelativeLayout autoRe;

    public MainPage1FragMiddleItemViewAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        //뷰페이지 슬라이딩 할 레이아웃 인플레이션
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.main_page1_frag_best_viewpager, container, false);

        autoRe = v.findViewById(R.id.auto_pager_relative);
        tvTitle = v.findViewById(R.id.tv_best_title);
        bestBack = v.findViewById(R.id.best_back_img);

        autoRe.setBackgroundColor(context.getResources().getColor(R.color.white));


        tvTitle.setVisibility(View.GONE);


        Glide.with(context).load(items.get(position)).into(bestBack);


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
