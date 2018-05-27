package com.example.cynthia.zscy.Activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cynthia.httphelper.HttpHelper;
import com.example.cynthia.httphelper.Response.Callback;
import com.example.cynthia.httphelper.Response.Response;
import com.example.cynthia.imageload.ConfigSetting;
import com.example.cynthia.imageload.ImageLoad;
import com.example.cynthia.zscy.Adapter.AnswerAdapter;
import com.example.cynthia.zscy.Adapter.RemarkAdapter;
import com.example.cynthia.zscy.Bean.Answer;
import com.example.cynthia.zscy.Bean.Remark;
import com.example.cynthia.zscy.Config.Config;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.Utils.Application;
import com.example.cynthia.zscy.Utils.JsonUtils;
import com.example.cynthia.zscy.Utils.TextUtil;
import com.example.cynthia.zscy.Utils.ToastUtils;
import com.example.cynthia.zscy.widget.CircleView;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout container1;
    private RelativeLayout container2;
    private TextView title;
    private CircleView avatar;
    private TextView id;
    private ImageView sex;
    private TextView date;
    private TextView adoptWord1;
    private TextView adoptWord2;
    private TextView content;
    private ImageView picture;
    private TextView remarkNum;
    private TextView makePraise;
    private TextView makeRemark;
    private RecyclerView mRemarkRv;
    private LinearLayout addRemark;
    private LinearLayout setting;
    private EditText remark;
    private TextView send;

    private String aId;
    private int self;
    private String kind;
    private String title1;
    private Answer answer;
    private List<Remark> remarks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode
                (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN|
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_detial_contaier);
        setToolbarVisible();
        setTitle("回答详情");
        setLeftDrawable(getResources().getDrawable(R.drawable.ic_arrows));
        setRightDrawable(getResources().getDrawable(R.drawable.ic_answer_more));
        Intent intent = getIntent();
        aId = intent.getStringExtra("aId");
        kind = intent.getStringExtra("kind");
        self = intent.getIntExtra("self",0);
        title1 = intent.getStringExtra("title");
        answer = (Answer)intent.getSerializableExtra("answer");
        String param = "stuNum="+ Application.getAc()+"&idNum="+Application.getPw()+"&answer_id=" + aId;
        initView();
        getList(param);

    }

    private void addData(String string){
        remarks = JsonUtils.jsonRemarks(string);
        title.setText(title1);
        TextUtil.setTVColor(title1,'#','#', Color.parseColor("#7195FA"),title);
        content.setText(answer.getContent());
        id.setText(answer.getNickname());
        date.setText(answer.getCreated_at());
        if (answer.getPhoto_thumbnail_src().equals("") || answer.getPhoto_thumbnail_src().equals("null")){
            avatar.setImageResource(R.drawable.default_avatar);
        } else {
            ConfigSetting setting = new ConfigSetting.builder(Application.getContext())
                    .error(R.drawable.default_avatar)
                    .from(answer.getPhoto_thumbnail_src())
                    .build();
            ImageLoad.show(avatar, setting);
        }
        if (kind.equals("情感")){
            int res = answer.getGender().equals("男")?R.drawable.ic_answer_man:R.drawable.ic_answer_woman;
            sex.setImageDrawable(Application.getContext().getResources().getDrawable(res));
        } else {
            sex.setVisibility(View.GONE);
        }
        int id = Integer.parseInt(aId);
        makePraise.setText("点赞（"+answer.getPraise_num()+")");
        makeRemark.setText("评论（"+answer.getComment_num()+")");
        remarkNum.setText("评论"+answer.getComment_num());
        RemarkAdapter remarkAdapter =  new RemarkAdapter(remarks,id,kind);
        mRemarkRv.setAdapter(remarkAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Application.getContext());
        mRemarkRv.setLayoutManager(layoutManager);

        setLeftDrawableClick(this);
        setRightDrawableClick(this);
        makePraise.setOnClickListener(this);
        makeRemark.setOnClickListener(this);
        send.setOnClickListener(this);

    }

    private void initView(){
        container1 = findViewById(R.id.detail_container1);
        container2 = findViewById(R.id.detail_container2);

        addContentLayout(R.layout.container_answer_detail,container1,ViewGroup.LayoutParams.WRAP_CONTENT);
        addContentLayout(R.layout.container_remark,container2,ViewGroup.LayoutParams.MATCH_PARENT);

        title = findViewById(R.id.detail_as_title);
        avatar = findViewById(R.id.detail_as_avatar);
        id = findViewById(R.id.detail_as_id);
        sex = findViewById(R.id.detail_as_sex);
        content = findViewById(R.id.detail_as_context);
        date = findViewById(R.id.detail_as_time);
        adoptWord1 = findViewById(R.id.detail_as_adopt_word);
        adoptWord2 = findViewById(R.id.detail_as_adpoted);
        picture = findViewById(R.id.detail_as_picture);
        remarkNum = findViewById(R.id.detail_remark_num);
        makePraise = findViewById(R.id.remark_left_word);
        makeRemark = findViewById(R.id.remark_right_word);
        addRemark = findViewById(R.id.add_remark);
        remark = findViewById(R.id.text_comment);
        send = findViewById(R.id.send_comment);
        setting = findViewById(R.id.remark_setting);
        mRemarkRv = findViewById(R.id.remarkRv);

        if (answer.getIs_adopted().equals("1")){
            adoptWord2.setVisibility(View.GONE);
        } else if (self == 1){
            adoptWord1.setVisibility(View.GONE);
        } else {
            adoptWord1.setVisibility(View.GONE);
            adoptWord2.setVisibility(View.GONE);
        }

        addRemark.setVisibility(View.GONE);

        if (answer.getPhoto_url() == null||answer.getPhoto_url().size() == 0){
            picture.setVisibility(View.GONE);
        }
    }

    public void addContentLayout(@LayoutRes int layoutId, RelativeLayout root, int height) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layoutId, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                height);
        root.addView(contentView, params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bar_left:
                finish();
                break;
            case R.id.bar_right:
                ToastUtils.showResponse("暂未开发");
                break;
            case R.id.remark_left_word:
                final String param = "stuNum="+Application.getAc()+"&idNum="+Application.getPw()+"&answer_id=" + answer.getId();
                if (answer.getIs_praised() == 0){
                    HttpHelper helper = new HttpHelper.set().mode("POST").url(Config.PRAISE).param(param).build();
                    new Response.from(helper).get(new Callback() {
                        @Override
                        public void succeed(String response) {
                            ToastUtils.showResponse("点赞成功！");
                            makePraise.setText("点赞（"+Integer.parseInt(answer.getPraise_num())+1+"）");
                            answer.setIs_praised(1);
                            answer.setPraise_num(Integer.parseInt(answer.getPraise_num())+1+"");
                        }

                        @Override
                        public void error(Exception e, int status) {
                            ToastUtils.showError(e.toString());
                        }
                    });
                } else {
                    HttpHelper helper = new HttpHelper.set().mode("POST").url(Config.CANCEL_PRAISE).param(param).build();
                    new Response.from(helper).get(new Callback() {
                        @Override
                        public void succeed(String response) {
                            ToastUtils.showResponse("取消成功！");
                            makePraise.setText(Integer.parseInt(answer.getPraise_num())-1+"");
                            answer.setIs_praised(0);
                            answer.setPraise_num(Integer.parseInt(answer.getPraise_num())+1+"");
                        }

                        @Override
                        public void error(Exception e, int status) {
                            ToastUtils.showError(e.toString());
                        }
                    });
                }
                break;
            case R.id.remark_right_word:
                setting.setVisibility(View.GONE);
                addRemark.setVisibility(View.VISIBLE);
                break;
            case R.id.send_comment:
                String cont = remark.getText().toString();
                String param1 = "stuNum="+Application.getAc()+"&idNum="+Application.getPw()+"&answer_id=" + answer.getId()+"&content="+cont;
                HttpHelper helper = new HttpHelper.set().url(Config.REMARK_ANSWER)
                        .mode("POST").param(param1).build();
                new Response.from(helper).get(new Callback() {
                    @Override
                    public void succeed(String response) {
                        ToastUtils.showResponse("评论成功");
                        makeRemark.setText("");
                        makeRemark.clearFocus();
                        addRemark.setVisibility(View.GONE);
                        setting.setVisibility(View.VISIBLE);
                        String param = "stuNum="+Application.getAc()+"&idNum="+Application.getPw()+"&answer_id=" + answer.getId();
                        getList(param);
                    }

                    @Override
                    public void error(Exception e, int status) {
                        ToastUtils.showResponse(e.toString());
                    }
                });
                break;
            case R.id.detail_as_adpoted:
                break;
        }
    }

    private void getList(String param){
        HttpHelper helper = new HttpHelper.set().mode("POST").url(Config.GET_REMARK_LIST)
                .param(param).build();
        new Response.from(helper).get(new Callback() {
            @Override
            public void succeed(String response) {
                addData(response);
            }

            @Override
            public void error(Exception e, int status) {
                ToastUtils.showError(e.toString());
            }
        });
    }
}
