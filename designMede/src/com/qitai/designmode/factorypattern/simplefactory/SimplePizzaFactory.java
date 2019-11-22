package com.qitai.designmode.factorypattern.simplefactory;

/**
 * 披萨的简单工厂
 */
public class SimplePizzaFactory {
    Pizza pizza=null;
    public Pizza createPizza(String type){
        if (type.equals("clam")){
            pizza = new ClamPizza();
        }
        if (type.equals("vaggie")){
            pizza = new VaggiePizza();
        }
        if (type.equals("greek")){
            pizza = new GreekPizza();
        }
        return pizza;
    }
}
