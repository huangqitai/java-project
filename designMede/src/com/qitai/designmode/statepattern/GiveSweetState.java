package com.qitai.designmode.statepattern;

public class GiveSweetState implements State {
    SweetMchine sweetMchine;

    GiveSweetState(SweetMchine sweetMchine){
        this.sweetMchine = sweetMchine;
    }

    @Override
    public void insertCoin() {
        System.out.println("错误");
    }

    @Override
    public void backCoin() {
        System.out.println("错误");
    }

    @Override
    public void giveSweet() {
        System.out.println("错误");
    }
}
