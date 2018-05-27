package com.example.cynthia.zscy.Activitys;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.cynthia.httphelper.HttpHelper;
import com.example.cynthia.httphelper.Response.Callback;
import com.example.cynthia.httphelper.Response.Response;
import com.example.cynthia.zscy.Config.Config;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.Utils.Application;

public class CommentActivity extends BaseActivity {

    private int aId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_detial_contaier);
        Intent intent = getIntent();
        aId = intent.getIntExtra("aId",-1);
        String param = "stuNum="+ Application.getAc()+"&idNum="+Application.getPw()+"&answer_id=" + aId;


        HttpHelper helper = new HttpHelper.set().mode("POST").url(Config.GET_REMARK_LIST)
                .param(param).build();
        new Response.from(helper).get(new Callback() {
            @Override
            public void succeed(String response) {

            }

            @Override
            public void error(Exception e, int status) {

            }
        });
//        todo: to show the details of one answer and show the comments of it
    }

    public void addContentLayout(@LayoutRes int layoutId, RelativeLayout root, int height) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layoutId, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                height);
        root.addView(contentView, params);
    }
}
