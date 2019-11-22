package com.qitai.designmode.visitorpattern;

/**
 * 元素接口
 */
public interface Element {
    /**
     * 元素提供的接受访问接口
     * @param visitor
     */
    void accept(Visitor visitor);

    void setElementName(String elementName);

    String getElementName();
}
