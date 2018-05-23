package com.goodyun.tourismyun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class RecentFrag extends Fragment {

    ImageView ivSearch;
    RecyclerView recyclerView;
    RecentFragAdapter adapter;
    ArrayList<RecentItem> items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recent_frag,container,false);
        ivSearch = view.findViewById(R.id.clcick_search);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),SearchActivity.class));
                getActivity().overridePendingTransition(R.anim.appear_search, R.anim.appear_search);
            }
        });

        recyclerView = view.findViewById(R.id.recycler);
        adapter = new RecentFragAdapter(getActivity().getApplicationContext(),items);
        recyclerView.setAdapter(adapter);




        return view;
    }







}
