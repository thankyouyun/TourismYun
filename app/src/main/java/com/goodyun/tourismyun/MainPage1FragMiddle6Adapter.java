package com.goodyun.tourismyun;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainPage1FragMiddle6Adapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<MainPage1FragMiddlesItem> items;
    String typeId;

    public MainPage1FragMiddle6Adapter(Context context, ArrayList<MainPage1FragMiddlesItem> items,String typeId) {
        this.context = context;
        this.items = items;
        this.typeId = typeId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.main_page1_frag_middle6_recycler_item,parent,false);

        VH holder = new VH(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;

        MainPage1FragMiddlesItem item = items.get(position);

        vh.tvTitle.setText(item.getTitle());
        vh.tvAddr.setText(item.getAddr());
        vh.tvTel.setText(item.getTel());
        Glide.with(context).load(item.img).into(vh.iv);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    class VH extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tvTitle,tvAddr,tvTel;


        public VH(final View itemView) {
            super(itemView);
            iv =itemView.findViewById(R.id.m6_iv);
            tvTitle = itemView.findViewById(R.id.m6_recycler_tv_title);
            tvAddr = itemView.findViewById(R.id.m6_recycler_tv_addr);
            tvTel = itemView.findViewById(R.id.m6_recycler_tv_tel);

            tvTel.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+tvTel.getText().toString()));
                    context.startActivity(intent);

                    return false;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = items.get(getLayoutPosition()).getId();
                    Intent intent = new Intent(context,MainPage1FragMiddle6ItemViewActivity.class);
                    intent.putExtra("Id",id);
                    intent.putExtra("TypeId",typeId);
                    intent.putExtra("Img",items.get(getLayoutPosition()).getImg());
                    context.startActivity(intent);

                }
            });

        }
    }

}
