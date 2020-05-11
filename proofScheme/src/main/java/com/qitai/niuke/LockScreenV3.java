package com.qitai.niuke;

import org.junit.Test;

import java.util.Scanner;
import java.util.Stack;

public class LockScreenV3 {
    //"13","19","17","31","91","71","39","37","93","73","79","97","46","64","82","28"
     String[] invalids = {"13","46","79","17","28","39","19","37","31","64","97","71","82","93","91","73"};
     String[] centers = {"2","5","8","4","5","6","5","5","2","5","8","4","5","6","5","5"};
     int[] numbers = {1,2,3,4,5,6,7,8,9};
     int total = 0;
     Stack<Integer> stacks = new Stack<>();
     StringBuffer effective = new StringBuffer();
    @Test
    public void Test(){
        System.out.println(solution(3,3));
    }
    public int solution(int m,int n){
        if (m<n) return 0;
        else if (m>9) return 0;
        else if (n>9) n=9;
        for (int i=m;i<=n;i++){
            if(i==0){total+=0;}
            else if(i==1){total+=9;}
            else fun(i,0);
        }
        return total;
    }
    private void fun(int count,int index){
        if(index == count) {
            effective.delete(0,effective.length());
            for (int i=0;i<stacks.size();i++){
                effective.append(stacks.get(i));
            }
            for (int i=0;i<invalids.length;i++) {
                if (effective.toString().contains(invalids[i])) {
                    if (!effective.substring(0, effective.indexOf(invalids[i])).contains(centers[i])) {
                        return;
                    }
                }
            }
            total++;
            return;
        }

        for(int i=0;i<numbers.length;i++) {
            if(!stacks.contains(numbers[i])) {
                stacks.add(numbers[i]);
                fun(count, index+1);
                stacks.pop();
            }
        }
    }
}
