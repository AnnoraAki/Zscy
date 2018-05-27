package com.example.cynthia.zscy.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cynthia.zscy.Activitys.AskQuestionActivity;
import com.example.cynthia.zscy.Adapter.PaperAdapter;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.Utils.Application;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class AskQuestionFragment extends Fragment implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {
    private TabLayout tab;
    private ViewPager viewpager;
    private PaperAdapter adapter;
    private FloatingActionButton floatingActionButton;
    private TextView next;
    private ImageView cancel;
    private AlertDialog d;
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
        setDialog();

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
        next.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.question_ask:
                d.show();
                break;
            case R.id.type_next:
                Intent intent = new Intent(Application.getContext(),AskQuestionActivity.class);
                intent.putExtra("kind",kind);
                startActivity(intent);
                break;
            case R.id.type_cancel:
                d.dismiss();
                break;
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

    private void setDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =
                (LayoutInflater)getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout =inflater.inflate(R.layout.popup_choose_type,null);
        builder.setView(layout);
        d = builder.create();
        Window dialogWindow = d.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.height =WindowManager.LayoutParams.WRAP_CONTENT;
        p.width =WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(p);

        next = layout.findViewById(R.id.type_next);
        cancel = layout.findViewById(R.id.type_cancel);



    }
}
