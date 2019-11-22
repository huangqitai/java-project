package com.qitai.designmode.statepattern;

public class NoCionState implements State{
    SweetMchine sweetMchine;
    NoCionState(SweetMchine sweetMchine){
        this.sweetMchine = sweetMchine;
    }
    @Override
    public void insertCoin() {
        System.out.println("投币成功");
        sweetMchine.setState(sweetMchine.getHaveCoin());
    }

    @Override
    public void backCoin() {
        System.out.println("你还没有投硬币");
    }

    @Override
    public void giveSweet() {
        System.out.println("你还没有投硬币");
    }
}
