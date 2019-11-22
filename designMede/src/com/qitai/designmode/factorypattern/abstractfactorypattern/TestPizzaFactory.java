package com.qitai.designmode.factorypattern.abstractfactorypattern;

import org.junit.Test;

public class TestPizzaFactory {

    @Test
    public void testPizza(){
        /**
         * 由工厂、类型决定创建的子类是哪一个类
         */
        PizzaStore pizzaStore = new NYPizzaStore();
        pizzaStore.orderPizza("clam");
        pizzaStore.orderPizza("greek");

        PizzaStore pizzaStore1 = new CHPizzaStore();
        pizzaStore1.orderPizza("clam");
        pizzaStore1.orderPizza("greek");
    }
}
