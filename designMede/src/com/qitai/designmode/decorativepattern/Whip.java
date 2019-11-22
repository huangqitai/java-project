package com.qitai.designmode.decorativepattern;

public class Whip extends CondimentDecorator {
    Beverage beverage;

    Whip(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+",Whip";
    }

    @Override
    public double cost() {
        return beverage.cost()+ 2.0;
    }
}
