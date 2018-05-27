package com.example.cynthia.zscy.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cynthia.httphelper.HttpHelper;
import com.example.cynthia.httphelper.Response.Callback;
import com.example.cynthia.httphelper.Response.Response;
import com.example.cynthia.zscy.Adapter.QuestionAdapter;
import com.example.cynthia.zscy.Bean.Answer;
import com.example.cynthia.zscy.Bean.Question;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.Utils.JsonUtils;
import com.example.cynthia.zscy.Utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NoticeViewHolder extends BaseViewHolder {

    public static final int TYPE_QUESTION = 0;
    public static final int TYPE_ANSWER = 1;
    public static final int TYPE_REMARK = 2;

    private TextView mTextView;
    boolean loading;

    public NoticeViewHolder(View itemView) {
        super(itemView);
        mTextView = getView(R.id.notice_word);
        loading = false;
    }

    public TextView getTextView() {
        return mTextView;
    }

    public void loadData(String api, String param, final RecyclerView.Adapter adapter, final int type){
        int n = 1;
        if ((adapter.getItemCount()-n)%6 != 0){
            mTextView.setText("没有更多了...");
            return;
        }else if(adapter.getItemCount() - n == 0){
            mTextView.setText("这里什么都还没有，期待您的补充 ♪(^∇^*)");
            return;
        }
        if (loading)
            return;
        mTextView.setText("加载中...");
        loading = true;
        HttpHelper helper = new HttpHelper.set().mode("POST")
                .url(api)
                .param(param)
                .build();
        Response response = new Response.from(helper).get(new Callback() {
            @Override
            public void succeed(String response) {
                loading = false;
                JSONObject jsonObject = null;
                JSONArray jsonArray = null;
                try {
                    jsonObject = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (jsonObject.isNull("data")){
                    mTextView.setText("没有更多了...");
                } else {
                    addData(response,type,adapter);
                }
            }

            @Override
            public void error(Exception e, int status) {
                mTextView.setText("加载失败:)");
                ToastUtils.showResponse(e.toString());
            }
        });

    }

    private void addData(String response,int type,RecyclerView.Adapter adapter){
        if (type == TYPE_QUESTION){
            List<Question> questions = JsonUtils.jsonQuestions(response);
            ((QuestionAdapter)adapter).addQuestions(questions);
        } else if (type == TYPE_ANSWER){
//            todo : json the more answers
//            List<Answer> answers = JsonUtils.
        } else {

        }


    }
}
