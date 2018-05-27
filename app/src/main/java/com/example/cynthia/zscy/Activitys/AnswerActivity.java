package com.example.cynthia.zscy.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cynthia.httphelper.HttpHelper;
import com.example.cynthia.httphelper.Response.Callback;
import com.example.cynthia.httphelper.Response.Response;
import com.example.cynthia.zscy.Config.Config;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.Utils.Application;
import com.example.cynthia.zscy.Utils.TextUtil;
import com.example.cynthia.zscy.Utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class AnswerActivity extends BaseActivity implements View.OnClickListener {

    private EditText description;
    private TextView desFlag;
    private ImageView addPicture;
    private int qId;

    private int maxNum = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_answer);
        setToolbarVisible();
        setTitle("提供帮助");
        setLeftText("取消");
        setRightText("提交");
        initView();
        description.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;

            }

            @Override
            public void afterTextChanged(Editable s) {
                desFlag.setText(maxNum-temp.length()+"");
            }
        });
        setLeftTextClick(this);
        setRightTextClick(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bar_left_t:
                Intent intent = new Intent(AnswerActivity.this,DetailActivity.class);
                intent.putExtra("qId",qId);
                setResult(1,intent);
                finish();
                break;
            case R.id.bar_right_t:
                String desc = description.getText().toString();
                if (desc.equals("")){
                    ToastUtils.showError("请填写你的回答哦~");
                } else {
                    String param = "stuNum="+ Application.getAc()
                            +"&idNum="+Application.getPw()+"&content="+desc+"&question_id="+qId;
                    HttpHelper helper = new HttpHelper.set().mode("POST").url(Config.ANSWER_QUESTION)
                            .param(param).build();
                    new Response.from(helper).get(new Callback() {
                        @Override
                        public void succeed(String response) {
                            ToastUtils.showResponse("回答成功！");
                            Intent intent = new Intent(AnswerActivity.this,DetailActivity.class);
                            intent.putExtra("qId",qId);
                            setResult(1,intent);
                            finish();
                        }

                        @Override
                        public void error(Exception e, int status) {
                            if (status == 403){
                                ToastUtils.showError("你已经回答过该问题");
                                finish();
                            } else {
                                ToastUtils.showError("出了点小问题...");
                            }
                        }
                    });
                }
                break;
        }
    }

    private void initView(){
        Intent intent =getIntent();
        qId = intent.getIntExtra("qId",-1);
        description = findViewById(R.id.make_aw_description);
        desFlag = findViewById(R.id.make_aw_context_flag);
        addPicture = findViewById(R.id.make_aw_add_picture);
    }
}
