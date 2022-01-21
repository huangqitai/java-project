package com.qitai.niuke;

import org.junit.Test;

public class Tests {
    public static final String A="ss";
    @Test
    public void t1(){
        String a = "ss";
        String b = new String("ss");
        String d = "s";
        String e = "s";
        String c = d+e;
        String f = "s"+"s";
        System.out.println(a==A);
        System.out.println(a==b);
        System.out.println(a==c);
        System.out.println(a==f);
        System.out.println(f==A);
        System.out.println(a==c.intern());
        System.out.println(a==b.intern());
    }
}
