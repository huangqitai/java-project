package com.qitai.designmode.decorativepattern;

import org.junit.Test;

public class DecorativeTest {

    @Test
    public void decorativeTest(){
        /**
         * 定一杯深焙咖啡
         */
        Beverage darkPoast = new DarkPoast();
        /**
         * 以摩卡和奶泡装饰咖啡
         */
        darkPoast = new Mocha(darkPoast);
        darkPoast = new Whip(darkPoast);


        System.out.println("订单："+darkPoast.getDescription()+",  账单："+darkPoast.cost());
    }
}
