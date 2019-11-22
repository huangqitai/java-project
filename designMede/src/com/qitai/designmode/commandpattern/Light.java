package com.qitai.designmode.commandpattern;

/**
 * 被控制的对象实体类
 */
public class Light {
    public void on(){
        System.out.println("打开灯泡开关");
    }
    public void off(){
        System.out.println("关闭灯泡开关");
    }
}
