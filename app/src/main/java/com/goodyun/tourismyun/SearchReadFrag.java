package com.goodyun.tourismyun;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SearchReadFrag extends Fragment {


    TextView tvEmpty;
    ListView lv;
    ArrayList<SearchReadItem> items = new ArrayList<>();
    SearchReadAdapter adapter;
    String date, ask;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_read_frag, container, false);

        ask = "한국";
//        ask = getArguments().getString("Ask");


        date = DateFormat.getDateInstance().format(new Date());

        lv = view.findViewById(R.id.list_view);
        adapter = new SearchReadAdapter(items, getLayoutInflater());
        lv.setAdapter(adapter);
        tvEmpty = view.findViewById(R.id.tv_empty_list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getActivity(), position + "번째 리스트를 클릭했습니다", Toast.LENGTH_SHORT).show();

            }
        });
        lv.setEmptyView(tvEmpty);


        return view;
    }//onCreate


}
