package com.qitai.designmode.bridgepattern;

/**
 * 普通消息
 */
public class CommonMessage extends AbstractMessage{
    CommonMessage(MessageImpl impl,String level){
        super(impl,level);
    }
}
