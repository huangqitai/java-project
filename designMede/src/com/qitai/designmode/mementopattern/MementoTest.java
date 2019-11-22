package com.qitai.designmode.mementopattern;

import org.junit.Test;

public class MementoTest {
    @Test
    public void test(){
        Originator originator = new Originator();
        //发起人的状态改变就将状态存入备忘录，然后将备忘录添加至管理者
        //有多少个状态就有多少个备忘录对象
        originator.setState("状态一");
        Memento memento1 = originator.createMemento();
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(memento1);

        originator.setState("状态二");
        Memento memento2 = originator.createMemento();
        caretaker.setMemento(memento2);

        originator.setState("状态三");
        Memento memento3 = originator.createMemento();
        caretaker.setMemento(memento3);
        originator.setState("状态四");
        Memento memento4 = originator.createMemento();
        caretaker.setMemento(memento4);

        System.out.println("此时发起者的状态："+originator.getState());

        originator.resetMemento(caretaker.getMemento());
        System.out.println("恢复一次状态："+originator.getState());

        originator.resetMemento(caretaker.getMemento());
        System.out.println("恢复er次状态："+originator.getState());
        originator.resetMemento(caretaker.getMemento());
        System.out.println("恢复三次状态："+originator.getState());

    }
}
