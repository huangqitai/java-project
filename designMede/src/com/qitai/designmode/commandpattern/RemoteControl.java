package com.qitai.designmode.commandpattern;

/**
 * 遥控器
 */
public class RemoteControl {
    Command command;
    public RemoteControl(){

    }
    public void setCommand(Command command){
        this.command = command;
    }

    //按钮按下
    public void buttonPressed(){
        command.execute();
    }
}
