package com.qitai.string;

import org.junit.Test;

public class StringTest {
    @Test
    public void test(){
        String a = "bbb";
        str(a);
        System.out.println(a);
    }

    private void str(String a){
        a+="aaa";
        System.out.println(a);
    }

    @Test
    public void test1(){
        String kq = "1.50(有事)";
        if (kq.contains("(")){
            kq = kq.replace(kq.substring(kq.indexOf("("),kq.indexOf(")")+1),"");
        }

        System.out.println(Double.parseDouble(kq));
    }
}
