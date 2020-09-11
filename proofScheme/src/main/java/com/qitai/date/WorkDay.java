package com.qitai.date;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WorkDay {

    public static String isWeekend(String bDate) throws ParseException {
        DateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
        Date bdate = format1.parse(bDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(bdate);
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            return "OK";
        } else{
            return "NO";
        }
    }

    @Test
    public void test1() throws ParseException {
        System.out.println(isWeekend("2020/06/29"));
    }

    @Test
    public void test2(){
        System.out.println(DateUtil.leapMonth(2020));
        int[] ymd = DateUtil.lunarToSolar(2020,4,5,false);
        System.out.println(ymd[0]+"     "+ymd[1]+"      "+ymd[2]);
    }
}
