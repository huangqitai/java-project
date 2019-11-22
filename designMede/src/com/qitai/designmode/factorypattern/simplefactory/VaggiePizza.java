package com.qitai.designmode.factorypattern.simplefactory;

public class VaggiePizza implements Pizza {
    @Override
    public void one() {
        System.out.println("VaggiePizza的制作步骤一");
    }

    @Override
    public void two() {
        System.out.println("VaggiePizza的制作步骤二");
    }

    @Override
    public void three() {
        System.out.println("VaggiePizza的制作步骤三");
    }

    @Override
    public void four() {
        System.out.println("VaggiePizza的制作步骤四");
    }
}
