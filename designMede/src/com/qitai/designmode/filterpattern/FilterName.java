package com.qitai.designmode.filterpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 姓名过滤器
 */
public class FilterName implements FilterDemo{
    @Override
    public List<Person> filter(List<Person> personList) {
        List<Person> list = new ArrayList<>();
        for (Person person:personList) {
            if (person.getName().substring(0,1).equals("张")){
                list.add(person);
            }
        }
        return list;
    }
}
