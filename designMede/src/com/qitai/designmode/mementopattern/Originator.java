package com.qitai.designmode.mementopattern;

public class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento createMemento(){
        return new Memento(state);
    }
    public void resetMemento(Memento memento){
        this.state = memento.getState();
    }
}
