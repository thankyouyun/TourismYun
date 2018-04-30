package com.goodyun.tourismyun;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainPage1FragNewAdapter extends RecyclerView.Adapter {



    Context context;
    ArrayList<String> items;

    public MainPage1FragNewAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        this.items = items;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);

        View itemView= inflater.inflate(R.layout.main_page1_new_list_item, parent, false);
        VH holder= new VH(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH)holder;

        String s= items.get(position);
        vh.tv.setText(s);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class VH extends RecyclerView.ViewHolder{

        //멤버변수
        TextView tv;

        public VH(View itemView) {
            super(itemView);
            tv= itemView.findViewById(R.id.tv);
        }
    }
}
