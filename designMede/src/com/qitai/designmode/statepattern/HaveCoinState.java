package com.qitai.designmode.statepattern;

public class HaveCoinState implements State {

    SweetMchine sweetMchine;
    HaveCoinState(SweetMchine sweetMchine){
        this.sweetMchine = sweetMchine;
    }
    @Override
    public void insertCoin() {
        System.out.println("你已经付过钱了");
        giveSweet();
    }

    @Override
    public void backCoin() {
        System.out.println("正在退还硬币");
        sweetMchine.setState(sweetMchine.getNoCoin());
    }

    @Override
    public void giveSweet() {
        System.out.println("你已经付过钱了，给你糖果。");
        sweetMchine.setState(sweetMchine.getNoCoin());
    }
}
