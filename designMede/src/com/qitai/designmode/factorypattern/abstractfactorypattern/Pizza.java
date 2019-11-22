package com.qitai.designmode.factorypattern.abstractfactorypattern;

/**
 * 工厂制作的产品基类，此例中为披萨
 */
public abstract class Pizza {
    String name;
    /**
     *制作披萨的步骤，子类可以有自己不同的实现
     */
    public void one(){
        System.out.println(name+"的制作");
    }
    public void two(){
        System.out.println("烘烤");
    }
    public void three(){
        System.out.println("切块");
    }
    public void four(){
        System.out.println("包装");
    }
}
