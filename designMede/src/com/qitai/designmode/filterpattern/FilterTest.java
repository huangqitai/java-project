package com.qitai.designmode.filterpattern;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FilterTest {
    @Test
    public void test(){
        Person p1 = new Person("张三",19,"男");
        Person p2 = new Person("李四",26,"男");
        Person p3 = new Person("张燕",17,"女");
        Person p4 = new Person("王艳",19,"女");
        Person p5 = new Person("张三",19,"女");

        List<Person> personList = new ArrayList<>();
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);
        personList.add(p5);

        FilterDemo filterDemo = new FilterName();
        List<Person> list = filterDemo.filter(personList);

        for (Person person:list) {
            System.out.println(person.toString());
        }
    }
}
