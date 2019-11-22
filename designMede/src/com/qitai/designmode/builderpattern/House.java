package com.qitai.designmode.builderpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造的对象，一间房屋
 */
public class House {
    private List<HousePart> parts = new ArrayList<>();

    public void addPart(HousePart part){
        parts.add(part);
    }
    public double getBudget(){
        double budget = 0;
        for (HousePart part: parts) {
            budget += part.budget();
        }
        return budget;
    }
    public void showParts(){
        for (HousePart part: parts) {
            System.out.println("房屋部分："+part.name()+",   材料种类："+part.Kind().materialKind()+",   预算："+part.budget());
        }
    }
}
