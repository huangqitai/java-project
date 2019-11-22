package com.qitai.designmode.builderpattern;

/**
 * 建造类，将房屋组装完成
 */
public class BuilderHouse {
    public House buiderHouse(Wall wall,Roof roof){
        House house = new House();
        house.addPart(wall);
        house.addPart(roof);
        return house;
    }
}
