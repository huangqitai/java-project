package com.qitai.designmode.builderpatternnorm;

/**
 * 抽象建造者
 */
public abstract class Builder {
    Meal meal = new Meal();

    public abstract void buildFood();
    public abstract void buildDrink();

    public Meal buildMeal(){
        return meal;
    }
}
