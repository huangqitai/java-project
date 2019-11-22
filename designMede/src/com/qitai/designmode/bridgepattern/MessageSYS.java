package com.qitai.designmode.bridgepattern;

/**
 * 系统内部发送
 */
public class MessageSYS implements MessageImpl{
    @Override
    public void sendMessage(String userName, String message) {
        System.out.println("系统发送,   发送给："+userName+"    ，内容："+message);
    }
}
