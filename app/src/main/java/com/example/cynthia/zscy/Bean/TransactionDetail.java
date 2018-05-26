package com.example.cynthia.zscy.Bean;

import java.util.List;

class TransactionDetail {
    /**
     * class : 0
     * day : 5
     * week : [12]
     */


    private int classX;
    private int day;
    private List<Integer> week;

    public int getClassX() {
        return classX;
    }

    public void setClassX(int classX) {
        this.classX = classX;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public List<Integer> getWeek() {
        return week;
    }

    public void setWeek(List<Integer> week) {
        this.week = week;
    }
}
