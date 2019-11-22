package com.qitai.designmode.bridgepattern;

/**
 * 特急消息
 */
public class VeryUrgentMessage extends AbstractMessage{
    VeryUrgentMessage(MessageImpl impl,String level){
        super(impl,level);
    }
}
