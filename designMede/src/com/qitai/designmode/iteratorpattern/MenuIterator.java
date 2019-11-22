package com.qitai.designmode.iteratorpattern;

import java.util.Iterator;

public class MenuIterator implements Iterator {
    MenuItem[] menuItems;
    int index = 0;

    MenuIterator(MenuItem[] menuItems){
        this.menuItems = menuItems;
    }
    @Override
    public boolean hasNext() {
        return index < menuItems.length&&menuItems[index]!=null ? true : false;
    }

    @Override
    public Object next() {
        MenuItem menuItem = menuItems[index];
        index ++ ;
        return menuItem;
    }
}
