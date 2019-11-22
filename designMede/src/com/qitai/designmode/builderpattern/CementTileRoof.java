package com.qitai.designmode.builderpattern;

/**
 * 水泥瓦房顶
 */
public class CementTileRoof extends Roof{
    @Override
    public String name() {
        return "水泥瓦房顶";
    }

    @Override
    public Materials Kind() {
        return new CementTile();
    }

    @Override
    public double budget() {
        return 60000;
    }
}
