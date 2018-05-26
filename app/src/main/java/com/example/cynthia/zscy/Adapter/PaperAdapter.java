package com.example.cynthia.zscy.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cynthia.zscy.Fragments.ListFragment;

import java.util.List;

public class PaperAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;


    public PaperAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ListFragment.title[position];

    }

}