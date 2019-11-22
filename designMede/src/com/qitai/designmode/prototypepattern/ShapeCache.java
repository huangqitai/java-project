package com.qitai.designmode.prototypepattern;

import java.util.Hashtable;

public class ShapeCache {
    private static Hashtable<String, Shape> shapeMap
            = new Hashtable<String, Shape>();

    public static Shape getShape(String shapeId) {
        //根据key id的值取出Circle对象，并返回该对象的克隆
        Shape cachedShape = shapeMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }

    //创建一个Circle对象，存入Hashtable中
    public static void loadCache() {
        Circle circle = new Circle();
        System.out.println(circle);
        circle.setId("1");
        shapeMap.put(circle.getId(),circle);
    }
}
