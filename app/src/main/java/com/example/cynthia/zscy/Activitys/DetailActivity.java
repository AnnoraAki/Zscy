package com.example.cynthia.zscy.Activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.example.cynthia.zscy.Utils.TextUtil;
import com.example.cynthia.zscy.Utils.ToastUtils;
import com.example.cynthia.zscy.ViewHolder.BaseViewHolder;
import com.example.cynthia.zscy.widget.CircleView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends BaseActivity implements View.OnClickListener {

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
    private RelativeLayout container1;
    private RelativeLayout container2;
    private ImageView left;
    private ImageView right;
    private TextView leftWord;
    private TextView rightWord;

    private int qId;
    private String kind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarVisible();
        setContentLayout(R.layout.activity_detial_contaier);
        setTitle("求 助 详 情");
        setLeftDrawable(getResources().getDrawable(R.drawable.ic_arrows));
        setRightDrawable(getResources().getDrawable(R.drawable.ic_answer_more));
        Intent intent = getIntent();
        qId = intent.getIntExtra("qId",-1);
        kind = intent.getStringExtra("kind");

        String param = "stuNum="+Application.getAc()+"&idNum="+Application.getPw()+"&question_id="+qId;

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

        sort.setOnClickListener(this);
        left.setOnClickListener(this);
        leftWord.setOnClickListener(this);
        rightWord.setOnClickListener(this);
        right.setOnClickListener(this);
        setLeftDrawableClick(this);
        setRightDrawableClick(this);
    }

    private void addData(String string){
        questionDetail = JsonUtils.jsonQuestionDetail(string);
        title.setText(questionDetail.getTitle());
        String temp = "#"+questionDetail.getTags()+"# "+questionDetail.getDescription();
        context.setText(temp);
        TextUtil.setTVColor(temp,'#','#', Color.parseColor("#7195FA"),context);
        id.setText(questionDetail.getQuestioner_nickname());
        if (questionDetail.getQuestioner_photo_thumbnail_src().equals("") || questionDetail.getQuestioner_photo_thumbnail_src().equals("null")) {
            avatar.setImageResource(R.drawable.default_avatar);
        } else {
            ConfigSetting setting = new ConfigSetting.builder(Application.getContext())
                    .error(R.drawable.default_avatar)
                    .from(questionDetail.getQuestioner_photo_thumbnail_src())
                    .build();
            ImageLoad.show(avatar, setting);
        }
        if (questionDetail.getPhoto_urls() == null||questionDetail.getPhoto_urls().size() == 0){
            picture.setVisibility(View.GONE);
        }
        if (questionDetail.getAnswers() == null){
            answerNum.setText("0个回答");
            List<Answer> answers = new ArrayList<>();
            questionDetail.setAnswers(answers);
        } else {
            answerNum.setText(questionDetail.getAnswers().size()+"个回答");
        }
        date.setText(questionDetail.getDisappear_at());
        price.setText(questionDetail.getReward()+"积分");
        if (questionDetail.getQuestioner_gender() == null){
            sex.setVisibility(View.GONE);
        } else {
            int res = questionDetail.getQuestioner_gender().equals("男")?R.drawable.ic_answer_man:R.drawable.ic_answer_woman;
            sex.setImageDrawable(getResources().getDrawable(res));
        }

        if (questionDetail.getIs_self() == 1){
            left.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_price));
            leftWord.setText("加价");
            right.setImageDrawable(getResources().getDrawable(R.drawable.ic_share_cancel));
            rightWord.setText("取消提问");
        }

        AnswerAdapter answerAdapter = new AnswerAdapter(questionDetail.getAnswers(),qId,kind);
        mAnswerView.setAdapter(answerAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Application.getContext());
        mAnswerView.setLayoutManager(layoutManager);

    }

    private void initView(){
        container1 = findViewById(R.id.detail_container1);
        container2 = findViewById(R.id.detail_container2);

        addContentLayout(R.layout.container_question,container1,ViewGroup.LayoutParams.WRAP_CONTENT);
        addContentLayout(R.layout.container_answer,container2,ViewGroup.LayoutParams.MATCH_PARENT);
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
        left = findViewById(R.id.detail_left_ic);
        leftWord = findViewById(R.id.detail_left_word);
        right = findViewById(R.id.detail_right_ic);
        rightWord = findViewById(R.id.detail_right_word);
    }

    public void addContentLayout(@LayoutRes int layoutId,RelativeLayout root,int height) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layoutId, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                height);
        root.addView(contentView, params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.detail_sort:
//                todo: show a popup window
                break;
            case R.id.detail_left_ic:
            case R.id.detail_left_word:
                if (questionDetail.getIs_self() == 0){
                    ToastUtils.showResponse("忽略该问题 :)");
                } else {
                    ToastUtils.showResponse("听说你想加价?");
                }
                break;
            case R.id.detail_right_ic:
            case R.id.detail_right_word:
                if (questionDetail.getIs_self() == 0){
//                    todo: go to a new activity and answer this question
                    Intent intent = new Intent(DetailActivity.this,AnswerActivity.class);
                    intent.putExtra("qId",qId);
                    startActivityForResult(intent,1);
                } else {
                    String param = "stuNum="+Application.getAc()
                            +"&idNum="+Application.getPw()+"&question_id="+qId;
                    HttpHelper helper = new HttpHelper.set().url(Config.CANCEL_QUESTION)
                            .param(param).mode("POST").build();
                    new Response.from(helper).get(new Callback() {
                        @Override
                        public void succeed(String response) {
                            ToastUtils.showResponse("取消提问成功");
                            finish();
                        }

                        @Override
                        public void error(Exception e, int status) {
                            ToastUtils.showError(e.toString());
                        }
                    });
                }
                break;
            case R.id.bar_left:
                finish();
                break;
            case R.id.bar_right:
//                todo : to show a popupWindow to show the share and report
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            qId = data.getIntExtra("qId",-1);
            String param = "stuNum="+Application.getAc()+"&idNum="+Application.getPw()+"&question_id="+qId;
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
        }
    }
}
