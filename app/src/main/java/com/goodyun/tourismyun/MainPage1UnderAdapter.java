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
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPage1UnderAdapter extends BaseAdapter {



    ArrayList<MainPage1FragMiddlesItem> items;
    LayoutInflater inflater;
    Context context;
    public MainPage1UnderAdapter(Context context,ArrayList<MainPage1FragMiddlesItem> items, LayoutInflater inflater) {
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


        view = inflater.inflate(R.layout.main_page1_frag_under_listview,viewGroup,false);

        MainPage1FragMiddlesItem item = items.get(position);

        ImageView iv = view.findViewById(R.id.under_img);
        TextView tvTitle = view.findViewById(R.id.under_tv_title);
        TextView tvAddr = view.findViewById(R.id.under_tv_addr);

        Glide.with(view).load(item.getImg()).into(iv);
        tvTitle.setText(item.getTitle());

        final Geocoder geocoder = new Geocoder(context);
        List<Address> addresses=null;

        double x = Double.parseDouble(item.mapX.toString());
        double y = Double.parseDouble(item.mapY.toString());

        try {
            addresses = geocoder.getFromLocation(y,x,1);
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(addresses!=null) {

            if(addresses.size()==0){
                tvAddr.setText("해당주소가 없습니다..");

            }else{
                tvAddr.setText(addresses.get(0).getAddressLine(0).toString());
            }


        }else{

        }

        return view;
    }




}
