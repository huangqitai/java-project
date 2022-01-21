package com.qitai.niuke;

import org.junit.Test;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
    @Test
    public void t1(){
        int[] arr = {6,1,3,3,8,7,9,2,5,4,0};
        //排序开始，起始下标0  结束下标为数组长度减1
        fun(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    /**
     * 1、获取数组第一个值作为基准值
     * 2、先从数组结束下标开始，向前遍历数组，当查找到小于基准值的数值，就将查找到的值赋给 开始下标所在的位
     * 3、然后从开始下标开始，向后遍历数组，当查找到大于基准值的数值，就将查找到的值赋予结束下标所在的位
     * 4、如此切换交替遍历查找，当开始下标和结束下标相等时结束这一回合
     * 5、以当前相等的下标为分界线，下标左边的分为左边界数组，右边（包含或者不包含当前坐标都可以）的分为右边界数组
     * 6、左右边界数组继续执行以上步骤（递归）
     * 7、递归结束条件为：开始下标等于结束坐标（说明左右边界数组已经遍历完了）
     */
    /**
     * 排序递归方法
     *
     * @param sortArr 待排序数组
     * @param startIndex 开始下标
     * @param endIndex 结束下标
     */
    private void fun(int[] sortArr,int startIndex,int endIndex){
        //startIndex=endIndex 说明左边界与右边界重合了，说明左右边界数组已经只有一个数值，不需要排序
        if (startIndex<endIndex){
            //记录下起始下标
            int leftStart = startIndex;
            //取边界开始的值作为基准比较值
            int key = sortArr[startIndex];
            //startIndex<endIndex 说明一趟还未结束，循环查找交换数值
            while (startIndex<endIndex){
                //从结束下标开始向前遍历，直到当前查找的数值小于基准值
                while (sortArr[endIndex]>key&&startIndex<endIndex){
                    endIndex--;
                }
                //跳出了上面的循环，情况1：开始下标等于了结束下标，此时说明此当前基准值小于开始下标后面的所有值，不需要交换，跳出此趟排序
                if (startIndex>=endIndex){
                    break;
                }
                //情况2：查找到了小于基准值的数值，将查找到的数值赋值给开始下标指向的元素，做一次交换
                sortArr[startIndex] = sortArr[endIndex];
                //元素交换后起始坐标向右
                startIndex++;
                //从开始下标开始向后遍历，直到当前查找的数值小于基准值
                while (sortArr[startIndex]<key&&startIndex<endIndex){
                    startIndex++;
                }
                //跳出了上面的循环，情况1：开始下标等于了结束下标，此时说明此当前基准值大于结束下标前面的所有值，不需要交换，跳出此趟排序
                if (startIndex>=endIndex){
                    break;
                }
                //情况2：查找到了大于基准值的数值，将查找到的数值赋值给结束下标指向的元素，做一次交换
                sortArr[endIndex] = sortArr[startIndex];
                //结束坐标向左移动
                endIndex--;
            }
            //一轮结束，将基准值赋值给当前下标
            sortArr[startIndex] = key;
            //左边界数组继续进行查找交换
            fun(sortArr,leftStart,startIndex-1);
            //右边界数组继续进行查找交换
            fun(sortArr,startIndex+1,sortArr.length-1);
        }
    }
}
