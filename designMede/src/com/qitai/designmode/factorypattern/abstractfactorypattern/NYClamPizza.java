package com.qitai.designmode.factorypattern.abstractfactorypattern;

/**
 * 纽约风味的ClamPizza
 */
public class NYClamPizza extends Pizza{
    NYClamPizza(){
        name = "纽约风味的ClamPizza";
    }

    /**
     * 覆盖了父类的步骤一
     */
    @Override
    public void two() {
        System.out.println("烘烤半小时");
    }
}
