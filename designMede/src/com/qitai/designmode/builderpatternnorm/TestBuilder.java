package com.qitai.designmode.builderpatternnorm;

import org.junit.Test;

public class TestBuilder {
    @Test
    public void test(){
        Builder builder = new MealBuilder();
        Director director = new Director(builder);
        Meal meal = director.construct();
        System.out.println(meal.toString());
    }
}
