package com.qitai.designmode.flyweightpattern;

/**
 * 圆
 */
public class Circle implements Shape{
    int center_x;
    int center_y;
    int radius;

    public Circle(){

    }

    public int getCenter_x() {
        return center_x;
    }

    public void setCenter_x(int center_x) {
        this.center_x = center_x;
    }

    public int getCenter_y() {
        return center_y;
    }

    public void setCenter_y(int center_y) {
        this.center_y = center_y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("圆心坐标：X:"+center_x+"   Y:"+center_y+",   半径："+radius);
    }
}
