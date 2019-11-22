package com.qitai.designmode.factorypattern.abstractfactorypattern;

/**
 * 纽约披萨店
 */
public class NYPizzaStore extends PizzaStore {
    Pizza pizza;
    @Override
    Pizza createPizza(String type) {
        if (type.equals("clam")){
            pizza = new NYClamPizza();
        }else if(type.equals("greek")){
            pizza = new NYGreekPizza();
        }else return null;
        return pizza;
    }
}
