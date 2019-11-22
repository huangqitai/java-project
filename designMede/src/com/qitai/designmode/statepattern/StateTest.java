package com.qitai.designmode.statepattern;

import org.junit.Test;

public class StateTest {
    @Test
    public void test(){
        SweetMchine sweetMchine = new SweetMchine(10);
        sweetMchine.giveSweet();

        sweetMchine.setState(sweetMchine.getNoCoin());
        sweetMchine.backCoin();

        sweetMchine.insertCoin();
        sweetMchine.giveSweet();

        sweetMchine.setSweetCount(0);
        sweetMchine.insertCoin();
    }
}
