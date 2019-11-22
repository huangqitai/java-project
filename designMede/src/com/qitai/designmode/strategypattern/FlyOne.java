package com.qitai.designmode.strategypattern;

public class FlyOne implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("具有飞行行为");
    }
}
