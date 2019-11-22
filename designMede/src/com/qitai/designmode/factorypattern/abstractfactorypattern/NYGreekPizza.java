package com.qitai.designmode.factorypattern.abstractfactorypattern;

public class NYGreekPizza extends Pizza{
    NYGreekPizza(){
        name = "纽约风味的GreekPizza";
    }

    @Override
    public void three() {
        System.out.println("切为正方形");
    }
}
