package com.qitai.designmode.statepattern;

public class NoSweetState implements State {
    SweetMchine sweetMchine;
    NoSweetState(SweetMchine sweetMchine){
        this.sweetMchine = sweetMchine;
    }
    @Override
    public void insertCoin() {
        System.out.println("没有糖果了");
    }

    @Override
    public void backCoin() {
        System.out.println("没有糖果了");
    }

    @Override
    public void giveSweet() {
        System.out.println("没有糖果了");
    }
}
