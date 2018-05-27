package com.example.cynthia.zscy.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
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
import com.example.cynthia.zscy.Utils.ToastUtils;

public class AskQuestionActivity extends BaseActivity implements View.OnClickListener {

    private EditText title;
    private EditText content;
    private EditText tag;
    private ImageView addPicture;
    private ImageView addTopic;
    private ImageView adPicture;
    private ImageView wantAnonymous;
    private TextView titleFlag;
    private TextView contentFlag;
    private TextView next;
    private TextView[] tags = new TextView[6];
    private AlertDialog d;
    private Question question = new Question();

    private int maxNum1 = 20;
    private int maxNum2 = 200;
    private String kind;
    private String[] tagS = {"大物","英语","线代","高数","几何","思修"};

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
        question.setKind(kind);
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
        addPicture = findViewById(R.id.ask_add_picture);
        adPicture = findViewById(R.id.ask_picture);
        addTopic = findViewById(R.id.ask_topic);
        wantAnonymous = findViewById(R.id.ask_anonymous);
        titleFlag = findViewById(R.id.ask_title_flag);
        contentFlag = findViewById(R.id.ask_context_flag);
        setTagDialog();
    }

    @Override
    public void onClick(View v) {
        String titles = title.getText().toString();
        String descriptions = content.getText().toString();
        String m = null;
        switch (v.getId()){
            case R.id.ask_tag:
                if (titles.equals("")||descriptions.equals("")){
                    ToastUtils.showResponse("请填写好问题和内容之后才继续下一步动作哦");
                } else {
                    d.show();
                    question.setTitle(titles);
                    question.setDescription(descriptions);
                }
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
                d.show();
                break;
            case R.id.topic_physics:
                tag.setText("#"+tagS[0]+"#");
                m = tagS[0];
                break;
            case R.id.topic_english:
                tag.setText("#"+tagS[1]+"#");
                m = tagS[1];
                break;
            case R.id.topic_linear:
                tag.setText("#"+tagS[2]+"#");
                m = tagS[2];
                break;
            case R.id.topic_math:
                tag.setText("#"+tagS[3]+"#");
                m = tagS[3];
                break;
            case R.id.topic_geometry:
                tag.setText("#"+tagS[4]+"#");
                m = tagS[4];
                break;
            case R.id.topic_politics:
                tag.setText("#"+tagS[5]+"#");
                m = tagS[5];
                break;
            case R.id.ask_add_tag:
                String t = tag.getText().toString();
                if (t.startsWith("#")){
                    t = m;
                }
                if (t.equals("")){
                    ToastUtils.showError("请选择一个话题哦~");
                } else {
                    question.setTags(t);
                    String temp = "#"+t+"#"+question.getTitle();
                    title.setText(temp);
                    TextUtil.setTVColor(temp,'#','#', Color.parseColor("#7195FA"),title);
                }
                d.dismiss();
                break;
        }
    }

    private void setTagDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater =
                (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout =inflater.inflate(R.layout.popup_choose_tag,null);
        builder.setView(layout);
        d = builder.create();
        Window dialogWindow = d.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.height =WindowManager.LayoutParams.WRAP_CONTENT;
        p.width =WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(p);
        tags[0] = layout.findViewById(R.id.topic_physics);
        tags[1] = layout.findViewById(R.id.topic_english);
        tags[2] = layout.findViewById(R.id.topic_linear);
        tags[3] = layout.findViewById(R.id.topic_math);
        tags[4] = layout.findViewById(R.id.topic_geometry);
        tags[5] = layout.findViewById(R.id.topic_politics);
        next = layout.findViewById(R.id.topic_next);
        tag = layout.findViewById(R.id.ask_add_tag);
        for (int i = 0; i < 5; i++) {
            tags[i].setOnClickListener(this);
        }
    }
}
