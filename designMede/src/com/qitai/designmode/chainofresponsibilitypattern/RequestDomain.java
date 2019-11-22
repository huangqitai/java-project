package com.qitai.designmode.chainofresponsibilitypattern;

public class RequestDomain {
    private String name;
    private String title;
    private int day;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "申请人："+name+",   申请事项："+title+",     请假时间："+day;
    }
}
