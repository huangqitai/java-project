package com.qitai.niuke;

import org.junit.Test;

/**
 * 描述
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长的 m 段（ m 、 n 都是整数， n > 1 并且 m > 1 ， m <= n ），
 * 每段绳子的长度记为 k[1],...,k[m] 。请问 k[1]*k[2]*...*k[m] 可能的最大乘积是多少？
 * 例如，当绳子的长度是 8 时，我们把它剪成长度分别为 2、3、3 的三段，此时得到的最大乘积是 18 。
 */
public class Jianshengzi {
    @Test
    public void test(){
        System.out.println(cutRope(8));
    }
    public int cutRope(int target) {
        if (target==2){
            return 1;
        }
        if (target==3){
            return 2;
        }
        int[] flags = new int[target+1];
        return fun(target,flags);
    }
    public int fun(int n,int[] flags){
        if (n<=4){
            return n;
        }
        if (flags[n]!=0){
            return flags[n];
        }
        int maxArea = 0;
        //不关心剪成多少段，从每段减1开始穷举获取乘积
        for (int i = 1; i < n&&n-i>=1; i++) {
            maxArea = Math.max(maxArea,i*fun(n-i,flags));
        }
        flags[n] = maxArea;
        return maxArea;
    }
}
