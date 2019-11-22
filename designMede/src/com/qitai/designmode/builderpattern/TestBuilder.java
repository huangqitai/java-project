package com.qitai.designmode.builderpattern;

import org.junit.Test;

public class TestBuilder {
    @Test
    public void test(){
        //房屋建造需要墙体和房顶
        BuilderHouse builderHouse = new BuilderHouse();
        Wall wall = new BrickWall();
        Roof roof = new GlazedTileRoof();
        House house = builderHouse.buiderHouse(wall,roof);
        house.showParts();
        System.out.println("总预算为："+house.getBudget());

        //改变了墙体抽象累的实现类，会得到不同属性的房屋
        wall = new ConcreteWall();

        house = builderHouse.buiderHouse(wall,roof);
        house.showParts();
        System.out.println("总预算为："+house.getBudget());
    }
}
