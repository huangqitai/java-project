package com.qitai.designmode.mementopattern;

import java.util.Stack;

public class Caretaker {
    private Stack<Memento> mementos;
    public Caretaker(){
        mementos = new Stack<>();
    }
    public void setMemento(Memento memento){
        mementos.push(memento);
    }
    public Memento getMemento(){
        return mementos.pop();
    }
}
