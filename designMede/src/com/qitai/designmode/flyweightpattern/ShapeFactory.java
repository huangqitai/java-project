package com.qitai.designmode.flyweightpattern;

import java.util.HashMap;
import java.util.Map;

public class ShapeFactory {
    private static Map<String,Shape> shapeMap = new HashMap<>();
    //静态工厂方法的写法，用于管理存储的对象
    public static Shape getCircle(String key){
        Circle circle = (Circle) shapeMap.get(key);

        if (circle==null){
            circle = new Circle();
            System.out.println("新建Circle对象");
            shapeMap.put(key,circle);
        }
        return circle;
    }
}
