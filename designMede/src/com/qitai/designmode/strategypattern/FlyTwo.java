package com.qitai.designmode.strategypattern;

public class FlyTwo implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("玩具鸭不会飞");
    }
}
