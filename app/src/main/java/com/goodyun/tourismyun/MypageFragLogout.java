package com.goodyun.tourismyun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class MypageFragLogout extends Fragment {

    View view;
    RelativeLayout write,login;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.my_page_frag_logout, container, false);

        write = view.findViewById(R.id.tv_my_write);


        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext().getApplicationContext(), "준비중입니다....", Toast.LENGTH_SHORT).show();
            }
        });







        return view;
    }
}
