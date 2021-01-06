package com.qitai;

import org.junit.Test;

public class ExceptionTest {
    @Test
    public void test1(){
        try {
            System.out.println(fun1());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String fun1() throws Exception{
        String s = "xxxx";
        try {
            throw new NullPointerException("空");
        }catch (Exception e){
            s = "cccc";
            throw e;
        }finally {
            s+="***";
        }
    }
}
