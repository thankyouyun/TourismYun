package com.goodyun.tourismyun;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainPage3FragAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Item2> items;


    public MainPage3FragAdapter(Context context, ArrayList<Item2> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



    class VH extends RecyclerView.ViewHolder{



        public VH(View itemView) {
            super(itemView);


        }
    }

}
