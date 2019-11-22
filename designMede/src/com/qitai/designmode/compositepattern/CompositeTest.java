package com.qitai.designmode.compositepattern;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CompositeTest {
    @Test
    public void test(){
        Employee natureDepartment = new Employee("自然资源事业部经理",30100);
        Employee developDepartment = new Employee("自然资源开发部经理",21200);
        Employee implementationDepartment = new Employee("自然资源实施部经理",12000);
        Employee projectDepartment = new Employee("自然资源方案部经理",12100);
        Employee natureEmp = new Employee("自然资源事业部直属职员",10100);

        Employee emp1 = new Employee("职员甲",8100);
        Employee emp2 = new Employee("职员乙",9200);
        Employee emp3 = new Employee("职员丙",7000);
        Employee emp4 = new Employee("职员A",12100);
        Employee emp5 = new Employee("职员B",7100);

        developDepartment.getEmpList().add(emp1);
        developDepartment.getEmpList().add(emp2);
        developDepartment.getEmpList().add(emp3);
        developDepartment.getEmpList().add(emp4);
        developDepartment.getEmpList().add(emp5);

        emp4.getEmpList().add(new Employee("职员AA",7500));
        emp4.getEmpList().add(new Employee("职员AB",7500));
        emp4.getEmpList().add(new Employee("职员AC",7500));


        List<Employee> natureList = natureDepartment.getEmpList();
        natureList.add(developDepartment);
        natureList.add(implementationDepartment);
        natureList.add(projectDepartment);
        natureList.add(natureEmp);

        CompositeIterator compositeIterator = new CompositeIterator(natureDepartment.getEmpList().iterator());
        while (compositeIterator.hasNext()){
            System.out.println(compositeIterator.next().toString());
        }
    }
}
