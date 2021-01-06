package com.qitai.thread;

import org.junit.Test;

import java.util.Map;

public class ThreadTest {

    @Test
    public void test1(){
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {

        }
        for(Thread thread : threads) {
            thread.start();
        }
    }
}
