package com.qitai.designmode.builderpattern;

public class Concrete implements Materials {
    @Override
    public String materialKind() {
        return "混凝土";
    }
}
