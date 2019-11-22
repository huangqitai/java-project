package com.qitai.designmode.iteratorpattern;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 早餐菜单，ArrayList实现菜单集合对象
 */
public class BreakfastMenu implements Menu {
    ArrayList<MenuItem> breakfastMenu;

    BreakfastMenu(){
        this.breakfastMenu = new ArrayList<>();
        addItem(new MenuItem("豆浆","现磨豆浆",2));
        addItem(new MenuItem("小笼包","肉馅，一笼五个",5));
        addItem(new MenuItem("牛奶","早餐牛奶",4));
        addItem(new MenuItem("面包","牛奶配点儿面包",3));
    }
    public void addItem(MenuItem menuItem){
        breakfastMenu.add(menuItem);
    }
    @Override
    public Iterator getIterator() {
        return breakfastMenu.iterator();
    }
}
