package com.example.cynthia.zscy.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cynthia.zscy.Bean.Question;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.Utils.Application;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_ask_question);
        setToolbarVisible();
        setTitle("求助");
        setLeft(getApplication().getResources().getDrawable(R.drawable.ic_arrows));

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
        setLeftClick(this);
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
        }
    }
}
