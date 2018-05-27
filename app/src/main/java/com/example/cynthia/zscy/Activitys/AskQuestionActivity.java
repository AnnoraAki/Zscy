package com.example.cynthia.zscy.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.cynthia.zscy.Bean.Question;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.Utils.Application;
import com.example.cynthia.zscy.Utils.TextUtil;

public class AskQuestionActivity extends BaseActivity implements View.OnClickListener {

    private EditText title;
    private EditText content;
    private TextView tag;
    private ImageView addPicture;
    private ImageView addTopic;
    private ImageView adPicture;
    private ImageView wantAnonymous;
    private TextView titleFlag;
    private TextView contentFlag;
    private Question question = new Question();

    private int maxNum1 = 20;
    private int maxNum2 = 200;
    private String kind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_ask_question);
        setToolbarVisible();
        setTitle("求助");
        setLeftDrawable(getApplication().getResources().getDrawable(R.drawable.ic_arrows));
        setRightText("下一步");
        Intent intent = getIntent();
        kind = intent.getStringExtra("kind");

        initView();

        title.addTextChangedListener(new TextWatcher() {
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
                titleFlag.setText(maxNum1-temp.length()+"");
                TextUtil.setTVColor(temp.toString(),'#','#',R.color.titleBlue,title);
            }
        });

        content.addTextChangedListener(new TextWatcher() {
            CharSequence temp;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                contentFlag.setText(""+(maxNum2-temp.length()));
            }
        });

        wantAnonymous.setOnClickListener(this);
        addTopic.setOnClickListener(this);
        adPicture.setOnClickListener(this);
        addPicture.setOnClickListener(this);
        setLeftDrawableClick(this);
        setRightTextClick(this);
    }

    private void initView(){
        title = findViewById(R.id.ask_title);
        content = findViewById(R.id.aks_description);
        tag = findViewById(R.id.ask_tag);
        addPicture = findViewById(R.id.ask_add_picture);
        adPicture = findViewById(R.id.ask_picture);
        addTopic = findViewById(R.id.ask_topic);
        wantAnonymous = findViewById(R.id.ask_anonymous);
        titleFlag = findViewById(R.id.ask_title_flag);
        contentFlag = findViewById(R.id.ask_context_flag);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ask_tag:
                break;
            case R.id.ask_add_picture:
                break;
            case R.id.ask_picture:
                break;
            case R.id.ask_anonymous:
                if (question.getIs_anonymous() == 0)
                {
                    wantAnonymous.setImageDrawable(Application.getContext().getResources().getDrawable(R.drawable.ic_select));
                    question.setIs_anonymous(1);
                }else {
                    wantAnonymous.setImageDrawable(Application.getContext().getResources().getDrawable(R.drawable.ic_not_select));
                    question.setIs_anonymous(0);
                }

                break;
            case R.id.bar_left:
                finish();
                break;
            case R.id.bar_right_t:
                break;
        }
    }

    private void showPopupWindow(){
        View contentView = getLayoutInflater().from(Application.getContext()).inflate(R.layout.popup_choose_type, null);
        PopupWindow mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        View rootView = getLayoutInflater().from(Application.getContext()).inflate(R.layout.fragment_ask_question, null);
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

    private void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }
}
