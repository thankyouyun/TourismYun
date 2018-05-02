package com.goodyun.tourismyun;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class SearchReadAdapter extends BaseAdapter {


    ArrayList<SearchReadItem> items;
    LayoutInflater inflater;

    public SearchReadAdapter(ArrayList<SearchReadItem> items, LayoutInflater inflater) {
        this.items = items;
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
            //new View
            view = inflater.inflate(R.layout.search_list_item, null);
        }
        SearchReadItem item = items.get(position);
        TextView tvRead = view.findViewById(R.id.tv_read);
        TextView tvDate = view.findViewById(R.id.tv_date);

        tvRead.setText(item.read);
        tvDate.setText(item.date);

        return view;
    }
}
