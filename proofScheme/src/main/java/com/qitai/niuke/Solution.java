package com.qitai.niuke;

import org.junit.Test;

import java.util.Random;
import java.util.Stack;


public class Solution {
    /**
     * 两个栈实现队列
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        while(!stack2.empty()){
            stack1.push(stack2.pop());
        }
        stack1.push(node);
        while (!stack1.empty()){
            stack2.push(stack1.pop());
        }
    }

    public int pop() {
        return stack2.pop();
    }

    /**
     * 旋转数组
     * @param array {3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * @return
     */
    public int minNumberInRotateArray(int [] array) {
        if (array.length==0) return 0;
        int min=array[0];
        for (int i=0;i<array.length;i++){
             min = array[i]>min?min:array[i];
        }
        return min;
    }
    @Test
    public void testarr(){
        int [] array = {3,4,5,1,2};
        System.out.println(minNumberInRotateArray(array));
    }


    @Test
    public void JumpFloor() {
    /**
     * 正数、负数转二进制，其中负数是其补码标识
     */
        String binary5 = Integer.toBinaryString(160);
        int len1 = binary5.length();
        String newstring1 = binary5.replaceAll("1","");
        int newlen1 = newstring1.length();
        System.out.println(len1-newlen1);

    }

    public double Power(double base, int exponent) {
        if(base==0&&exponent==0) return 0;
        if(base==0){
            return 0;
        }
        if(exponent==0){
            return 0;
        }

        if(exponent==1){
            return base;
        }
        return Power(base,exponent-1)*base;
    }

    @Test
    public void testPower(){
        //System.out.println(Power(2.2,5));

        int [] od = new int[10];
        System.out.println(od.length);
    }


    public void reOrderArray(int [] array) {
        int [] odd;
        int [] even;
        int oddlen = 0;
        int evenlen = 0;

        for(int i=0;i<array.length;i++){
            if(array[i]%2!=0){
                oddlen++;
            }else {
                evenlen++;
            }
        }
        odd = new int[oddlen];
        even = new int[evenlen];

        oddlen = 0;
        evenlen = 0;
        for(int i=0;i<array.length;i++){
            if(array[i]%2!=0){
                odd[oddlen] = array[i];
                oddlen++;
            }else {
                even[evenlen] = array[i];
                evenlen++;
            }
        }

        for (int i=0;i<odd.length;i++){
            array[i] = odd[i];
        }
        for (int i=odd.length;i<(odd.length)+(even.length);i++){
            array[i] = even[i-(odd.length)];
        }

        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+"     ");
        }

    }

    @Test
    public void testArr(){
        int [] arr = {1,2,3,4,5,6,7};
        reOrderArray(arr);
    }
}