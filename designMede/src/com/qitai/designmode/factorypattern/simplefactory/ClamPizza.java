package com.qitai.designmode.factorypattern.simplefactory;

public class ClamPizza implements Pizza {
    @Override
    public void one() {
        System.out.println("ClamPizza的制作步骤一");
    }

    @Override
    public void two() {
        System.out.println("ClamPizza的制作步骤二");
    }

    @Override
    public void three() {
        System.out.println("ClamPizza的制作步骤三");
    }

    @Override
    public void four() {
        System.out.println("ClamPizza的制作步骤四");
    }
}
