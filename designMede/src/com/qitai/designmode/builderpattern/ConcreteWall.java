package com.qitai.designmode.builderpattern;

public class ConcreteWall extends Wall{
    @Override
    public String name() {
        return "混凝土墙";
    }

    @Override
    public Materials Kind() {
        return new Concrete();
    }

    @Override
    public double budget() {
        return 180000;
    }
}
