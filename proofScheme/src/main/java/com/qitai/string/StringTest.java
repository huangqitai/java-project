package com.qitai.string;

import com.qitai.utils.ConvertUtil;
import com.qitai.utils.json.JsonUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void test5(){
        String log = "{\"msg\":null,\"res\":\"SUCCESS\",\"respdata\":[{\"appcode\":\"Azrzyyw4400000000008202006180001\",\"apicode\":\"Izrzyyw440000000000F202006230001\",\"data\":{\"respdata\":{\"records\":[{\"swbs\":\"0\",\"xmPpddm\":\"1\",\"gmsfhm\":\"441702199202241713\",\"gmsfhmPpddm\":\"1\"}]},\"msg\":\"查询成功！\",\"res\":\"200\",\"originDept\":\"中华人民共和国公安部\",\"success\":true}}],\"resDescription\":\"成功\"}";
        Map<String,Object> resultMap = new HashMap<>();

            Map<String, Object> contentMap = JsonUtil.jsonStringToMap(log);

            if ("SUCCESS".equals(contentMap.get("res"))) {
                List<Map<String, Object>> respDataList = (List<Map<String, Object>>) contentMap.get("respdata");
                resultMap = JsonUtil.jsonStringToMap(ConvertUtil.getValue(respDataList.get(0).get("data"), ""));
            } else if ("200".equals(contentMap.get("res"))) {
                resultMap = contentMap;
            } else {
                resultMap.put("res", contentMap.get("res"));
                resultMap.put("msg", contentMap.get("msg"));
            }
        System.out.println("sss");
    }

    @Test
    public void s(){
        String a = "aaww";sa("www");
        System.out.println(a);
    }
    private String sa(String a){
        a = a + "aaa";
        return "";
    }
}
