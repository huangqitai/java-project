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
    @Test
    public void test3(){
        String kq = "2020001000101";
        char[] chars = kq.toCharArray();
        System.out.println(str2int64(chars)+1);

    }
    private long str2int64(char[] str)
    {
        int i = 0;
        long n = 0;
        int flag = 1;

        if(str[0] == '-')
        {
            i = 1;
            flag = -1;
        }

        for(; i<str.length ; i++)
        {
            assert(str[i] >= '0' && str[i] <= '9');

            //循环转换
            n = str[i] - '0' + n*10;
        }
        return n*flag;
    }
}
