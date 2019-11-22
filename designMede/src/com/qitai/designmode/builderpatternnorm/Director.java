package com.qitai.designmode.builderpatternnorm;

/**
 * 指挥者
 */
public class Director {
    private Builder builder;

    public Director(Builder builder){
        this.builder = builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public Meal construct(){
        builder.buildFood();
        builder.buildDrink();
        return builder.buildMeal();
    }
}
