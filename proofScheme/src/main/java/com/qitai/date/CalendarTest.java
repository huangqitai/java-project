package com.qitai.date;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarTest {
    String[] HOLIDAYS = {"01-01","05-01","10-01"};

    @Test
    public void writeCalendar(){
        ChineseDate date = new ChineseDate(cn.hutool.core.date.DateUtil.parseDate("2020-01-01"));
        System.out.println(date.getChineseYear());
        System.out.println(date.getMonth()+"    "+date.getDay());
        System.out.println(date.getFestivals());
    }

    @Test
    public void writeCalendar1() throws ParseException {
        int year = 2020;
        String path = "D:\\calender.txt";
        FileUtil.touch(new File(path));

        List<Map> maps = new ArrayList<>();
        Map map = new HashMap();
        ChineseDate date;
        for (int i=1;i<13;i++){
            List<Map> newDates = new ArrayList<>();
            List<String> dates = DateUtil.getMonthFullDay(year,i);
            for (String d :dates ) {
                Map dateMap = new HashMap();
                date = new ChineseDate(cn.hutool.core.date.DateUtil.parseDate(d));
                dateMap.put("date",d);
                dateMap.put("lunarCalendar",date.getChineseYear()+"-"+date.getMonth()+"-"+date.getDay());
                dateMap.put("festivals",date.getFestivals());
                dateMap.put("dayDescribe",DateUtil.isWeekend(d)==1||DateUtil.isWeekend(d)==7?"周末":"工作日");
                dateMap.put("dayType",DateUtil.isWeekend(d)==1||DateUtil.isWeekend(d)==7?1:0);
                dateMap.put("dayOfWeek",getWeek(DateUtil.isWeekend(d)));
                newDates.add(dateMap);
            }
            map.put(i+"月",newDates);
        }
        String jsonStr = JSONUtil.toJsonStr(map);
        FileWriter writer = new FileWriter(path);
        writer.write(jsonStr);
    }

    private String getWeek(int day_of_week){
        switch (day_of_week){
            case 1:return "周日";
            case 2:return "周一";
            case 3:return "周二";
            case 4:return "周三";
            case 5:return "周四";
            case 6:return "周五";
            case 7:return "周六";
            default:return "";
        }
    }
}
