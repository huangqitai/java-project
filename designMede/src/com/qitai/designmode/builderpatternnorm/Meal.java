package com.qitai.designmode.builderpatternnorm;

/**
 * 以肯德基套餐为例，套餐即为产品
 */
public class Meal {
    //这里的组成部分可以是任意类型
    private Food food;
    private Drink drink;

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    @Override
    public String toString() {
        return "套餐：主食："+food.getName()+",   价格："+food.getPrice()+",     饮料："+drink.getName()+",     价格："+drink.getPrice()+"" +
                ",      总价："+(food.getPrice()+drink.getPrice());
    }
}
