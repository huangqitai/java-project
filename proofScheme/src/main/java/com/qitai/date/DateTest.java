package com.qitai.date;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTest {
    public static final String value = "organization";
    @Test
    public void test() throws ParseException {
        String dateStr = "2020-05-21 00:00:00";
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
        System.out.println(date);
    }
   @Test
    public void test4() throws ParseException {

       String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
       System.out.println(dateStr);

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

    @Test
    public void test2(){
        String s = "organization";
        Map<String,String> queryMap = new  HashMap<>();
        //value = queryMap.put("key","value");

        switch (s){
            case value :
                System.out.println(s);break;
            default:
                System.out.println("不相等");
        }
    }

    @Test
    public void Test3(){
        isLike("打扫房间看在埃是法国或多或少单位1开展的是法国第一时间工作撒地方","%在%单位开展%工作%");
    }
    public boolean isLike(String target,String search){
        String[] searchs = search.split("%");
        boolean islike = false;
        for (int i=1;i<searchs.length;i++){
            if (target.contains(searchs[i])){
                islike = true;
                target = target.split(searchs[i])[1];
            }else {
                islike = false;
                break;
            }
        }
        return islike;
    }

    @Test
    public void Test4(){
        System.out.println("".toString().trim());
    }
    @Test
    public void tset5(){
        Object s1 = "1234";
        String s2 = "1234";
        System.out.println(s1.equals(s2));
        System.out.println(s1==s2);
    }
    @Test
    public void tset6(){
        Object m = new HashMap<>();
        System.out.println(!(m instanceof Map));
    }

    @Test
    public void tset8(){
        String ss = "4.5";
        double aa = Double.valueOf(String.valueOf(ss));
        System.out.println(aa);
    }

    @Test
    public void tset7(){
        List<String> JID_FIELD_NAME = Arrays.asList("sblsh", "fsblsh", "jid");
        String ss = "{\"strJson\":\"{\\\"XZQHDM\\\":\\\"441702\\\",\\\"SBLSH\\\":\\\"44170020061600081\\\",\\\"PGJZ\\\":\\\"\\\",\\\"WSZT\\\":\\\"1\\\",\\\"SFPGJ\\\":\\\"1\\\",\\\"WSSJ\\\":\\\"2020-06-29\\\",\\\"CLXX\\\":[{\\\"XH\\\":\\\"1\\\",\\\"CLMC\\\":\\\"不动产权属转移完（免）税情况\\\",\\\"CLFJXX\\\":[{\\\"XH\\\":\\\"1\\\",\\\"FJLJ\\\":\\\"/202006/441700200616000811f3892-0ef5-4675-9174-c57428c7d91e.pdf\\\",\\\"FJMC\\\":\\\"不动产权属转移完（免）税情况\\\"}],\\\"FS\\\":\\\"1\\\"}],\\\"FJLJ\\\":\\\"/202006/441700200616000811f3892-0ef5-4675-9174-c57428c7d91e.pdf\\\"}\"}";
        System.out.println(ss);
        String jid = getParameterValue(ss,JID_FIELD_NAME);
        System.out.println(jid);
    }

    private String getParameterValue(String strJson, List<String> fieldNameList) {

        String value = "";
        for (String fieldName : fieldNameList) {
            String[] afters = strJson.split("\\\"" + fieldName + "\\\"").length > 1 ?
                    strJson.split("\\\"" + fieldName + "\\\"") :
                    strJson.split("\\\"" + fieldName.toUpperCase() + "\\\"");
            if (afters.length > 1) {
                String after = afters[1];
                Pattern p = Pattern.compile("\\\"(\\w+)\\\"");
                Matcher m = p.matcher(after);
                if (m.find()) {
                    value = m.group(1);
                    if (value!=null&&value!="") {
                        break;
                    }
                }
            }
        }
        return value;
    }


    @Test
    public void test9(){
        Date date1 = new Date("2020-06-27 8:30:00");
        Date date2 = new Date("2020-06-29 17:30:00");
        System.out.println(differentDays(date1,date2));
    }

    /**
     * date2比date1多的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
}
