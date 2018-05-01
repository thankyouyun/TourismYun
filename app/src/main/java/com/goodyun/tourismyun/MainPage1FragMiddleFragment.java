package com.goodyun.tourismyun;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.QuickContactBadge;

import java.util.ArrayList;

public class MainPage1FragMiddleFragment extends Fragment{
    GridView gridView;
    ArrayAdapter adapter;

    ArrayList<String> datas= new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_page1_frag_middle_fragment,container,false);

        datas.add("관광지");
        datas.add("음식점");
        datas.add("쇼핑");
        datas.add("숙박");
        datas.add("공연/행사");
        datas.add("축제");


        gridView= view.findViewById(R.id.gridview);

        adapter= new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, datas);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {



            }
        });

        return view;

    }


}
