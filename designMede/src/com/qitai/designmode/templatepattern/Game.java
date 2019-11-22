package com.qitai.designmode.templatepattern;

/**
 * 抽象基类，定义了模板方法，提供给子类实现的步骤
 */
public abstract class Game {
    /**
     * 模板方法，
     */
    public final void play(){
        init();
        System.out.println("初始化之后可能还有固定步骤，不应该被子类自己实现");
        start();
        System.out.println("游戏开始之后可能还有固定步骤，不应该被子类自己实现");
        end();

        if (show()){
            System.out.println("显示游戏时间");
        }

    }
    abstract void init();
    abstract void start();
    abstract void end();

    /**
     * 钩子方法，默认为true，即执行 “显示游戏时间”这段算法
     * 子类可以重写方法自己控制，也可以不，就使用默认显示
     * @return
     */
    public boolean show(){
        return true;
    }
}
