package com.qitai.designmode.decorativepattern;

public class Mocha extends CondimentDecorator {
    /**
     * 依赖同一类型的对象，某一种饮料或者装饰了饮料的调料示例。
     */
    Beverage beverage;
    Mocha(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+",Mocha";
    }

    /**
     * 装饰的对象单价加上当前调料的单价，返回总价格。
     * @return
     */
    @Override
    public double cost() {
        return beverage.cost()+1.5;
    }
}
