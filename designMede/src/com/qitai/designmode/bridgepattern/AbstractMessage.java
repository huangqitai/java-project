package com.qitai.designmode.bridgepattern;

/**
 * 抽象消息类，将消息发送委托于接口
 */
public abstract class AbstractMessage {
    MessageImpl impl;
    String level;
    AbstractMessage(MessageImpl impl,String level){
        this.impl = impl;
        this.level = level;
    }
    //委托消息发送
    public void sendMessage(String userName,String message){
        System.out.println("信件紧急程度："+level);
        impl.sendMessage(userName,message);
    }
}
