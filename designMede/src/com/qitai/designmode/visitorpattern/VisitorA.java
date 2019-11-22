package com.qitai.designmode.visitorpattern;

public class VisitorA implements Visitor {
    @Override
    public void visit(Element element) {
        System.out.println("在"+element.getElementName()+"接受A访问的时候,改变了它的elementName属性值");
        element.setElementName("被访问者A改变的"+element.getElementName());
    }
}
