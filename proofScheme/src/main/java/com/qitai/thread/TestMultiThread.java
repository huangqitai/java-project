package com.qitai.thread;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMultiThread {

    public static void main(String[] args) {
        try {
            List<List<Map>> threadList = new ArrayList<List<Map>>();//线程数组，size就是线程数量
            for(int j=0;j<10;j++) {
                List<Map> oneThreadDealData = new ArrayList<>();//一个线程需要处理的数量
                for (int i = 0; i < 5; i++) {
                    oneThreadDealData.add(new HashMap());
                }
                threadList.add(oneThreadDealData);
            }


            MultiThread<Map,Map> multiThread = new MultiThread<Map,Map>(threadList){
                @Override
                public List<Map> outExecute(int currentThread, List<Map> data) {
                    List<Map> list = new ArrayList<>();
                    for(Map vo:data) {
                        System.out.println("当前线程号=" + currentThread + " data=" + data);
                        list.add(new HashMap());
                    }
                    return list;
                }
            };
            List<Map> list = multiThread.getResult();

            //获取每一批次处理结果
            System.out.println("获取处理结果........................");
            for(Map vo:list){
                //System.out.println(vo);
            }
            System.out.println(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}