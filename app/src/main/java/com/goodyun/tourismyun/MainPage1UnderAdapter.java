package com.goodyun.tourismyun;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainPage1UnderAdapter extends BaseAdapter {



    ArrayList<MainPage1FragMiddlesItem> items;
    LayoutInflater inflater;

    public MainPage1UnderAdapter(ArrayList<MainPage1FragMiddlesItem> items, LayoutInflater inflater) {
        this.items = items;
        this.inflater = inflater;
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
    public View getView(int position, View view, ViewGroup viewGroup) {


        view = inflater.inflate(R.layout.main_page1_frag_under_listview,viewGroup,false);

        MainPage1FragMiddlesItem item = items.get(position);

        ImageView iv = view.findViewById(R.id.under_img);
        TextView tvTitle = view.findViewById(R.id.under_tv_title);
        TextView tvSubTitle = view.findViewById(R.id.under_tv_subtitle);
        TextView tvAddr = view.findViewById(R.id.under_tv_addr);

        Glide.with(view).load(item.getImg()).into(iv);
        tvTitle.setText(item.getTitle());
        tvSubTitle.setText(item.id);
        tvAddr.setText(item.mapX+" / "+item.mapY);


        return view;
    }



    public void refreshAdapter(ArrayList<MainPage1FragMiddlesItem> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();

    }

}
