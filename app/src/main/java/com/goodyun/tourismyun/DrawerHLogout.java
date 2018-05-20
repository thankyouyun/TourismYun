package com.goodyun.tourismyun;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

public class DrawerHLogout extends Fragment {
    LinearLayout logOff;
    TextView logOn, email, name;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drawer_logout_frag, container, false);


        logOff = view.findViewById(R.id.tv_login_on);
        email = view.findViewById(R.id.tv_login_on_email);
        name = view.findViewById(R.id.tv_login_on_name);
        SharedPreferences preferences = getActivity().getSharedPreferences("LoginData", MODE_PRIVATE);

        email.setText(preferences.getString("email", "null"));
        name.setText(preferences.getString("name", "null"));


        return view;
    }//onCreate
}
