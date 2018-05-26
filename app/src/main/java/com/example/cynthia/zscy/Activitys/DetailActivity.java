package com.example.cynthia.zscy.Activitys;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cynthia.httphelper.HttpHelper;
import com.example.cynthia.httphelper.Response.Callback;
import com.example.cynthia.httphelper.Response.Response;
import com.example.cynthia.imageload.ConfigSetting;
import com.example.cynthia.imageload.ImageLoad;
import com.example.cynthia.zscy.Adapter.AnswerAdapter;
import com.example.cynthia.zscy.Bean.Answer;
import com.example.cynthia.zscy.Bean.QuestionDetail;
import com.example.cynthia.zscy.Config.Config;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.Utils.Application;
import com.example.cynthia.zscy.Utils.JsonUtils;
import com.example.cynthia.zscy.Utils.ToastUtils;
import com.example.cynthia.zscy.ViewHolder.BaseViewHolder;
import com.example.cynthia.zscy.widget.CircleView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends BaseActivity {

    private TextView title;
    private TextView context;
    private ImageView picture;
    private CircleView avatar;
    private TextView id;
    private TextView date;
    private TextView price;
    private TextView answerNum;
    private TextView sort;
    private RecyclerView mAnswerView;
    private QuestionDetail questionDetail;
    private ImageView sex;

    private int qId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarVisible();
        setContentLayout(R.layout.activity_detial);
        setTitle("求 助 详 情");
        setLeft(getResources().getDrawable(R.drawable.ic_arrows));
        setRight(getResources().getDrawable(R.drawable.ic_answer_more));
        Intent intent = getIntent();
        String param = intent.getStringExtra("param");
        qId = intent.getIntExtra("qId",-1);

        initView();

        HttpHelper helper = new HttpHelper.set().mode("POST").url(Config.GET_DETAIL).param(param).build();
        Response response = new Response.from(helper).get(new Callback() {
            @Override
            public void succeed(String response) {
                JSONObject object = null;
                int status = 0;
                try {
                    object = new JSONObject(response);
                    status = object.getInt("status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if ( status == HttpURLConnection.HTTP_OK){
                    addData(response);
                } else {
                    ToastUtils.showError("没有登录哦~请登录后重试");
                    finish();
                }
            }

            @Override
            public void error(Exception e, int status) {
                ToastUtils.showResponse(e.toString());
            }
        });

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                todo: set a popupwindow to set detail...
            }
        });
    }

    private void addData(String string){
        questionDetail = JsonUtils.jsonQuestionDetail(string);
        title.setText(questionDetail.getTitle());
        context.setText(questionDetail.getDescription());
        id.setText(questionDetail.getQuestioner_nickname());
        ConfigSetting setting = new ConfigSetting.builder(Application.getContext())
                .from(questionDetail.getQuestioner_photo_thumbnail_src()).error(R.drawable.default_avatar).build();
        ImageLoad.show(avatar,setting);
        if (questionDetail.getPhoto_urls() == null){
            picture.setVisibility(View.GONE);
        }

        if (questionDetail.getAnswers() == null){
            List<Answer> answers = new ArrayList<>();
            questionDetail.setAnswers(answers);
        }
        answerNum.setText(questionDetail.getAnswers().size()+"个回答");
        date.setText(questionDetail.getDisappear_at());
        price.setText(questionDetail.getReward()+"积分");
        if (questionDetail.getQuestioner_gender() == null){
            sex.setVisibility(View.GONE);
        } else {
            int res = questionDetail.getQuestioner_gender().equals("男")?R.drawable.ic_answer_man:R.drawable.ic_answer_woman;
            sex.setImageDrawable(getResources().getDrawable(res));
        }

        AnswerAdapter answerAdapter = new AnswerAdapter(questionDetail.getAnswers(),qId);
        mAnswerView.setAdapter(answerAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Application.getContext());
        mAnswerView.setLayoutManager(layoutManager);

    }

    private void initView(){
        title = findViewById(R.id.detail_title);
        context = findViewById(R.id.detail_context);
        id = findViewById(R.id.detail_id);
        picture = findViewById(R.id.detail_picture);
        avatar = findViewById(R.id.detail_avatar);
        answerNum  =findViewById(R.id.detail_answer_num);
        date = findViewById(R.id.detail_time);
        price = findViewById(R.id.detail_price);
        sort  =findViewById(R.id.detail_sort);
        sex = findViewById(R.id.detail_sex);
        mAnswerView = findViewById(R.id.answerRv);
    }
}
