package com.example.cynthia.zscy.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cynthia.zscy.Activitys.AskQuestionActivity;
import com.example.cynthia.zscy.Adapter.PaperAdapter;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.Utils.Application;

import java.util.ArrayList;
import java.util.List;

public class AskQuestionFragment extends Fragment {
    private TabLayout tab;
    private ViewPager viewpager;
    private PaperAdapter adapter;
    private FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ask_question, container, false);
        initView(view);
        return view;

    }

    public void initView(View view){
        tab = (TabLayout)view.findViewById(R.id.question_tab);
        viewpager = (ViewPager)view.findViewById(R.id.mPaper);
        floatingActionButton = view.findViewById(R.id.question_ask);

        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            fragments.add(ListFragment.newInstance(i + 1));
        }
        adapter = new PaperAdapter(getFragmentManager(), fragments);
        //给ViewPager设置适配器
        viewpager.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来。
        tab.setupWithViewPager(viewpager);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Application.getContext(), AskQuestionActivity.class);
                startActivity(intent);
            }
        });
    }
}
