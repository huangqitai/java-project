package com.qitai.designmode.singletonpattern;

/**
 * 经典单例模式在多线程下保证不了对象唯一
 * 所以需要给全局访问点添加同步锁
 */
public class SynchronizationSingleton {
    private static SynchronizationSingleton synchronizationSingleton;
    private SynchronizationSingleton(){}
    //添加同步锁，所有访问方法的线程都会被阻塞，等待其他线程离开方法，下一个线程才能进入。
    public static synchronized SynchronizationSingleton getInstance(){
        if (synchronizationSingleton==null){
            synchronizationSingleton = new SynchronizationSingleton();
        }
        return synchronizationSingleton;
    }
}
