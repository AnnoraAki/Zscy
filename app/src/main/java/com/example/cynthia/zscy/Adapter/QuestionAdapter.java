package com.example.cynthia.zscy.Adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cynthia.zscy.Bean.Question;
import com.example.cynthia.zscy.Config.Config;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.ViewHolder.NoticeViewHolder;
import com.example.cynthia.zscy.ViewHolder.QuestionViewHolder;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Question> questions;
    private String kind;
    private static final int TYPE_NOTICE = 0;
    private static final int TYPE_QUESTION = 1;

    public QuestionAdapter(List<Question> questions, String kind) {
        this.questions = questions;
        this.kind = kind;
    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() - 1 ? TYPE_NOTICE : TYPE_QUESTION;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_QUESTION:
                View view = inflater.inflate(R.layout.recycler_questions, parent, false);
                return new QuestionViewHolder(view,questions,kind);

            case TYPE_NOTICE:
                View view1 = inflater.inflate(R.layout.recycler_loading, parent, false);
                return new NoticeViewHolder(view1);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof QuestionViewHolder) {
            QuestionViewHolder questionViewHolder = (QuestionViewHolder) holder;
            questionViewHolder.initData(questions.get(position),kind);
        } else {
            NoticeViewHolder noticeViewHolder = (NoticeViewHolder) holder;
            String param = "page=" + (questions.size() / 6 + 1 ) + "&size=" + 6 + "&kind=" + kind;
            noticeViewHolder.loadData(Config.GET_QUESTION_LIST, param, this, NoticeViewHolder.TYPE_QUESTION);


        }

    }

    @Override
    public int getItemCount() {
        return questions.size() + 1;
    }

    public void addQuestions(List<Question> questions) {
        this.questions.addAll(questions);
        notifyDataSetChanged();
    }
}
