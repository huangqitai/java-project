package com.qitai.designmode.iteratorpattern;

import java.util.Iterator;

public class LunchMenu implements Menu {
    static final int MAX_ITEMS=4;
    int index = 0;
    MenuItem[] menuItems;

    LunchMenu(){
        menuItems = new MenuItem[MAX_ITEMS];
        addItem(new MenuItem("鱼香肉丝","午餐，配米饭",14));
        addItem(new MenuItem("家常豆腐","午餐，配米饭",13));
        addItem(new MenuItem("木桶饭","午餐",15));
        addItem(new MenuItem("香菇排骨","午餐，配米饭",16));
    }
    public void addItem(MenuItem menuItem){
        if (index>=MAX_ITEMS){
            System.out.println("菜单超过最大限制");
        }
        else {
            menuItems[index] = menuItem;
            index++;
        }
    }
    @Override
    public Iterator getIterator() {
        return new MenuIterator(menuItems);
    }
}
