package com.goodyun.tourismyun;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchViewFragAdapter extends BaseAdapter {


    ArrayList<SearchReadItem> items;
    LayoutInflater inflater;
    Context context;

    public SearchViewFragAdapter(ArrayList<SearchReadItem> items, LayoutInflater inflater, Context context) {
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.main_page1_frag_middle1_recycler_item, viewGroup, false);
        SearchReadItem item = items.get(position);

        ImageView iv = view.findViewById(R.id.m1_iv);
        TextView tvTitle = view.findViewById(R.id.m1_recycler_tv_title);
        TextView tvAddr = view.findViewById(R.id.m1_recycler_tv_addr);

        Geocoder geocoder = new Geocoder(context, Locale.KOREA);
        double x, y;
        List<Address> addresses = null;
        x = Double.parseDouble(item.getMapX());
        y = Double.parseDouble(item.getMapY());
        try {
            addresses = geocoder.getFromLocation(y, x, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String addr = addresses.get(0).getAddressLine(0).toString();
        if (item.getImg() == null) {
            Glide.with(view).load(R.drawable.korea).into(iv);
        } else {
            Glide.with(view).load(item.getImg()).into(iv);
        }
        tvTitle.setText(item.getTitle());
        if(addr.contains("대한민국")){
            addr = addr.replace("대한민국","");
        }

        if(addr.contains("서울특별시")){
            addr = addr.replace("서울특별시","");
        }
        tvAddr.setText(addr);


        return view;
    }
}
