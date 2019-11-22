package com.qitai.designmode.visitorpattern;

public class ElementB implements Element{
    private String elementName;

    ElementB(String elementName){
        this.elementName = elementName;
    }
    @Override
    public void accept(Visitor visitor) {
        System.out.println("访问签的元素名"+elementName);
        visitor.visit(this);
        System.out.println("访问后的元素名："+elementName);
    }

    @Override
    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    @Override
    public String getElementName() {
        return elementName;
    }
}
