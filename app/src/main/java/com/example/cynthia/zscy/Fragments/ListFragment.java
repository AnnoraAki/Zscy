package com.example.cynthia.zscy.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cynthia.httphelper.HttpHelper;
import com.example.cynthia.httphelper.Response.Callback;
import com.example.cynthia.httphelper.Response.Response;
import com.example.cynthia.zscy.Adapter.QuestionAdapter;
import com.example.cynthia.zscy.Bean.Question;
import com.example.cynthia.zscy.Config.Config;
import com.example.cynthia.zscy.R;
import com.example.cynthia.zscy.Utils.Application;
import com.example.cynthia.zscy.Utils.JsonUtils;
import com.example.cynthia.zscy.Utils.ToastUtils;
import com.example.cynthia.zscy.ViewHolder.QuestionViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {
    public static String TAB_FRAGMENT = "tab_fragment";
    private RecyclerView[] mRv;
    private QuestionAdapter[] questionAdapter;
    private int type;
    private HttpHelper helper;
    private List<Question> allQuestions = new ArrayList<>();
    private List<Question> studyQuestions = new ArrayList<>();
    private List<Question> lifeQuestions = new ArrayList<>();
    private List<Question> emotionQuestions = new ArrayList<>();
    private List<Question> otherQuestions = new ArrayList<>();
    public static String[] title = {"全部","学习","生活","情感","其他"};


    public static ListFragment newInstance(int type) {
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAB_FRAGMENT, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = (int) getArguments().getSerializable(TAB_FRAGMENT);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_questions, container, false);
        initView(view);
        return view;
    }


    protected void initView(View view) {
        mRv = new RecyclerView[5];
        questionAdapter = new QuestionAdapter[5];
        for (int i = 0; i < 5; i++) {
            mRv[i] = view.findViewById(R.id.mQuestionRv);
        }
        switch (type) {
            case 1:
                helper = new HttpHelper.set().mode("POST").url(Config.GET_QUESTION_LIST)
                        .param("page="+1+"&kind="+title[type-1]).build();
                getResponse(helper,type-1);

                break;
            case 2:
                helper = new HttpHelper.set().mode("POST").url(Config.GET_QUESTION_LIST)
                        .param("page="+1+"&kind="+title[type-1]).build();
                getResponse(helper,type-1);
                break;
            case 3:
                helper = new HttpHelper.set().mode("POST").url(Config.GET_QUESTION_LIST)
                        .param("page="+1+"&kind="+title[type-1]).build();
                getResponse(helper,type-1);
                break;
            case 4:
                helper = new HttpHelper.set().mode("POST").url(Config.GET_QUESTION_LIST)
                        .param("page="+1+"&kind="+title[type-1]).build();
                getResponse(helper,type-1);
                break;
            case 5:
                helper = new HttpHelper.set().mode("POST").url(Config.GET_QUESTION_LIST)
                        .param("page="+1+"&kind="+title[type-1]).build();
                getResponse(helper,type-1);
                break;
        }
    }

    private void getResponse (HttpHelper httpHelper, final int type){
        Response res = new Response.from(httpHelper).get(new Callback() {
            @Override
            public void succeed(String response) {
                addData(response,type);
            }

            @Override
            public void error(Exception e, int status) {
                ToastUtils.showError(e.toString());
            }
        });
    }

    private void addData(String re,int type){
        List<Question> questions = JsonUtils.jsonQuestions(re);
        switch (type){
            case 0:
                if (questions != null){
                    allQuestions.addAll(questions);
                } else {
                    allQuestions.clear();
                }
                questionAdapter[type] = new QuestionAdapter(allQuestions,title[type]);
                break;
            case 1:
                if (questions != null){
                    studyQuestions.addAll(questions);
                } else {
                    studyQuestions.clear();
                }
                questionAdapter[type] = new QuestionAdapter(studyQuestions,title[type]);
                break;
            case 2:
                if (questions != null){
                    lifeQuestions.addAll(questions);
                } else {
                    lifeQuestions.clear();
                }
                questionAdapter[type] = new QuestionAdapter(lifeQuestions,title[type]);
                break;
            case 3:
                if (questions != null){
                    emotionQuestions.addAll(questions);
                } else {
                    emotionQuestions.clear();
                }
                questionAdapter[type] = new QuestionAdapter(emotionQuestions,title[type]);
                break;
            case 4:
                if (questions != null){
                    otherQuestions.addAll(questions);
                } else {
                    otherQuestions.clear();
                }
                questionAdapter[type] = new QuestionAdapter(otherQuestions,title[type]);
                break;
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(Application.getContext());
        mRv[type].setAdapter(questionAdapter[type]);
        mRv[type].setLayoutManager(layoutManager);
    }
}
