package com.goodyun.tourismyun;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import java.util.ArrayList;

public class MainPage1FragMiddleFragment extends Fragment{

    Button btn1,btn2,btn3,btn4,btn5,btn6;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_page1_frag_middle_fragment,container,false);

        btn1 = view.findViewById(R.id.no1_btn);
        btn2 = view.findViewById(R.id.no2_btn);
        btn3 = view.findViewById(R.id.no3_btn);
        btn4 = view.findViewById(R.id.no4_btn);
        btn5 = view.findViewById(R.id.no5_btn);
        btn6 = view.findViewById(R.id.no6_btn);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MainPage1FragMiddle1.class));

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MainPage1FragMiddle2.class));
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MainPage1FragMiddle3.class));
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MainPage1FragMiddle4.class));
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MainPage1FragMiddle5.class));
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MainPage1FragMiddle6.class));
            }
        });




        return view;

    }




}
