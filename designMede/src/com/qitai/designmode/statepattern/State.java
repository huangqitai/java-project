package com.qitai.designmode.statepattern;

/**
 * 状态接口，所有的状态都应该实现
 */
public interface State {
    void insertCoin();
    void backCoin();
    void giveSweet();
}
