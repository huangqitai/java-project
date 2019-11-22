package com.qitai.designmode.builderpattern;

/**
 * 砖墙
 */
public class BrickWall extends Wall{
    @Override
    public String name() {
        return "砖墙";
    }

    @Override
    public Materials Kind() {
        return new Brick();
    }

    @Override
    public double budget() {
        return 120000;
    }
}
