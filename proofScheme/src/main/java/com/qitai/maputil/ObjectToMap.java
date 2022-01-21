package com.qitai.maputil;

import org.junit.Test;

import java.util.Map;

public class ObjectToMap {
    @Test
    public void t1() throws Exception {
        MailInfo mailInfo = new MailInfo();
        ReceiverInfo receiverInfo = new ReceiverInfo();
        SenderInfo senderInfo = new SenderInfo();
        mailInfo.setClerkCard("clerkCard");
        mailInfo.setClerkNum("clerkNum");
        mailInfo.setCount(2);
        receiverInfo.setDetailAddress("xxx省xxx市xxx区");
        receiverInfo.setPersonName("personName");
        receiverInfo.setPersonPhone("personPhone");
        senderInfo.setDetailAddress("xxx省xxx市xxx区");
        senderInfo.setPersonName("personName");
        senderInfo.setPersonPhone("personPhone");
        mailInfo.setReceiverInfo(receiverInfo);
        mailInfo.setSenderInfo(senderInfo);
        Map<String,Object> objectMap = ObjectToMapUtil.objectToMap(mailInfo);
        MailInfo mailInfo1 = ObjectToMapUtil.mapToObject(MailInfo.class,objectMap);
        System.out.println(mailInfo1);
    }
}
