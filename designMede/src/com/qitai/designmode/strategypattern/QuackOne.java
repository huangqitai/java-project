package com.qitai.designmode.strategypattern;

public class QuackOne implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("比如说东北鸭的叫声是：嘎嘎嘎");
    }
}
