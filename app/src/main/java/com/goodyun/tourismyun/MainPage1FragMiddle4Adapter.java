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

public class MainPage1FragMiddle4Adapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<MainPage1FragMiddlesItem> items;


    public MainPage1FragMiddle4Adapter(Context context, ArrayList<MainPage1FragMiddlesItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.main_page1_frag_middle4_recycler_item,parent,false);

        VH holder = new VH(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;

        MainPage1FragMiddlesItem item = items.get(position);

        vh.tvTitle.setText(item.getTitle()+"  "+item.getAddr().replace("(","").replace(")",""));
        vh.tvTel.setText(item.getTel());

        Glide.with(context).load(item.img).into(vh.iv);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    class VH extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tvTitle, tvTel;


        public VH(final View itemView) {
            super(itemView);
            iv =itemView.findViewById(R.id.m4_iv);
            tvTitle = itemView.findViewById(R.id.m4_recycler_tv_title);
            tvTel = itemView.findViewById(R.id.m4_recycler_tv_addr);

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
                    Intent intent = new Intent(context,MainPage1FragMiddle4ItemViewActivity.class);
                    intent.putExtra("Id",id);
                    intent.putExtra("Img",items.get(getLayoutPosition()).getImg());
                    context.startActivity(intent);

                }
            });

        }
    }

}
