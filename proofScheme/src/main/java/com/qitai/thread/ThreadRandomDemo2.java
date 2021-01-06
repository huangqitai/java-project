package com.qitai.thread;

import cn.hutool.json.JSONUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreadRandomDemo2 {
    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strs.add("strThread:" + i);
        }
        Map<String,List<String>> stringListMap = new HashMap<>();
        stringListMap.put("1",strs.subList(0,5));
        stringListMap.put("2",strs.subList(5,10));
        stringListMap.put("3",strs.subList(10,15));
        stringListMap.put("4",strs.subList(15,20));
        RandomThread[] threads = new RandomThread[4];
        for (int i = 0; i < 4; i++) {
            threads[i] = new RandomThread(stringListMap.get(i+1+""));
        }
        for(RandomThread thread : threads) {
            thread.start();
        }
        for(RandomThread thread : threads) {
            System.out.println(thread.getStrings().toString()+"********");
        }
    }
}

class RandomThread extends Thread {
    private List<String> strings;
    public RandomThread(List<String> strings) {
        this.strings = strings;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(JSONUtil.toJsonStr(this.strings));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
