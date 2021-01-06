package com.qitai.thread;

import java.util.Map;
import java.util.concurrent.Callable;

public class ThreadCall1 implements Callable<String> {

    private ThreadCall threadCall;

    public ThreadCall1(ThreadCall threadCall ) {
        this.threadCall = threadCall;
    }
    @Override
    public String call() throws Exception {
        return threadCall.sout()+"";
    }
}
