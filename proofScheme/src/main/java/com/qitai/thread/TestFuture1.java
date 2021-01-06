package com.qitai.thread;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFuture1 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int taskNum = 10000;
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Integer>> pointTaskFutureList = new ArrayList<>(taskNum);
        for (int i = 0; i < taskNum; i++) {
            // 提交任务，任务的执行由线程池去调用执行并管理。
            // 这里获取结果任务的Future，并放到list中，供所有任务提交完后，通过每个任务的Future判断执行状态和结果。
            Future<Integer> future = executor.submit(new PointTask(i + 1));
            pointTaskFutureList.add(future);
        }

        int total = 0; // 总计算结果
        int done = 0; //完成任务的数量

        while (!pointTaskFutureList.isEmpty()) {
            Iterator<Future<Integer>> iter = pointTaskFutureList.iterator();
            while (iter.hasNext()) {
                Future<Integer> next = iter.next();
                if (next.isDone()) {
                    done++;
                    Integer res = next.get();
                    total += res;
                    iter.remove();
                }
            }
            System.out.println("完成任务量：" + done + " 此时计算结果为：" + total);
            // 停留一会，避免一直循环。
            Thread.sleep(1000L);
        }
        System.out.println("执行完成后，完成任务量：" + done + " 此时计算结果为：" + total);
        executor.shutdown();
    }
}
class PointTask1 implements Callable<Integer> {

    private int customerId;

    // customerId是假设每个任务都需要设置执行的参数。
    public PointTask1(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public Integer call() throws Exception {
        //模拟任务执行时间
        Thread.sleep(new Random().nextInt(1000) + 1);
        System.out.println("计算用户积分中。。。");
        // return new Random().nextInt(10) + 1;
        // 返回1便于测试，确认任务结果。
        return 1;
    }

}
