package com.qitai.designmode.mediatorpattern;

import org.junit.Test;

public class MediatorTest {
    @Test
    public void test(){
        User user3 = new User("张三");
        User user1 = new User("张一");
        User user2 = new User("张二");
        User user4 = new User("张四");

        user1.sendMessage("你好啊",user2);
    }
}
