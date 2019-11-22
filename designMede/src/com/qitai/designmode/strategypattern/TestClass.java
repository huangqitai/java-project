package com.qitai.designmode.strategypattern;

import org.junit.Test;
/**
 * 策略模式定义：将算法族(行为)独立封装，独立于使用算法的对象，是各算法族之间可以相互替换。
 */
/**
 * 比如说角色格斗游戏，每个角色拥有多个武器，而同时只能使用一把武器，此时就可以设计一个角色父类，
 * 一个武器接口，使用策略模式设置不同角色拥有不同的武器，而且通过set方法可以动态变换武器
 */

/**
 * 对于子类不同的行为使用继承父类的行为方法达不到代码的复用，每个子类都需要重写自己的行为
 * 所以将行为设计为接口，不同的子类选择不同的行为实现。
 * 不会出现塑料鸭子在天上飞的行为
 */
public class TestClass {
    @Test
    public void duckTest(){
        Duck duckOne = new DuckOne();
        Duck duckTwo = new DuckTwo();

        duckOne.name();
        duckOne.doFly();
        duckOne.doQuack();

        duckTwo.name();
        duckTwo.doFly();
        duckTwo.doQuack();

        /**
         * DuckTwo是不会飞的，但是可以动态设置一个飞行实现对象，使具有飞行行为
         */
        duckTwo.setFly(new FlyThree());
        duckTwo.doFly();
    }
}
