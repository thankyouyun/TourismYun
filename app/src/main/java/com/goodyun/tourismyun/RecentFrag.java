package com.goodyun.tourismyun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class RecentFrag extends Fragment {

    ImageView ivSearch;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recent_frag,container,false);
        ivSearch = view.findViewById(R.id.clcick_search);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),SearchActivity.class));
                getActivity().overridePendingTransition(R.anim.appear_search, R.anim.appear_search);
            }
        });

        return view;
    }



}
