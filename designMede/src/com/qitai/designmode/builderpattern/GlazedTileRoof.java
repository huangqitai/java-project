package com.qitai.designmode.builderpattern;

/**
 * 琉璃瓦房顶
 */
public class GlazedTileRoof extends Roof{
    @Override
    public String name() {
        return "琉璃瓦房顶";
    }

    @Override
    public Materials Kind() {
        return new GlazedTile();
    }

    @Override
    public double budget() {
        return 80000;
    }
}
