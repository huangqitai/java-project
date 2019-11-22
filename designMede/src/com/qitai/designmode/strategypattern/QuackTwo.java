package com.qitai.designmode.strategypattern;

public class QuackTwo implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("一直会唱歌的鸭子：啦啦啦");
    }
}
