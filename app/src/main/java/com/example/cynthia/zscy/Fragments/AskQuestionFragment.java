package com.example.cynthia.zscy.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import com.example.cynthia.zscy.Activitys.AskQuestionActivity;
import com.example.cynthia.zscy.Adapter.PaperAdapter;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.Utils.Application;

import java.util.ArrayList;
import java.util.List;

public class AskQuestionFragment extends Fragment implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {
    private TabLayout tab;
    private ViewPager viewpager;
    private PaperAdapter adapter;
    private FloatingActionButton floatingActionButton;
    private String kind;



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

        floatingActionButton.setOnClickListener(this);
    }

    private void showPopupWindow(){
        View contentView = getActivity().getLayoutInflater().from(Application.getContext()).inflate(R.layout.popup_choose_type, null);
        PopupWindow mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        View rootView = getActivity().getLayoutInflater().from(Application.getContext()).inflate(R.layout.fragment_ask_question, null);
        mPopWindow.setTouchable(true);
        mPopWindow.setOutsideTouchable(true);
        setBackgroundAlpha(0.4f);
        mPopWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.question_ask:
                showPopupWindow();
                break;
            case R.id.type_next:
                Intent intent = new Intent(Application.getContext(),AskQuestionActivity.class);
                intent.putExtra("kind",kind);
                startActivity(intent);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getCheckedRadioButtonId()){
            case  R.id.type_study:
                kind = "学习";
                break;
            case  R.id.type_emotion:
                kind = "情感";
                break;
            case R.id.type_life:
                kind = "生活";
                break;
            case R.id.type_other:
                kind = "其他";
                break;
        }
    }

    private void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(lp);
    }
}
