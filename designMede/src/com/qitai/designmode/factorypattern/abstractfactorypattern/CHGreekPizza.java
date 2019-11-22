package com.qitai.designmode.factorypattern.abstractfactorypattern;

public class CHGreekPizza extends Pizza {
    CHGreekPizza(){
       name = "中国风味的ClamPizza";
    }

    @Override
    public void three() {
        System.out.println("切块为正三角");
    }
}
