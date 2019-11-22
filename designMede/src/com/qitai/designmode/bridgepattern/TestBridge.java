package com.qitai.designmode.bridgepattern;

import org.junit.Test;

public class TestBridge {
    @Test
    public void test(){
        MessageImpl messageImpl = new MessageMobile();
        AbstractMessage message = new UrgencyMessage(messageImpl,"紧急");
        message.sendMessage("快递一号","上门取件");
    }
}
