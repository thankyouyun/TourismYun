package com.goodyun.tourismyun;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainPage2FragAdapter extends BaseAdapter {

    ArrayList<MainPage2Frag2DBItems> items;
    LayoutInflater inflater;
    TextView title,tourdate,text,crdate,place,name,no;

    public MainPage2FragAdapter(ArrayList<MainPage2Frag2DBItems> members, LayoutInflater inflater) {
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
        if (view == null) {
            view = inflater.inflate(R.layout.main_page2_listview, viewGroup,false);
        }
        MainPage2Frag2DBItems item = items.get(position);

        tourdate = view.findViewById(R.id.frag2_tour_date);
        crdate = view.findViewById(R.id.frag2_create_date);
        place =view.findViewById(R.id.frag2_place);
        text = view.findViewById(R.id.frag2_text);
        name = view.findViewById(R.id.frag2_name);
        no = view.findViewById(R.id.frag2_no);

        no.setText(item.getNo());
        tourdate.setText(item.getTourdate());
        crdate.setText(item.getCrdate());
        place.setText(item.getPlace());
        text.setText(item.getText());
        name.setText(item.getName());



        return view;
    }
}
