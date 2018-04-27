package com.goodyun.tourismyun;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPageAdapter extends FragmentPagerAdapter {

    Fragment[] frags= new Fragment[3];
    String[] title = new String[]{"추천코스","Talk","이달의장소"};


    public MainPageAdapter(FragmentManager fm) {
        super(fm);
        frags[0] = new MainPage1Fragment();
        frags[1] = new MainPage2Fragment();
        frags[2] = new MainPage3Fragment();

    }

    @Override
    public Fragment getItem(int position) {

        return frags[position];
    }

    @Override
    public int getCount() {
        return frags.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return title[position];
    }
}
