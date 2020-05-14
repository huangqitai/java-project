package com.qitai.niuke;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class Matrix {
    int LEFT = 0;
    int RIGHT = 1;
    int DOWN = 2;
    int UP = 3;
    int[][] isExit;
    @Test
    public void Test(){
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        ArrayList<Integer> integers = printMatrix(matrix);
        for (int element : integers ) {
            System.out.print(element+"  ");
        }
    }
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        isExit = new int[matrix.length][matrix[0].length];
        ArrayList<Integer> integerList = new ArrayList<>();
        int direction = RIGHT;
        int i=0,j=0;
        boolean flag = true;
        while (flag){
            if (direction == LEFT){
                if (--j>=0&&isExit(i,j)){
                    integerList.add(matrix[i][j+1]);
                    isExit[i][j+1] = 1;
                }else {
                    direction = UP;
                    j++;
                    if (i-1<0||!isExit(i-1,j)){
                        integerList.add(matrix[i][j]);
                        flag = false;
                    }
                }
            }
            else if(direction == DOWN){
                if (++i<matrix.length&&isExit(i,j)){
                    integerList.add(matrix[i-1][j]);
                    isExit[i-1][j] = 1;
                }else {
                    direction = LEFT;
                    i--;
                    if (j-1<0||!isExit(i,j-1)){
                        integerList.add(matrix[i][j]);
                        flag = false;
                    }
                }
            }
            else if(direction == RIGHT){
                if (++j<matrix[i].length&&isExit(i,j)){
                    integerList.add(matrix[i][j-1]);
                    isExit[i][j-1] = 1;
                }else {
                    direction = DOWN;
                    j--;
                    if (i+1>=matrix.length||!isExit(i+1,j)){
                        integerList.add(matrix[i][j]);
                        flag = false;
                    }
                }
            }
            else if(direction == UP){
                if (--i>=0&&isExit(i,j)){
                    integerList.add(matrix[i+1][j]);
                    isExit[i+1][j] = 1;
                }else {
                    direction = RIGHT;
                    i++;
                    if (j+1>=matrix[i].length||!isExit(i,j+1)){
                        integerList.add(matrix[i][j]);
                        flag = false;
                    }
                }
            }
        }
        return integerList;
    }
    private boolean isExit(int i,int j){
        if (i<0||j<0||i>isExit.length-1||j>isExit[i].length-1) return false;
        if (isExit[i][j]==1) return false;
        return true;
    }
}
