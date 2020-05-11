package com.qitai.niuke;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * 现有一个 3x3 规格的 Android 智能手机锁屏程序和两个正整数 m 和 n ，请计算出使用最少m 个键和最多 n个键可以解锁该屏幕的所有有效模式总数。
 * 其中有效模式是指：
 * 1、每个模式必须连接至少m个键和最多n个键；
 * 2、所有的键都必须是不同的；
 * 3、如果在模式中连接两个连续键的行通过任何其他键，则其他键必须在模式中选择，不允许跳过非选择键（如图）；
 * 4、顺序相关，单键有效（这里可能跟部分手机不同）。
 *
 * 输入：m,n
 * 代表允许解锁的最少m个键和最多n个键
 * 输出：
 * 满足m和n个键数的所有有效模式的总数
 */
public class LockScreenV2 {
    //"13","19","17","31","91","71","39","37","93","73","79","97","46","64","82","28"
    static String[] invalids = {"13","46","79","17","28","39","19","37","31","64","97","71","82","93","91","73"};
    static String[] centers = {"2","5","8","4","5","6","5","5","2","5","8","4","5","6","5","5"};
    static int[] numbers = {1,2,3,4,5,6,7,8,9};
    static int total = 0;
    static Stack<Integer> stacks = new Stack<>();
    static StringBuffer effective = new StringBuffer();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String in = scanner.next();
        String[] ins = in.split(",");
        int min = Integer.parseInt(ins[0]);
        int max = Integer.parseInt(ins[1]);
        solution(min,max);
        System.out.println(total);
    }

    @Test
    public void Test(){
        System.out.println(solution(3,3));
    }
    private static boolean effection(String effective){
        boolean flag=true;
        for (int i=0;i<invalids.length;i++) {
            if (effective.contains(invalids[i])) {
                String pre = effective.substring(0, effective.indexOf(invalids[i]));
                if (!pre.contains(centers[i])) {
                    return false;
                }
            }
        }
        return flag;
    }
    public static int solution(int m,int n){
        for (int i=m;i<=n;i++){
            if(i==0){total+=0;}
            else if(i==1){total+=9;}
            else fun(i,0);
        }
        return total;
    }
    private static void fun(int count,int index){
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
