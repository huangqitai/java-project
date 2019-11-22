package com.qitai.designmode.commandpattern;

import org.junit.Test;

/**
 * 测试类
 */
public class TestCommand {
    @Test
    public void test(){
        RemoteControl remoteControl = new RemoteControl();
        Light light = new Light();
        Command command = new LightCommand(light);
        remoteControl.setCommand(command);
        remoteControl.buttonPressed();

        AirConditioner airConditioner = new AirConditioner();
        Command command1 = new AirConditionerCommand(airConditioner);
        remoteControl.setCommand(command1);
        remoteControl.buttonPressed();
    }
}
