package com.qitai.designmode.builderpattern;

/**
 * 墙体类，实现房屋接口
 */
public abstract class Wall implements HousePart {
    @Override
    public abstract Materials Kind();

    @Override
    public abstract double budget();
}
