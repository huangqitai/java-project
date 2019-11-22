package com.qitai.designmode.iteratorpattern;

import org.junit.Test;

import java.util.Iterator;

public class TestIterator {
    @Test
    public void test(){
        BreakfastMenu breakfastMenu = new BreakfastMenu();
        LunchMenu lunchMenu = new LunchMenu();

        Iterator iterator1 = breakfastMenu.getIterator();
        while (iterator1.hasNext()){
            System.out.println(iterator1.next().toString());
        }
        Iterator iterator2 = lunchMenu.getIterator();
        while (iterator2.hasNext()){
            System.out.println(iterator2.next().toString());
        }
    }
}
