package com.example.cynthia.zscy.Bean;

import java.util.List;

public class Schedule {

    /**
     * status : 200
     * version : 16.9.4
     * term : 2017-2018学年第2学期
     * stuNum : 2016210xxx
     * cachedTimestamp : 1527003165462
     * outOfDateTimestamp : 1527607965462
     * data : [{"hash_day":0,"hash_lesson":0,"begin_lesson":1,"day":"星期一","lesson":"一二节","course":"通信软件开发与应用(1)","teacher":"罗文丰","classroom":"4403","rawWeek":"1-16周","weekModel":"all","weekBegin":1,"weekEnd":16,"type":"必修","period":2,"_id":"5b04381de0800855200fd7fb","week":[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]}]
     * nowWeek : 12
     */

    private int status;
    private String version;
    private String term;
    private String stuNum;
    private long cachedTimestamp;
    private long outOfDateTimestamp;
    private int nowWeek;
    private List<ScheduleDetail> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public long getCachedTimestamp() {
        return cachedTimestamp;
    }

    public void setCachedTimestamp(long cachedTimestamp) {
        this.cachedTimestamp = cachedTimestamp;
    }

    public long getOutOfDateTimestamp() {
        return outOfDateTimestamp;
    }

    public void setOutOfDateTimestamp(long outOfDateTimestamp) {
        this.outOfDateTimestamp = outOfDateTimestamp;
    }

    public int getNowWeek() {
        return nowWeek;
    }

    public void setNowWeek(int nowWeek) {
        this.nowWeek = nowWeek;
    }

    public List<ScheduleDetail> getData() {
        return data;
    }

    public void setData(List<ScheduleDetail> data) {
        this.data = data;
    }
}
