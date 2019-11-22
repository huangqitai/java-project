package com.qitai.designmode.visitorpattern;

/**
 * 访问者接口
 */
public interface Visitor {
    //访问者为元素扩展的行为
    void visit(Element element);
}
