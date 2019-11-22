package com.qitai.designmode.filterpattern;

public class Person {
    private String name;
    private int age;
    private String gender;

    public Person(String name,int age,String gender){
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "姓名："+name+",    年龄："+age+",     性别："+gender;
    }
}
