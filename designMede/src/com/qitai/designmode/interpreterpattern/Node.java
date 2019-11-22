package com.qitai.designmode.interpreterpattern;

public abstract class Node {
    public abstract void parse(Context context) throws ParseException;
}
