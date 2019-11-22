package com.qitai.designmode.mediatorpattern;

/**
 * 消息中介
 */
public class MessageMediator {
    /**
     * 中介者，封装了对象之间的交互，在此处为封装了用户发送消息给指定用户的行为转发
     *
     * @param sendUser
     * @param message
     * @param receiveUser
     */
    public static void forwardMessage(User sendUser,String message,User receiveUser){
        receiveUser.receive(sendUser,message);
    }
}
