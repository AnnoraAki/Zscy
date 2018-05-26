package com.example.cynthia.zscy.Bean;

import java.util.List;

public class Transaction {

    /**
     * id : 15270450378678627
     * time : 5
     * title : this is a title
     * content : this is a content
     * updated_time : 2018-05-23 11:10:38
     * date : [{"class":0,"day":5,"week":[12]}]
     */

    private long id;
    private int time;
    private String title;
    private String content;
    private String updated_time;
    private List<TransactionDetail> date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }

    public List<TransactionDetail> getDate() {
        return date;
    }

    public void setDate(List<TransactionDetail> date) {
        this.date = date;
    }
}
