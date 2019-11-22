package com.qitai.designmode.chainofresponsibilitypattern;

import org.junit.Test;

/**
 * 假如规定学生请假小于或等于 2 天，班主任可以批准；小于或等于 7 天，系主任可以批准；
 * 小于或等于 10 天，院长可以批准；其他情况不予批准；这个实例适合使用职责链模式实现。
 */
public class TestCORB {
    @Test
    public void test(){
        RequestDomain requestDomain = new RequestDomain();
        requestDomain.setName("小明");
        requestDomain.setTitle("请假");
        requestDomain.setDay(8);

        //组装责任链
        Leader leader = new Teacher();
        Leader leader1 = new Director();
        Leader leader2  = new Dean();
        leader.setNext(leader1);
        leader1.setNext(leader2);
        leader.handleRequest(requestDomain);
    }
}
