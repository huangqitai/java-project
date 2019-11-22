package com.qitai.designmode.builderpattern;

/**
 * 建筑的材料接口，无论是墙体还是房顶都属于材料建造
 */
public interface Materials {
    String materialKind();
}
