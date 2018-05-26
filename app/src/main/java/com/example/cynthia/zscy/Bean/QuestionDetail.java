package com.example.cynthia.zscy.Bean;

import java.util.List;

public class QuestionDetail {
    /**
     * is_self : 0
     * title : 这个代码太难写了\\ue056
     * description : 代码是真的难
     * reward : 2
     * disappear_at : 2018-04-27 02:22:22
     * tags : PHP
     * kind : 其他
     * photo_urls : []
     * questioner_nickname : 。
     * questioner_photo_thumbnail_src :
     * questioner_gender : 女
     * answers : [{"id":"10","nickname":"Jay","photo_thumbnail_src":"http://wx.idsbllp.cn/cyxbsMobile/Public/photo/thumbnail/1503374869_593154551.png","gender":"男","content":"菜","created_at":"2018-04-22 14:08:50","praise_num":"0","comment_num":"0","is_adopted":"0","is_praised":0,"photo_url":[]}]
     */

    private int is_self;
    private String title;
    private String description;
    private String reward;
    private String disappear_at;
    private String tags;
    private String kind;
    private String questioner_nickname;
    private String questioner_photo_thumbnail_src;
    private String questioner_gender;
    private List<String> photo_urls;
    private List<Answer> answers;

    public int getIs_self() {
        return is_self;
    }

    public void setIs_self(int is_self) {
        this.is_self = is_self;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getDisappear_at() {
        return disappear_at;
    }

    public void setDisappear_at(String disappear_at) {
        this.disappear_at = disappear_at;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getQuestioner_nickname() {
        return questioner_nickname;
    }

    public void setQuestioner_nickname(String questioner_nickname) {
        this.questioner_nickname = questioner_nickname;
    }

    public String getQuestioner_photo_thumbnail_src() {
        return questioner_photo_thumbnail_src;
    }

    public void setQuestioner_photo_thumbnail_src(String questioner_photo_thumbnail_src) {
        this.questioner_photo_thumbnail_src = questioner_photo_thumbnail_src;
    }

    public String getQuestioner_gender() {
        return questioner_gender;
    }

    public void setQuestioner_gender(String questioner_gender) {
        this.questioner_gender = questioner_gender;
    }

    public List<String> getPhoto_urls() {
        return photo_urls;
    }

    public void setPhoto_urls(List<String> photo_urls) {
        this.photo_urls = photo_urls;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
