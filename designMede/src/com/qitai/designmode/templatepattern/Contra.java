package com.qitai.designmode.templatepattern;

/**
 * 游戏子类、魂斗罗
 */
public class Contra extends Game {
    @Override
    void init() {
        System.out.println("魂斗罗正在初始化...");
    }

    @Override
    void start() {
        System.out.println("选择关卡，点击确定进入");
    }

    @Override
    void end() {
        System.out.println("你已死亡,正在退出...");
    }

    /**
     * 子类重写钩子方法，表示不需要显示游戏时间
     * @return
     */
    @Override
    public boolean show() {
        return false;
    }
}
