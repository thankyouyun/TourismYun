package com.goodyun.tourismyun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import org.w3c.dom.Text;

public class MyPageFrag extends Fragment {

    ImageView ivSearch;
    RelativeLayout map,recentGo,answer,setup;
    TextView tvLogIn,tvInter;

    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_page_frag,container,false);
        this.view = view;
        ivSearch =view.findViewById(R.id.clcick_search);
        map = view.findViewById(R.id.tv_my_map);
        recentGo = view.findViewById(R.id.tv_my_recent);
        answer =view.findViewById(R.id.tv_my_answer);
        setup = view.findViewById(R.id.tv_my_setup);
        tvLogIn = view.findViewById(R.id.tv_login);
        tvInter=view.findViewById(R.id.tv_inter);

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(((AppCompatActivity)getActivity()),SearchActivity.class));
                getActivity().overridePendingTransition(R.anim.appear_search, R.anim.appear_search);

            }
        });

        tvLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),LogInActivity.class));
            }
        });

        return view;
    }


}
