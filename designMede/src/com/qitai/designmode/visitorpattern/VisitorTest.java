package com.qitai.designmode.visitorpattern;

import org.junit.Test;

public class VisitorTest {
    @Test
    public void test(){
        ObjectStructure objectStructure = new ObjectStructure();
        Visitor visitorA = new VisitorA();
        Element elementA = new ElementA("元素A");
        Element elementB = new ElementB("元素B");


        objectStructure.add(elementA);
        objectStructure.add(elementB);
        objectStructure.accept(visitorA);
    }
}
