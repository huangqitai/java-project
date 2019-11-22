package com.qitai.designmode.singletonpattern;

/**
 * 同步锁方式可能会极大地降低程序的性能
 * 解决方法一：”急切“ 创建实例
 */
public class EagerlySingleton {
    //静态初始化，再类加载的时候就已经创建了实例,保证了单例。
    private static EagerlySingleton eagerlySingleton = new EagerlySingleton();
    private EagerlySingleton(){}
    public static EagerlySingleton getInstance(){
        return eagerlySingleton;
    }
}
