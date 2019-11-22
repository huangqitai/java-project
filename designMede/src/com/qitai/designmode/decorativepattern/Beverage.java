package com.qitai.designmode.decorativepattern;

/**
 *饮料基类，所有的咖啡、调料都以此为基类
 */
public abstract class Beverage {
    String description = "unknown Beverage";

    public String getDescription(){
        return description;
    }
    public abstract double cost();
}
