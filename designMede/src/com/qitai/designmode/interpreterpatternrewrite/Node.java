package com.qitai.designmode.interpreterpatternrewrite;

public abstract class Node {
    public abstract void parse(Context context) throws ParseException;
}
