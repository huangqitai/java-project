package com.qitai.designmode.prototypepattern;

import org.junit.Test;

import java.io.IOException;

public class PrototypeTest {
    @Test
    public void test(){
        ShapeCache.loadCache();
        Shape shape = ShapeCache.getShape("1");
        /*System.out.println(shape.type);
        shape.draw();*/
       // System.out.println(shape);

        Circle circle = new Circle();
        circle.setId("1");
        Circle circle1 = (Circle)circle.clone();

        System.out.println(circle.getId()==circle1.getId());

        Rectangle rectangle = new Rectangle();
        rectangle.setId("2");
        try {
            Rectangle rectangle1 = rectangle.deepClone();
            System.out.println(rectangle.getId()==rectangle1.getId());//false，String是引用类型，深复制下会创建一个引用对象
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
