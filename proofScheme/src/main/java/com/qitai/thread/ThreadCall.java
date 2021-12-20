package com.qitai.thread;
import com.qitai.utils.CheckUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadCall {

    private int i=0;
    @Test
    public void test1() throws InterruptedException {
        System.out.println(sout());
        System.out.println(sout());
    }
    @Test
    public void demo(){

        List<String> list = Arrays.asList("22","222");
        //获取系统处理器个数，作为线程池数量
        int nThreads = Runtime.getRuntime().availableProcessors();

        ExecutorService exc = new ThreadPoolExecutor(1, 200,1, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(100), new ThreadPoolExecutor.AbortPolicy());
        //申明线程池
        //ExecutorService exc = new ThreadPoolExecutor();
        List<Future<String>> futures = null;
        List<ThreadCall1> checkThreadHandleRequests = new ArrayList<>();
        for (int i=0;i<50;i++) {
            try {
                //申请单个线程执行类
                ThreadCall1 checkThreadHandleRequest = new ThreadCall1(this);
                //放到集合中，直接调用
                checkThreadHandleRequests.add(checkThreadHandleRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            //启动线程
            futures = exc.invokeAll(checkThreadHandleRequests);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (futures == null){
            throw new RuntimeException("");
        }
        for (Future<String> future : futures) {
            try {
                //处理返回结果
                String content = future.get();
                if (CheckUtil.isNullorEmpty(content)) {
                    continue;
                }
                System.out.println(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int sout() throws InterruptedException {
        Thread.sleep(100);
        return ++i;
    }
}
