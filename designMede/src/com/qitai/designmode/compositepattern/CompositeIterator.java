package com.qitai.designmode.compositepattern;

import java.util.Iterator;
import java.util.Stack;

/**
 * 组合迭代器，使用递归
 */
public class CompositeIterator implements Iterator {
    //使用栈来存储组合树形结构中的所有集合的迭代器
    Stack<Iterator> stack = new Stack();
    CompositeIterator(Iterator iterator){
        stack.push(iterator);
    }

    @Override
    public boolean hasNext() {
        if (stack.empty()){
            return false;
        }else {
            Iterator iterator = stack.peek();
            if (!iterator.hasNext()){
                stack.pop();
                return hasNext();
            }else {
                return true;
            }
        }
    }

    @Override
    public Object next() {
        if (hasNext()){
            Iterator iterator = stack.peek();
            Employee employee = (Employee)iterator.next();
            if (employee.getEmpList()!=null){
                stack.push(employee.getEmpList().iterator());
            }
            return employee;
        }
        return null;
    }
}
