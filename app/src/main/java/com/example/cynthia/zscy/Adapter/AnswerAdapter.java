package com.example.cynthia.zscy.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cynthia.zscy.Bean.Answer;
import com.example.cynthia.zscy.Bean.Question;
import com.example.cynthia.zscy.Config.Config;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.Utils.Application;
import com.example.cynthia.zscy.ViewHolder.AnswerViewHolder;
import com.example.cynthia.zscy.ViewHolder.NoticeViewHolder;
import com.example.cynthia.zscy.ViewHolder.QuestionViewHolder;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Answer> answers;
    private int qId;
    private static final int TYPE_NOTICE = 0;
    private static final int TYPE_ANSWER = 1;

    public AnswerAdapter(List<Answer> answers,int qId) {
        this.answers = answers;
        this.qId = qId;
    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() - 1 ? TYPE_NOTICE : TYPE_ANSWER;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_ANSWER:
                View view = inflater.inflate(R.layout.recycler_answers, parent, false);
                return new AnswerViewHolder(view,answers);

            case TYPE_NOTICE:
                View view1 = inflater.inflate(R.layout.recycler_loading, parent, false);
                return new NoticeViewHolder(view1);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AnswerViewHolder) {
            AnswerViewHolder answerViewHolder= (AnswerViewHolder)holder;
            answerViewHolder.initData(answers.get(position));
        } else {
            NoticeViewHolder noticeViewHolder = (NoticeViewHolder) holder;
            String param = "stuNum="+ Application.getAc()+
                    "&idNum="+Application.getPw() +
                    "&page=" + (answers.size() / 6 + 1 ) + "&size=" + 6 + "&question_id=" + qId;
            noticeViewHolder.loadData(Config.GET_ANSWER_LIST, param, this, NoticeViewHolder.TYPE_ANSWER);


        }

    }

    @Override
    public int getItemCount() {
        return answers.size() + 1;
    }

    public void addQuestions(List<Answer> answers) {
        this.answers.addAll(answers);
        notifyDataSetChanged();
    }
}
