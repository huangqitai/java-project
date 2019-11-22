package com.qitai.designmode.bridgepattern;

/**
 * 邮件发送
 */
public class MessagaeEmail implements MessageImpl{
    @Override
    public void sendMessage(String userName, String message) {
        System.out.println("邮箱发送,   发送给："+userName+"    ，内容："+message);
    }
}
