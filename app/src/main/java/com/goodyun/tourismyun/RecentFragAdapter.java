package com.goodyun.tourismyun;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecentFragAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<RecentItem> items;
    Intent intent;

    public RecentFragAdapter(Context context, ArrayList<RecentItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recent_frag_adapter_item, parent, false);

        VH holder = new VH(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;

        RecentItem item = items.get(position);

        Glide.with(context).load(item.getImg()).into(vh.iv);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class VH extends RecyclerView.ViewHolder {
        ImageView iv;


        public VH(final View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.recent_iv);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    switch (items.get(getLayoutPosition()).getType()) {
                        case 1:
                            intent = new Intent(context, MainPage1FragMiddle1ItemViewActivity.class);
                            break;
                        case 2:
                            intent = new Intent(context, MainPage1FragMiddle2ItemViewActivity.class);
                            break;
                        case 3:
                            intent = new Intent(context, MainPage1FragMiddle3ItemViewActivity.class);
                            break;
                        case 4:
                            intent = new Intent(context, MainPage1FragMiddle4ItemViewActivity.class);
                            break;
                        case 5:
                            intent = new Intent(context, MainPage1FragMiddle5ItemViewActivity.class);
                            break;
                        case 6:
                            intent = new Intent(context, MainPage1FragMiddle6ItemViewActivity.class);
                            break;
                        case 25:
                            intent = new Intent(context, MainPage1FragBestItemViewActivity.class);
                            break;
                        case 26:
                            intent = new Intent(context, MainPage3FragItemViewActivity.class);
                            break;
                    }


                    intent.putExtra("Id", items.get(getLayoutPosition()).getId());
                    context.startActivity(intent);


                }
            });


        }
    }

}
