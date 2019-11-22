package com.qitai.designmode.filterpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 年龄过滤
 */
public class FilterAge implements FilterDemo{
    @Override
    public List<Person> filter(List<Person> personList) {
        List<Person> list = new ArrayList<>();
        for (Person person:personList) {
            if (person.getAge()>18){
                list.add(person);
            }
        }
        return list;
    }
}
