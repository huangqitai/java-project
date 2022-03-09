package com.qitai.niuke;

import org.junit.Test;

/**
 * 地上有一个 rows 行和 cols 列的方格。坐标从 [0,0] 到 [rows-1,cols-1] 。
 * 一个机器人从坐标 [0,0] 的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于 threshold 的格子。
 * 例如，当 threshold 为 18 时，机器人能够进入方格   [35,37] ，因为 3+5+3+7 = 18。
 * 但是，它不能进入方格 [35,38] ，因为 3+5+3+8 = 19 。请问该机器人能够达到多少个格子？
 */
public class Jiqirenguiji {
    int count = 0;
    @Test
    public void test(){
        movingCount(10,1,100);
        System.out.println(count);
    }
    public int movingCount(int threshold, int rows, int cols) {
        int[][] flags = new int[rows][cols];
        fun(0,0,flags,threshold);
        return count;
    }
    public void fun(int i,int j,int[][] flags,int threshold){
        String iStr = String.valueOf(i);
        String jStr = String.valueOf(j);
        int num = 0;
        for (int k = 0; k < iStr.length(); k++) {
            num += Integer.parseInt(String.valueOf(iStr.charAt(k)));
        }
        for (int k = 0; k < jStr.length(); k++) {
            num += Integer.parseInt(String.valueOf(jStr.charAt(k)));
        }
        //System.out.println(num);

        flags[i][j] = 1;
        if (num<=threshold){
            System.out.println(i+","+j);
            count++;
            if (i-1>=0&&flags[i-1][j] != 1){
                fun(i-1,j,flags,threshold);
            }if (i+1< flags.length&&flags[i+1][j] != 1){
                fun(i+1,j,flags,threshold);
            }if (j-1>=0&&flags[i][j-1] != 1){
                fun(i,j-1,flags,threshold);
            }if (j+1<flags[i].length&&flags[i][j+1] != 1){
                fun(i,j+1,flags,threshold);
            }
        }
    }
}
