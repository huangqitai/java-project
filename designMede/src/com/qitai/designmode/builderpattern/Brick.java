package com.qitai.designmode.builderpattern;

/**
 * 墙体的材料，砖
 */
public class Brick implements Materials{
    @Override
    public String materialKind() {
        return "砖";
    }
}
