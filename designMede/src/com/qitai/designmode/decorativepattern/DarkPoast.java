package com.qitai.designmode.decorativepattern;

/**
 * 深焙咖啡，必须实现计算账单cost()方法。饮料的实现类，等待调料装饰
 */
public class DarkPoast extends Beverage{
    DarkPoast(){
        description = "Dark Poast";
    }
    @Override
    public double cost() {
        return 1.2;
    }
}
