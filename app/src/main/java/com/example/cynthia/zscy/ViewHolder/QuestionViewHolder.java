package com.example.cynthia.zscy.ViewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.cynthia.imageload.ConfigSetting;
import com.example.cynthia.imageload.ImageLoad;
import com.example.cynthia.zscy.Activitys.DetailActivity;
import com.example.cynthia.zscy.Bean.Question;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.Utils.Application;
import com.example.cynthia.zscy.widget.CircleView;

import java.util.List;

public class QuestionViewHolder extends BaseViewHolder {

    private CircleView avatar;
    private TextView title;
    private TextView tag;
    private TextView time;
    private TextView userId;
    private TextView price;
    private TextView context;
    private List<Question> mQuestion;
    private TextView lookMore;


    public QuestionViewHolder(View itemView,List<Question> questions) {
        super(itemView);
        avatar = getView(R.id.question_avatar);
        time = getView(R.id.question_time);
        userId = getView(R.id.question_id);
        price = getView(R.id.question_price);
        context = getView(R.id.question_context);
        title = getView(R.id.question_title);
        tag = getView(R.id.question_tag);
        lookMore = getView(R.id.question_look_more);

        mQuestion = questions;

        lookMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = mQuestion.get(getLayoutPosition());
                String param = "stuNum="+Application.getAc()+"&idNum="+Application.getPw()+"&question_id=" + question.getId();
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("param",param);
                intent.putExtra("qId",question.getId());
                v.getContext().startActivity(intent);
            }
        });

    }

    public void initData(Question question) {
        time.setText("小时后消失");//记得做一个时间戳

        price.setText(question.getReward() + "积分");
        context.setText(question.getDescription());
        tag.setText("#" + question.getTags() + "#");
        title.setText(question.getTitle());
        userId.setText(question.getNickname());
        if (question.getIs_anonymous() == 0) {
            ConfigSetting setting = new ConfigSetting.builder(Application.getContext())
                    .error(R.drawable.default_avatar)
                    .from(question.getPhoto_thumbnail_src())
                    .build();
            ImageLoad.show(avatar, setting);

        } else {
            avatar.setImageResource(R.drawable.default_avatar);
        }
    }
}
