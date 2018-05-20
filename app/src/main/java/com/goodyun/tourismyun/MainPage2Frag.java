package com.goodyun.tourismyun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.lang.reflect.Member;
import java.util.ArrayList;

public class MainPage2Frag extends Fragment {
    ArrayList<Items> members = new ArrayList<>();
    ListView listView;
    MainPage2FragAdapter adapter;
    ScrollView scrollView;
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page2_fragment, container, false);

        fab = view.findViewById(R.id.fab_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IntroActivity.loginOnOff) {
                    Toast.makeText(getActivity(), "글을 쓰시오", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(getActivity(), LogInActivity.class));
                }
            }
        });

        members.add(new Items(R.drawable.korea, "김승윤", "서울 성북구"));
        members.add(new Items(R.drawable.korea, "김승윤2", "서울 도봉구"));
        members.add(new Items(R.drawable.korea, "김승윤3", "서울 노원구"));
        members.add(new Items(R.drawable.korea, "김승윤4", "서울 중구"));
        members.add(new Items(R.drawable.korea, "김승윤5", "서울 강남구"));
        members.add(new Items(R.drawable.korea, "김승윤6", "서울 강동구"));
        members.add(new Items(R.drawable.korea, "김승윤7", "서울 강서구"));
        members.add(new Items(R.drawable.korea, "김승윤8", "서울 동대문구"));
        members.add(new Items(R.drawable.korea, "김승윤9", "서울 성동구"));
        members.add(new Items(R.drawable.korea, "김승윤0", "서울 강북구"));


        listView = view.findViewById(R.id.list_view);


        adapter = new MainPage2FragAdapter(members, getLayoutInflater());
        listView.setAdapter(adapter);

        setListViewHeightBasedOnChildren(listView);


        return view;
    }//onCreate


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }//ls


}
