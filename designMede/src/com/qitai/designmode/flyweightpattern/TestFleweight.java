package com.qitai.designmode.flyweightpattern;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestFleweight {
    @Test
    public void test(){

        //使用工厂获取Circle对象
        ShapeFactory shapeFactory = new ShapeFactory();
        Circle circle = (Circle) shapeFactory.getCircle("circle");
        circle.setCenter_x(10);
        circle.setCenter_y(20);
        circle.setRadius(6);
        circle.draw();

        //再次取出存储的对象进行属性修改，加以复用
        Circle circle1 = (Circle) shapeFactory.getCircle("circle");
        circle1.setRadius(16);
        circle1.draw();
    }
}
