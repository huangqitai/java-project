package com.qitai.designmode.builderpatternnorm;

/**
 * 用于作为食物对象
 */
public class Food {
    private String name;
    private double price;

    Food(String name,double price){
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
