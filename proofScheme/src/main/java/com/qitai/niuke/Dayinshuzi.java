package com.qitai.niuke;

import org.junit.Test;

/**
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 * 1. 用返回一个整数列表来代替打印
 * 2. n 为正整数，0 < n <= 5
 */
public class Dayinshuzi {
    @Test
    public void test(){
        printNumbers(5);
    }
    public int[] printNumbers (int n) {
        if (n<=0){
            return null;
        }
        int maxNumber = (int) Math.pow(10,n);
        maxNumber--;
        int[] numbers = new int[maxNumber];
        for (int i = 0; i < maxNumber; i++) {
            numbers[i] = i+1;
        }
        return numbers;
    }
}
