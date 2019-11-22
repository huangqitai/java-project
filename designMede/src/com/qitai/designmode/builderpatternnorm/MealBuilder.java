package com.qitai.designmode.builderpatternnorm;

/**
 * 具体建造者
 */
public class MealBuilder extends Builder{
    @Override
    public void buildFood() {
        meal.setFood(new Food("汉堡",7.5));
    }

    @Override
    public void buildDrink() {
        meal.setDrink(new Drink("可口可乐",3));
    }
}
