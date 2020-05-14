package com.qitai.niuke;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
 * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 * （注意：这两个序列的长度是相等的）
 */
public class StackSolutionTwo {
    Stack<Integer> stack = new Stack<>();

    @Test
    public void Test(){
        int[] pushA = {1,2,3,4,5};
        int[] popA = {2,5,4,3,1};
        System.out.println(IsPopOrder(pushA,popA));
    }

    /**
     * 将入栈顺序数组循环入栈，入栈的同时判断当前入栈的栈顶元素是否与出栈顺序数组的首元素相等
     * 根据栈的先入后出，当当前入栈元素等于出栈顺序数组的首元素时，标识该元素应该出栈，以符合出栈顺序
     * 当满足条件的栈顶元素出栈后，出栈顺序的首元素向后移动一位，因为是数组，移动下标标识当前选中的首元素并不是数组实际意义上的首元素
     * 而是当前移动至的下标元素
     * 依次循环下去，直到入栈数组循环完毕则结束，若此时栈中元素全部出栈，即栈为空，则表示根据出栈顺序数组，该栈能够全部出栈
     * 表示该出栈顺序数组是有效的
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if(pushA.length == 0 || popA.length == 0 || pushA.length != popA.length){
            return false;
        }
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0, j = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (j < popA.length && !stack.isEmpty() && stack.peek() == popA[j]){
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    /**
     *
     * @param shu   待选择的数组
     * @param targ  要选择多少个次
     * @param cur   当前选择的是第几次
     */
    private void fun(int[] shu, int targ, int cur) {
        if(cur == targ) {
            System.out.println(stack);
            return;
        }

        for(int i=0;i<shu.length;i++) {
            if(!stack.contains(shu[i])) {
                stack.add(shu[i]);
                fun(shu, targ, cur+1);
                stack.pop();
            }
        }
    }
}
