package com.qitai.designmode.builderpattern;

/**
 * 房顶
 */
public abstract class Roof implements HousePart {
    @Override
    public abstract Materials Kind();

    @Override
    public abstract double budget();
}
