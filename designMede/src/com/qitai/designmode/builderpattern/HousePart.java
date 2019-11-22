package com.qitai.designmode.builderpattern;

/**
 * 房屋接口，墙体、房顶都是房屋的一部分
 */
public interface HousePart {
    public String name();
    public Materials Kind();
    public double budget();
}
