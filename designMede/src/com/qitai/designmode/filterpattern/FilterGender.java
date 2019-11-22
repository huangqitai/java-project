package com.qitai.designmode.filterpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 性别过滤
 */
public class FilterGender implements FilterDemo {
    @Override
    public List<Person> filter(List<Person> personList) {
        List<Person> list = new ArrayList<>();
        for (Person person:personList) {
            if (person.getGender().equals("男")){
                list.add(person);
            }
        }
        return list;
    }
}
