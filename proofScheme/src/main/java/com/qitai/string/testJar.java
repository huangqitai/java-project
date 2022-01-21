package com.qitai.string;

import org.junit.Test;

public class testJar {
    public void str(String a){
        a+="aaa";
        System.out.println(a);
    }

    @Test
    public void t1(){
        int x=10,y=2,z;
        x = ++x*y++;
        z = x/y++;
        System.out.println(x+","+y+","+z);
    }
}
