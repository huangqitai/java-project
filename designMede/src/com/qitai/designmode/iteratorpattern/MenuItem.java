package com.qitai.designmode.iteratorpattern;

/**
 * 菜单项实体
 */
public class MenuItem {
    private String name;
    private String description;
    private double price;

    MenuItem(String name,String description,double price){
        this.name = name;
        this.description = description;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name+",  "+description+",    "+price+"元";
    }
}
