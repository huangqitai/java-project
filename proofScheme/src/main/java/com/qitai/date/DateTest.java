package com.qitai.date;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateTest {

    @Test
    public void test() throws ParseException {
        String dateStr = "2020-05-21 00:00:00";
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
        System.out.println(date);
    }

    @Test
    public void test1(){
        Map<String,Object> map = new HashMap<>();
        map.put("a","aaa");
        map.put("b","aaa");
        map.put("c","aaa");
        map.put("d","aaa");
        map.put("e","aaa");
        map.put("f","");
        System.out.println(map.get("e").toString());
        System.out.println(map.containsKey("f"));
        System.out.println(map.get("f"));
    }
}
