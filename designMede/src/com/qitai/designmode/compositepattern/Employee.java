package com.qitai.designmode.compositepattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 顶层部门，比如自然资源事业部
 * 部门名称，职员总数，子部门等属性
 */
public class Employee {
    private String name;
    private int salary;
    private List<Employee> empList;

    Employee(String name, int salary){
        this.name = name;
        this.empList = new ArrayList<Employee>();
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmpCount() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public List<Employee> getEmpList() {
        return empList;
    }

    public void setEmpList(List<Employee> empList) {
        this.empList = empList;
    }

    @Override
    public String toString() {
        return name+",  "+salary;
    }
}
