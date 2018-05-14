package com.goodyun.tourismyun;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Build;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainPage1FragBestItemViewAdapter extends BaseAdapter {


    ArrayList<MainPage1FragBestItemView> items;
    MainPage1FragBestItemView item;
    LayoutInflater inflater;

    Context context;

    public MainPage1FragBestItemViewAdapter(Context context, ArrayList<MainPage1FragBestItemView> items, LayoutInflater inflater) {
        this.items = items;
        this.inflater = inflater;
        this.context = context;

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {


        ImageView ivShot;

        float degree;

        if (position % 2 == 0) {
            view = inflater.inflate(R.layout.main_page1_frag_best_item_list_view, viewGroup, false);
            degree = Float.parseFloat(225 + "");
            ivShot = view.findViewById(R.id.best_item_shot1);
            ivShot.setRotation(degree);
        } else {
            view = inflater.inflate(R.layout.main_page1_frag_best_item_list_view2, viewGroup, false);
            degree = Float.parseFloat(315 + "");
            ivShot = view.findViewById(R.id.best_item_shot2);
            ivShot.setRotation(degree);

        }


        item = items.get(position);

        final CircleImageView iv = view.findViewById(R.id.best_item1_view_img);
        TextView tvTitle = view.findViewById(R.id.best_item1_view_title);
        TextView tvOver = view.findViewById(R.id.best_item1_view_over);
        TextView tvMap = view.findViewById(R.id.best_item1_view_map);




        if (item.getImg() == null) {

            Glide.with(view).load(R.drawable.korea).into(iv);

        } else {

            Glide.with(view).load(item.getImg()).into(iv);
        }
        tvTitle.setText(item.getTitle());

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent= new Intent(context, ImageVIewZoomActivity.class);
                intent.putExtra("Img", items.get(position).getImg());
                intent.putExtra("Title", items.get(position).getTitle());


                //전환 효과
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation((Activity)context, new Pair<View, String>(iv, "IMG"));
                    context.startActivity(intent, options.toBundle());
                }else{
                    context.startActivity(intent);
                }

            }
        });



        return view;
    }


}
