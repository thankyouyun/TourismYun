package com.goodyun.tourismyun;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainPage2FragAdapter extends BaseAdapter {

    ArrayList<Items> items;
    LayoutInflater inflater;

    public MainPage2FragAdapter(ArrayList<Items> members, LayoutInflater inflater) {
        this.items = members;
        this.inflater = inflater;
    }



    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if( view == null ){
            view = inflater.inflate(R.layout.main_page2_listview, null);
        }
        Items item= items.get(position);

        ImageView iv= view.findViewById(R.id.item_img);
        TextView tvName= view.findViewById(R.id.item_tv_name);
        TextView tvNation= view.findViewById(R.id.item_tv_nation);

        iv.setImageResource(item.imgId);
        tvName.setText(item.name);
        tvNation.setText(item.nation);

        return view;
    }
}
