package com.qitai.designmode.factorypattern.simplefactory;

import org.junit.Test;

/**
 * 测试，相当于制作披萨
 */
public class TestPizzaFactory {
    @Test
    public void testPizza(){
        SimplePizzaFactory simplePizzaFactory = new SimplePizzaFactory();
        Pizza pizza = simplePizzaFactory.createPizza("clam");

        pizza.one();
        pizza.two();
        pizza.three();
        pizza.four();

    }
}
