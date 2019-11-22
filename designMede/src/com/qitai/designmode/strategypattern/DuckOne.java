package com.qitai.designmode.strategypattern;

public class DuckOne extends Duck {
    DuckOne(){
        /**
         * DuckOne是一直会飞的鸭子，选择具有飞行行为的实现类
         */
        fly = new FlyOne();
        /**
         * DuckOne不但会飞而且会唱歌
         */
        quack = new QuackTwo();
    }

    @Override
    public void name() {
        System.out.println("我是DuckOne");
    }
}
