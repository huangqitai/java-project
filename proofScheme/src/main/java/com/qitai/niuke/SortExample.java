package com.qitai.niuke;

import org.junit.Test;

import java.util.Arrays;


public class SortExample {
    @Test
    public void t1(){
        int[] arr = {6,1,3,3,8,7,9,2,5,4,0};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    /**
     * 冒泡排序
     * 遍历数组，将元素与后面的元素进行比较，大的往后交换，小的往前交换
     * 共进行length-1轮交换完成排序
     */
    private void bubbleSort(int[] arr){
        //进行arr.length-1轮交换排序
        for (int i = 0; i < arr.length-1; i++) {
            //每一轮从首元素开始，交换至arr.length-1-i，这里-i 是因为，经过多少轮,就说明最后的几位数都是有序的且比前面的数都要大的，不用参与交换
            for (int j = 0; j < arr.length-1-i; j++) {
                //将相邻元素进行比较，前一个元素大于后一个元素则交换值
                if (arr[j]>arr[j+1]){
                    int a = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = a;
                }
            }
        }
    }

    @Test
    public void t2(){
        int[] arr = {6,1,3,3,8,7,9,2,5,4,0};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序，遍历数组，将最小值取出来放在已排序序列中，以此循环，知道数组全部在已排序序列
     * @param arr
     */
    private void selectionSort(int[] arr){
        //遍历数组
        for (int i=0;i<arr.length;i++){
            //首先使用此轮首元素为最小值
            int min = arr[i];
            //最小值所在索引下标
            int minIndex = i;
            //未排序序列中查找最小值
            for (int j = i; j < arr.length; j++) {
                if (arr[j]<min){
                    //查询到比当前最小值还要小的数值，则更新最小值以及最小值索引
                    min = arr[j];
                    minIndex = j;
                }
            }
            //将最小值放在数据的有序序列中
            int iValue = arr[i];
            arr[i] = min;
            arr[minIndex] = iValue;
        }
    }
}
