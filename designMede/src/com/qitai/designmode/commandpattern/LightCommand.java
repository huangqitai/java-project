package com.qitai.designmode.commandpattern;

/**
 * 控制灯泡的命令实现类
 */
public class LightCommand implements Command {
    Light light;
    LightCommand(Light light){
        this.light = light;
    }
    @Override
    public void execute() {
        light.on();
    }
}
