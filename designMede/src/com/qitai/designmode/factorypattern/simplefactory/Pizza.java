package com.qitai.designmode.factorypattern.simplefactory;

/**
 * 所有披萨类的基类
 * 仅仅是让所有的披萨类有共同的类型
 */
public interface Pizza {
    /**
     *制作披萨的具体实现，子类会有自己不同的实现
     */
    public void one();
    public void two();
    public void three();
    public void four();


}
