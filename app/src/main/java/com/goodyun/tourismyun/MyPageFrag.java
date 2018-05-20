package com.goodyun.tourismyun;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.facebook.login.Login;

import org.w3c.dom.Text;

public class MyPageFrag extends Fragment {

    ImageView ivSearch;
    RelativeLayout setUp, recentGo,  bers;
    Fragment[] frags = new Fragment[2];
    FragmentTransaction transaction;

    View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_page_frag, container, false);
        this.view = view;
        ivSearch = view.findViewById(R.id.clcick_search);

        setUp = view.findViewById(R.id.tv_my_setup);
        recentGo = view.findViewById(R.id.tv_my_recent);
        bers = view.findViewById(R.id.tv_my_bers);


        setUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MypageSetupActivity.class));

            }
        });



        bers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(((AppCompatActivity)getActivity()));
                builder.setTitle("BERSION");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setMessage("BERSION : 1.0.1 \nMake in 2018");

                builder.setPositiveButton("OK", null);
                AlertDialog webdialog = builder.create();
                webdialog.setCanceledOnTouchOutside(false);
                webdialog.show();

            }
        });

        recentGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "준비 중입니다.. 감사합니다..", Toast.LENGTH_SHORT).show();
            }
        });


        frags[0] = new MypageFragLogout();
        frags[1] = new MypageFragLogIn();
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.my_change,frags[0]);
        transaction.add(R.id.my_change,frags[1]);
        transaction.commit();


        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(((AppCompatActivity) getActivity()), SearchActivity.class));
                getActivity().overridePendingTransition(R.anim.appear_search, R.anim.appear_search);

            }
        });



        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        frags[0].getView().setVisibility(View.GONE);
        frags[1].getView().setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(IntroActivity.loginOnOff){
            frags[0].getView().setVisibility(View.VISIBLE);
        }else{
            frags[1].getView().setVisibility(View.VISIBLE);
        }

    }
}
