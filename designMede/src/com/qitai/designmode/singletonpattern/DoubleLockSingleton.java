package com.qitai.designmode.singletonpattern;

/**
 * 多线程单例解决二：双重锁
 */
public class DoubleLockSingleton {
    //volatile关键字是的变量doubleLockSingleton在多线程下，保证了不同线程对此变量的操作的可见性
    //即一个线程改变了此变量，其它线程会立即见到最新值。保证了多线程会正确处理doubleLockSingleton
    private volatile static DoubleLockSingleton doubleLockSingleton;
    private DoubleLockSingleton(){}
    public static DoubleLockSingleton getInstance(){
        if (doubleLockSingleton==null){
            //经过判断为null后“才”进入同步区块，也就是说，只有第一次访问时才同步，好了很多
            synchronized (DoubleLockSingleton.class){
                //进入之后再判断一次，保证单例
                if (doubleLockSingleton==null){
                    doubleLockSingleton = new DoubleLockSingleton();
                }
            }
        }
        return doubleLockSingleton;
    }
}
