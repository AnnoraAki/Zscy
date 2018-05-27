package com.example.cynthia.zscy.Utils;

import android.util.Log;

import com.example.cynthia.zscy.Bean.Answer;
import com.example.cynthia.zscy.Bean.Question;
import com.example.cynthia.zscy.Bean.QuestionDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class JsonUtils {
    public static List<Question> jsonQuestions(String response) {
        JSONObject jsonObject = null;
        ArrayList<Question> questions = new ArrayList<>();
        try {
            jsonObject = new JSONObject(response);
            if (jsonObject.isNull("data")){
                return null;
            }
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                Question question = new Question();
                question.setTitle(jsonObject2.getString("title"));
                question.setDescription(jsonObject2.getString("description"));
                question.setKind(jsonObject2.getString("kind"));
                question.setTags(jsonObject2.getString("tags"));
                question.setReward(jsonObject2.getInt("reward"));
                question.setAnswer_num(jsonObject2.getInt("answer_num"));
                question.setDisappear_at(jsonObject2.getString("disappear_at"));
                question.setCreated_at(jsonObject2.getString("created_at"));
                question.setIs_anonymous(jsonObject2.getInt("is_anonymous"));
                question.setId(jsonObject2.getInt("id"));
                String temp = jsonObject2.getString("photo_thumbnail_src");
                temp = temp.replaceAll("http","https");
                question.setPhoto_thumbnail_src(temp);
                question.setNickname(jsonObject2.getString("nickname"));
                question.setGender(jsonObject2.getString("gender"));
                questions.add(question);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public static QuestionDetail jsonQuestionDetail(String response){
        QuestionDetail detail = new QuestionDetail();
        JSONObject jsonObject = null;
        ArrayList<Answer> answers = new ArrayList<>();
        ArrayList<String> urls = new ArrayList<>();
        try {
            jsonObject = new JSONObject(response);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            detail.setIs_self(jsonObject1.getInt("is_self"));
            detail.setTitle(jsonObject1.getString("title"));
            detail.setDescription(jsonObject1.getString("description"));
            detail.setKind(jsonObject1.getString("kind"));
            detail.setTags(jsonObject1.getString("tags"));
            detail.setReward(jsonObject1.getString("reward"));
            detail.setDisappear_at(jsonObject1.getString("disappear_at"));
            detail.setQuestioner_nickname(jsonObject1.getString("questioner_nickname"));
            detail.setIs_self(jsonObject1.getInt("is_self"));
            String temp = jsonObject1.getString("questioner_photo_thumbnail_src");
            temp = temp.replaceAll("http","https");
            detail.setQuestioner_photo_thumbnail_src(temp);
            detail.setQuestioner_nickname(jsonObject1.getString("questioner_nickname"));
            detail.setQuestioner_gender(jsonObject1.getString("questioner_gender"));
            if (jsonObject1.isNull("photo_urls")){
                urls.clear();
            }
            JSONArray jsonArray1 = jsonObject1.getJSONArray("photo_urls");
            for (int i = 0; i < jsonArray1.length(); i++) {
                String url = jsonArray1.getString(i);
                url = url.replaceAll("http","https");
                urls.add(url);
            }
            detail.setPhoto_urls(urls);
            if (jsonObject1.isNull("answers")){
                answers.clear();
            } else {
                JSONArray jsonArray = jsonObject1.getJSONArray("answers");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    Answer answer = new Answer();
                    answer.setContent(jsonObject2.getString("content"));
                    answer.setCreated_at(jsonObject2.getString("created_at"));
                    answer.setPraise_num(jsonObject2.getString("praise_num"));
                    answer.setComment_num(jsonObject2.getString("comment_num"));
                    answer.setIs_adopted(jsonObject2.getString("is_adopted"));
                    answer.setIs_praised(jsonObject2.getInt("is_praised"));
                    answer.setCreated_at(jsonObject2.getString("created_at"));
                    answer.setId(jsonObject2.getString("id"));
                    String temp1 = jsonObject2.getString("photo_thumbnail_src");
                    temp1 = temp1.replaceAll("http", "https");
                    answer.setPhoto_thumbnail_src(temp1);
                    answer.setNickname(jsonObject2.getString("nickname"));
                    answer.setGender(jsonObject2.getString("gender"));

                    JSONArray jsonArray3 = jsonObject2.getJSONArray("photo_url");
                    ArrayList<String> mUrl = new ArrayList<>();
                    for (int j = 0; j < jsonArray3.length(); j++) {
                        String url = jsonArray1.getString(j);
                        url = url.replaceAll("http", "https");
                        mUrl.add(url);
                    }
                    answer.setPhoto_url(mUrl);
                    answers.add(answer);
                }
                detail.setAnswers(answers);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return detail;
    }
}