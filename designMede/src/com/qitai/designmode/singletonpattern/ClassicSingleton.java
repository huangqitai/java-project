package com.qitai.designmode.singletonpattern;

/**
 * 经典单例模式
 */
public class ClassicSingleton {
    private static ClassicSingleton classicSingleton;

    /**
     * 私有化构造器
     */
    private ClassicSingleton(){

    }

    /**
     * 开放获取实例对象的方法
     * @return
     */
    public static ClassicSingleton getInstance(){
        /**
         * 第一次访问时创建对象。
         */
        if (classicSingleton==null){
            classicSingleton = new ClassicSingleton();
        }
        return classicSingleton;
    }
}
