package com.qitai.designmode.templatepattern;

/**
 * 游戏子类、植物大战僵尸
 */
public class Pvz extends Game{
    @Override
    void init() {
        System.out.println("一大波僵尸正在赶来...");
    }

    @Override
    void start() {
        System.out.println("种植植物抵御僵尸的入侵");
    }

    @Override
    void end() {
        System.out.println("僵尸已被消灭，正在结算...");
    }
}
