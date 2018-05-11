package com.goodyun.tourismyun;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainPage1FragMiddle3Adapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<MainPage3FragItem> items;


    public MainPage1FragMiddle3Adapter(Context context, ArrayList<MainPage3FragItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.main_page1_frag_middle3_recycler_item,parent,false);

        VH holder = new VH(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;

        MainPage3FragItem item = items.get(position);

        vh.tvTitle.setText(item.getTitle());
        vh.tvAddr.setText(item.getAddr());

        Glide.with(context).load(item.img).into(vh.iv);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    class VH extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tvTitle,tvAddr;


        public VH(View itemView) {
            super(itemView);
            iv =itemView.findViewById(R.id.m3_iv);
            tvTitle = itemView.findViewById(R.id.m3_recycler_tv_title);
            tvAddr = itemView.findViewById(R.id.m3_recycler_tv_addr);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = items.get(getLayoutPosition()).getId();
                    Intent intent = new Intent(context,MainPage1FragMiddle4ItemViewActivity.class);
                    intent.putExtra("Id",id);
                    context.startActivity(intent);

                }
            });
        }
    }

}
