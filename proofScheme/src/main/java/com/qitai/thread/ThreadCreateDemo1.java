package com.qitai.thread;

public class ThreadCreateDemo1 {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start(); //该方法调用多次,出现IllegalThreadStateException


    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i=0;i<10;i++){
            System.out.println("hellow_world!"+i+1);
        }
    }
}
