package com.qitai.designmode.bridgepattern;

/**
 * 紧急消息
 */
public class UrgencyMessage extends AbstractMessage{
    UrgencyMessage(MessageImpl impl,String level){
        super(impl,level);
    }
}
