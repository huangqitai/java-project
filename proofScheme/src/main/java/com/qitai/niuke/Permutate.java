package com.qitai.niuke;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Permutate {
    // 保存生成的排列组合内容
    public List<String> Permutation = new ArrayList<String>();
    /**
     * 递归的方式计算排列组合
     * @param list  传入list.size()个集合
     * @param preStr 上一步递归中生成的排列组合
     * @return
     */
    public  void permutation( List<List<String>> list,String preStr) {
        int size = list.size();
        if(1==size){
            for(int i=0; i<list.get(0).size(); i++) {
                Permutation.add(preStr + list.get(0).get(i));
            }
        }else{
            List<String> permu = new ArrayList<String>(list.get(0));
            List<List<String>> now = new ArrayList<List<String>>(list);
            now.remove(0);
            for(int i=0; i<permu.size(); i++){
                permutation(now, preStr +permu.get(i));
            }
        }
    }

    //准备递归用的参数
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");
        list2.add("c");
        List<String> list3 = new ArrayList<>();
        list3.add("_A");
        list3.add("_B");
        list3.add("_C");
        List<List<String>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        Permutate permutate = new Permutate();
        permutate.permutation(list,"");
        String str = permutate.Permutation.toString();
        System.out.println(str);
    }


    @Test
    public void test() {
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");
        list2.add("c");
        List<String> list3 = new ArrayList<>();
        list3.add("_A");
        list3.add("_B");
        list3.add("_C");
        List<List<String>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        testFun(list,"");
        String str = Permutation.toString();
        System.out.println(str);
    }
    private void testFun(List<List<String>> list,String ss){
        int size = list.size();
        if (size==1){
            List<String> subList = list.get(0);
            for (int i = 0; i < subList.size(); i++) {
                Permutation.add(ss+subList.get(i));
            }
        }else {
            List<String> subList = list.get(0);
            List<List<String>> lists = new ArrayList<>(list);
            lists.remove(0);
            for (int i = 0; i < subList.size(); i++) {
                String s = ss+subList.get(i);
                testFun(lists,s);
            }
        }
    }
}
