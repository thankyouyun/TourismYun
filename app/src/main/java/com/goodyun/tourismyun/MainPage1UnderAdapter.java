package com.goodyun.tourismyun;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.INotificationSideChannel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainPage1UnderAdapter extends BaseAdapter {


    ArrayList<MainPage1FragMiddlesItem> items;
    LayoutInflater inflater;
    Context context;
    String addr;
    public MainPage1UnderAdapter(Context context, ArrayList<MainPage1FragMiddlesItem> items, LayoutInflater inflater) {
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


        view = inflater.inflate(R.layout.main_page1_frag_under_listview, viewGroup, false);

        MainPage1FragMiddlesItem item = items.get(position);

        ImageView iv = view.findViewById(R.id.under_img);
        TextView tvTitle = view.findViewById(R.id.under_tv_title);
        TextView tvAddr = view.findViewById(R.id.under_tv_addr);

        Glide.with(view).load(item.getImg()).into(iv);
        tvTitle.setText(item.getTitle());

        final Geocoder geocoder = new Geocoder(context, Locale.KOREA);
        List<Address> addresses = null;

        double x = Double.parseDouble(item.mapX.toString());
        double y = Double.parseDouble(item.mapY.toString());

        try {

            addresses = geocoder.getFromLocation(y, x, 1);
            if (addresses != null) {

                addr = addresses.get(0).getAddressLine(0).toString();

                if(addr.contains("대한민국")){
                    addr = addr.replace("대한민국","");
                }
                if(addr.contains("서울특별시")){
                    addr =addr.replace("서울특별시","");
                }


                if (addresses.size() == 0) {
                    tvAddr.setText("해당주소가 없습니다..");

                } else {
                    tvAddr.setText(addr);
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        }




        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(context,MainPage1FragBestItemViewActivity.class);
                intent.putExtra("Id",items.get(position).getId());
                intent.putExtra("Img",items.get(position).getImg());
                intent.putExtra("Title",items.get(position).getTitle());
                intent.putExtra("Addr",addr);
                intent.putExtra("MapX",items.get(position).getMapX());
                intent.putExtra("MapY",items.get(position).getMapY());
                context.startActivity(intent);
            }
        });

        return view;
    }


}
