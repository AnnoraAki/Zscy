package com.example.cynthia.zscy.Bean;

import java.io.Serializable;
import java.util.List;

public class Answer implements Serializable {
    /**
     * id : 10
     * nickname : Jay
     * photo_thumbnail_src : http://wx.idsbllp.cn/cyxbsMobile/Public/photo/thumbnail/1503374869_593154551.png
     * gender : 男
     * content : 菜
     * created_at : 2018-04-22 14:08:50
     * praise_num : 0
     * comment_num : 0
     * is_adopted : 0
     * is_praised : 0
     * photo_url : []
     */

    private String id;
    private String nickname;
    private String photo_thumbnail_src;
    private String gender;
    private String content;
    private String created_at;
    private String praise_num;
    private String comment_num;
    private String is_adopted;
    private int is_praised;
    private List<String> photo_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoto_thumbnail_src() {
        return photo_thumbnail_src;
    }

    public void setPhoto_thumbnail_src(String photo_thumbnail_src) {
        this.photo_thumbnail_src = photo_thumbnail_src;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getPraise_num() {
        return praise_num;
    }

    public void setPraise_num(String praise_num) {
        this.praise_num = praise_num;
    }

    public String getComment_num() {
        return comment_num;
    }

    public void setComment_num(String comment_num) {
        this.comment_num = comment_num;
    }

    public String getIs_adopted() {
        return is_adopted;
    }

    public void setIs_adopted(String is_adopted) {
        this.is_adopted = is_adopted;
    }

    public int getIs_praised() {
        return is_praised;
    }

    public void setIs_praised(int is_praised) {
        this.is_praised = is_praised;
    }

    public List<String> getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(List<String> photo_url) {
        this.photo_url = photo_url;
    }
}
