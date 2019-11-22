package com.qitai.designmode.statepattern;

/**
 * 糖果机
 */
public class SweetMchine {
    //四种状态：没有硬币、投入硬币、出售糖果、糖果售尽
    State noCoin;
    State haveCoin;
    State giveSweet;
    State noSweet;

    //默认没有装糖果。
    State state = noSweet;
    int sweetCount = 0;

    SweetMchine(int sweetCount){
        this.sweetCount = sweetCount;
        noCoin = new NoCionState(this);
        haveCoin = new HaveCoinState(this);
        giveSweet = new GiveSweetState(this);
        noSweet = new NoSweetState(this);

        if (sweetCount>0){
            state = noCoin;
        }
    }

    public void setState(State state){
        this.state = state;
    }
    public void insertCoin(){
        state.insertCoin();
    }
    public void backCoin(){
        state.backCoin();
    }

    public void giveSweet(){
        state.giveSweet();
    }

    public State getNoCoin() {
        return noCoin;
    }

    public void setNoCoin(State noCoin) {
        this.noCoin = noCoin;
    }

    public State getHaveCoin() {
        return haveCoin;
    }

    public void setHaveCoin(State haveCoin) {
        this.haveCoin = haveCoin;
    }

    public State getGiveSweet() {
        return giveSweet;
    }

    public void setGiveSweet(State giveSweet) {
        this.giveSweet = giveSweet;
    }

    public State getNoSweet() {
        return noSweet;
    }

    public void setNoSweet(State noSweet) {
        this.noSweet = noSweet;
    }

    public State getState() {
        return state;
    }

    public int getSweetCount() {
        return sweetCount;
    }

    public void setSweetCount(int sweetCount) {
        this.sweetCount = sweetCount;
        if (sweetCount>0){
            state = noCoin;
        }else state = noSweet;
    }
}
