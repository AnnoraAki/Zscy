package com.example.cynthia.zscy.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cynthia.imageload.ConfigSetting;
import com.example.cynthia.imageload.ImageLoad;
import com.example.cynthia.zscy.Bean.Remark;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.Utils.Application;
import com.example.cynthia.zscy.widget.CircleView;

public class RemarkViewHolder extends BaseViewHolder {

    private CircleView avatar;
    private TextView nickname;
    private ImageView sex;
    private TextView date;
    private TextView content;

    private String kind;

    public RemarkViewHolder(View itemView,String kind) {
        super(itemView);
        avatar = getView(R.id.remark_avatar);
        nickname = getView(R.id.remark_id);
        sex = getView(R.id.remark_sex);
        date = getView(R.id.remark_date);
        content = getView(R.id.remark_context);

        this.kind = kind;
    }

    public void loadData(Remark remark){
        nickname.setText(remark.getNickname());
        content.setText(remark.getContent());
        date.setText(remark.getCreated_at());
        if (remark.getPhoto_thumbnail_src().equals("") || remark.getPhoto_thumbnail_src().equals("null")){
            avatar.setImageResource(R.drawable.default_avatar);
        } else {
            ConfigSetting setting = new ConfigSetting.builder(Application.getContext())
                    .error(R.drawable.default_avatar)
                    .from(remark.getPhoto_thumbnail_src())
                    .build();
            ImageLoad.show(avatar, setting);
        }
        if (kind.equals("情感")){
            int res = remark.getGender().equals("男")?R.drawable.ic_answer_man:R.drawable.ic_answer_woman;
            sex.setImageDrawable(Application.getContext().getResources().getDrawable(res));
        } else {
            sex.setVisibility(View.GONE);
        }
    }
}
