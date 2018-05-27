package com.example.cynthia.zscy.ViewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cynthia.imageload.ConfigSetting;
import com.example.cynthia.imageload.ImageLoad;
import com.example.cynthia.zscy.Activitys.CommentActivity;
import com.example.cynthia.zscy.Activitys.DetailActivity;
import com.example.cynthia.zscy.Bean.Answer;
import com.example.cynthia.zscy.Bean.Question;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.Utils.Application;
import com.example.cynthia.zscy.widget.CircleView;

import java.util.List;

public class AnswerViewHolder extends BaseViewHolder implements View.OnClickListener {

    private List<Answer> answers;
    private CircleView avatar;
    private TextView id;
    private ImageView sex;
    private ImageView accept;
    private TextView acceptWord;
    private TextView context;
    private TextView date;
    private TextView commentNum;
    private ImageView praise;
    private TextView praiseNum;
    private TextView lookMore;

    private String kind;


    public AnswerViewHolder(View itemView, final List<Answer> answers,String kind) {
        super(itemView);
        this.answers = answers;
        this.kind = kind;

        avatar = getView(R.id.answer_avatar);
        id = getView(R.id.answer_id);
        sex = getView(R.id.answer_sex);
        accept = getView(R.id.answer_accept);
        acceptWord = getView(R.id.answer_accept_word);
        context = getView(R.id.answer_context);
        date = getView(R.id.answer_date);
        commentNum = getView(R.id.answer_comment_num);
        praise = getView(R.id.answer_praise);
        praiseNum = getView(R.id.answer_praise_num);

        lookMore = getView(R.id.answer_look_more);
        lookMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer answer = answers.get(getLayoutPosition());
                String param = "stuNum="+Application.getAc()+"&idNum="+Application.getPw()+"&answer_id=" + answer.getId();
                Intent intent = new Intent(v.getContext(), CommentActivity.class);
                intent.putExtra("param",param);
                v.getContext().startActivity(intent);
            }
        });
    }

    public void initData(Answer answer) {
        date.setText(answer.getCreated_at());
        context.setText(answer.getContent());
        ConfigSetting setting = new ConfigSetting.builder(Application.getContext())
                    .error(R.drawable.default_avatar)
                    .from(answer.getPhoto_thumbnail_src())
                    .build();
        ImageLoad.show(avatar, setting);
        id.setText(answer.getNickname());
        if (kind.equals("情感")){
            int res = answer.getGender().equals("男")?R.drawable.ic_answer_man:R.drawable.ic_answer_woman;
            sex.setImageDrawable(Application.getContext().getResources().getDrawable(res));
        } else {
          sex.setVisibility(View.GONE);
        }

        if (answer.getIs_adopted().equals("0")){
            accept.setVisibility(View.GONE);
            acceptWord.setVisibility(View.GONE);
        }
        commentNum.setText(answer.getComment_num());
        praiseNum.setText(answer.getPraise_num());
        int resp = answer.getIs_praised()==0?R.drawable.ic_praise1:R.drawable.ic_praise_select;
        praise.setImageDrawable(Application.getContext().getResources().getDrawable(resp));
    }

    @Override
    public void onClick(View v) {
        Answer answer = answers.get(getLayoutPosition());
        switch (v.getId()) {
            case R.id.answer_look_more:
                Intent intent = new Intent(v.getContext(), CommentActivity.class);
                intent.putExtra("aId",answer.getId());
                v.getContext().startActivity(intent);
                break;
            case R.id.answer_accept:

                break;
        }
    }
}
