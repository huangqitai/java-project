package com.qitai.designmode.factorypattern.abstractfactorypattern;

public class CHClamPizza extends Pizza {
    CHClamPizza(){
        name = "中国风味的ClamPizza";
    }

    @Override
    public void two() {
        System.out.println("均匀烘烤半小时");
    }
}
