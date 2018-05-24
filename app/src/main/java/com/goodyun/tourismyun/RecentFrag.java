package com.goodyun.tourismyun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class RecentFrag extends Fragment {

    LoadSQLlite loadSQLlite;
    ImageView ivSearch;
    CardView cvA, cvS;
    RecyclerView recyclerView;
    RecentFragAdapter adapter;
    ArrayList<RecentItem> items;
    String id;
    SwipeRefreshLayout refreshLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recent_frag, container, false);
        ivSearch = view.findViewById(R.id.clcick_search);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
                getActivity().overridePendingTransition(R.anim.appear_search, R.anim.appear_search);
            }
        });
        loadSQLlite = new LoadSQLlite(getActivity(), "test.db", null, 1);
        items = loadSQLlite.getResult();

        recyclerView = view.findViewById(R.id.recent_recycler);
        adapter = new RecentFragAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);

        refreshLayout = view.findViewById(R.id.recent_refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                items.clear();
                items = loadSQLlite.getResult();
                refreshLayout.setRefreshing(false);

            }
        });

        cvA = view.findViewById(R.id.recent_a_delete);
        cvA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.clear();
                loadSQLlite.delete();
                adapter.notifyDataSetChanged();
            }
        });//cva

        cvS = view.findViewById(R.id.recent_s_delete);
        cvS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = items.size()-1; i >= 0; i--) {
                    if (items.get(i).isSel()) {
                        items.get(i).setSel(false);
                        loadSQLlite.selectedDelete(items.get(i).getId());

                    }//if
                }//for

                items.clear();
                items = loadSQLlite.getResult();
                adapter.notifyDataSetChanged();

            }
        });//cvs


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        items.clear();
        items = loadSQLlite.getResult();
    }
}
