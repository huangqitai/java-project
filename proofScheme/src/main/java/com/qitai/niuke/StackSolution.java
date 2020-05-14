package com.qitai.niuke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 * 注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法。
 */
public class StackSolution {
    Stack<Integer> stack = new Stack();
    ArrayList<Integer> arrayList = new ArrayList();
    int min;
    public void push(int node) {
        arrayList.add(node);
        Collections.sort(arrayList);
        min = arrayList.get(0);
        stack.push(node);
    }

    public void pop() {
        if (stack.empty()) return;
        int element = stack.pop();
        for (int i=0;i<arrayList.size();i++){
            if (arrayList.get(i)==element){
                arrayList.remove(i);
                break;
            }
        }
        if (arrayList.size()>0){
            Collections.sort(arrayList);
            min = arrayList.get(0);
        }
    }

    public int top() {
        if (!stack.empty()) return stack.peek();
        return 0;
    }

    public int min() {
        if (arrayList.size()<=0||stack.empty()) return 0;
        return min;
    }
}
