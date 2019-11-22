package com.qitai.designmode.strategypattern;

public class FlyThree implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("飞行是可以锻炼的！");
    }
}
