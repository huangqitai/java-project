package com.qitai.designmode.factorypattern.abstractfactorypattern;

/**
 * 披萨店---披萨工厂的基类，可以根据不同的产品扩展不同的子类
 * 抽象工厂
 */
public abstract class PizzaStore {
    /**
     *
     * @param type 订购哪一种pizza有客户自己决定，不同的子类实现
     * @return
     */
    public Pizza orderPizza(String type){
        Pizza pizza = createPizza(type);
        pizza.one();
        pizza.two();
        pizza.three();
        pizza.four();
        return pizza;
    }
    abstract Pizza createPizza(String type);
}
