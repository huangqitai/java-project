package com.qitai.designmode.strategypattern;

public class DuckTwo extends Duck {
    DuckTwo(){
        /**
         * DuckTwo是一只玩具鸭，不会飞
         */
        fly = new FlyTwo();
        /**
         * 同时DuckTwo叫声贼难听，嘎嘎嘎
         */
        quack = new QuackOne();
    }

    @Override
    public void name() {
        System.out.println("我是DuckTwo");
    }
}
