package com.qitai.designmode.factorypattern.simplefactory;

/**
 * 披萨的其中一种实例，希腊披萨
 */
public class GreekPizza implements Pizza{
    @Override
    public void one() {
        System.out.println("GreekPizza的制作步骤一");
    }

    @Override
    public void two() {
        System.out.println("GreekPizza的制作步骤二");
    }

    @Override
    public void three() {
        System.out.println("GreekPizza的制作步骤三");
    }

    @Override
    public void four() {
        System.out.println("GreekPizza的制作步骤四");
    }
}
