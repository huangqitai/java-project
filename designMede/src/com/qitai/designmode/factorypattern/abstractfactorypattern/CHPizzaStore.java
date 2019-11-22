package com.qitai.designmode.factorypattern.abstractfactorypattern;

public class CHPizzaStore extends PizzaStore {
    Pizza pizza;
    @Override
    Pizza createPizza(String type) {
        if (type.equals("clam")){
            pizza = new CHClamPizza();
        }
        else if(type.equals("greek")){
            pizza = new CHGreekPizza();
        }else return null;
        return pizza;
    }
}
