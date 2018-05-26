package com.example.cynthia.zscy.Bean;

import java.util.List;

class ScheduleDetail {
    /**
     * hash_day : 0
     * hash_lesson : 0
     * begin_lesson : 1
     * day : 星期一
     * lesson : 一二节
     * course : 通信软件开发与应用(1)
     * teacher : 罗文丰
     * classroom : 4403
     * rawWeek : 1-16周
     * weekModel : all
     * weekBegin : 1
     * weekEnd : 16
     * type : 必修
     * period : 2
     * _id : 5b04381de0800855200fd7fb
     * week : [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]
     */

    private int hash_day;
    private int hash_lesson;
    private int begin_lesson;
    private String day;
    private String lesson;
    private String course;
    private String teacher;
    private String classroom;
    private String rawWeek;
    private String weekModel;
    private int weekBegin;
    private int weekEnd;
    private String type;
    private int period;
    private String _id;
    private List<Integer> week;

    public int getHash_day() {
        return hash_day;
    }

    public void setHash_day(int hash_day) {
        this.hash_day = hash_day;
    }

    public int getHash_lesson() {
        return hash_lesson;
    }

    public void setHash_lesson(int hash_lesson) {
        this.hash_lesson = hash_lesson;
    }

    public int getBegin_lesson() {
        return begin_lesson;
    }

    public void setBegin_lesson(int begin_lesson) {
        this.begin_lesson = begin_lesson;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getRawWeek() {
        return rawWeek;
    }

    public void setRawWeek(String rawWeek) {
        this.rawWeek = rawWeek;
    }

    public String getWeekModel() {
        return weekModel;
    }

    public void setWeekModel(String weekModel) {
        this.weekModel = weekModel;
    }

    public int getWeekBegin() {
        return weekBegin;
    }

    public void setWeekBegin(int weekBegin) {
        this.weekBegin = weekBegin;
    }

    public int getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(int weekEnd) {
        this.weekEnd = weekEnd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<Integer> getWeek() {
        return week;
    }

    public void setWeek(List<Integer> week) {
        this.week = week;
    }
}
