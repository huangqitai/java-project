package com.qitai.designmode.bridgepattern;

/**
 * 手机短信发送
 */
public class MessageMobile implements MessageImpl{
    @Override
    public void sendMessage(String userName, String message) {
        System.out.println("手机短信发送,   发送给："+userName+"    ，内容："+message);
    }
}
