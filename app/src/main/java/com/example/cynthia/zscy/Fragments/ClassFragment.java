package com.example.cynthia.zscy.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cynthia.zscy.Activitys.LogInActivity;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.Utils.Application;
import com.example.cynthia.zscy.Utils.ToastUtils;

public class ClassFragment extends Fragment implements View.OnClickListener {

    private ImageView arrows;
    private ImageView addTranslation;
    private ImageView picture;
    private TextView notice;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, container, false);
        loadData();
        initView(view);
        return view;

    }

    private void initView(View view){
        arrows = view.findViewById(R.id.class_look_more);
        addTranslation = view.findViewById(R.id.class_right);
        picture = view.findViewById(R.id.class_picture);
        notice = view.findViewById(R.id.class_notice);

        if (Application.getAc() != null){
            picture.setImageDrawable(getResources().getDrawable(R.drawable.bg_empty_course));
            notice.setText("没课的日子总是对它充满了想念~");
            notice.setTextColor(Color.parseColor("#999999"));
            notice.setOnClickListener(null);
        } else {
            picture.setImageDrawable(getResources().getDrawable(R.drawable.no_course));
            notice.setText("快登陆拯救课表君！");
            notice.setTextColor(getResources().getColor(R.color.titleBlue));
            notice.setOnClickListener(this);
        }

        arrows.setOnClickListener(this);
        addTranslation.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.class_look_more:
                ToastUtils.showResponse("该部分功能暂未开发，尽请期待~");
                break;
            case R.id.class_right:
                ToastUtils.showResponse("该部分功能暂未开发，尽请期待~");
                break;
            case R.id.class_notice:
                Intent intent = new Intent(Application.getContext(), LogInActivity.class);
                startActivity(intent);
        }
    }

    private void loadData() {
        SharedPreferences sp = Application.getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String name = sp.getString("ac", null);
        final String password = sp.getString("pw", null);
        if (name != null && password != null){
            Application.setAc(name);
            Application.setPw(password);
        }
    }
}
