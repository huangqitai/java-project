package com.qitai.string;

import com.aspose.cad.internal.cm.S;
import com.qitai.utils.ConvertUtil;
import com.qitai.utils.DesUtil;
import com.qitai.utils.json.JsonUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringTest {

    @Test
    public void t2(){
        System.out.println(DesUtil.getEncryptString("52c7a6198cba5c8bb883b3479e3a6e489de0c0dce87aa9135cbb4c6c"));
        System.out.println(DesUtil.getDecryptString("7a+vyjlYFPXpqaeWeKyCX8dtLGnKheWPbdNDTIcpeWdiT+MoMRsV98PNDI5eoz+ueQDMh+fcPuwQ\r\nFG13I3n+SQ=="));
        System.out.println(DesUtil.getEncryptString("lXEOKyOk8o6xhQBbC5SmCqNwY5pNw3mZG8CuZS5QP/JN1tr0p9jiA93D3p8+wwW4TuV/yf++RHUQ\\nFG13I3n+SQ=="));
    }

    @Test
    public void test(){
        String a = "bbb";
        str(a);
        System.out.println(a);
    }
    @Test
    public void t33t(){
        String txdz = "/202106/JMH-808559/946b65ea-bf86-4a64-b00b-5e30986123a9.dwg||0";
        String sjly = txdz.split("\\|\\|")[1];
        txdz = txdz.split("\\|\\|")[0];
        System.out.println(txdz);
        System.out.println(txdz.substring(0,txdz.lastIndexOf("/")));
        System.out.println(txdz.substring(txdz.lastIndexOf("/")+1));
        System.out.println(sjly);
    }

    @Test
    public void t3t(){
        Set<String> error = new HashSet<>();
        error.add("xxxx");
        error.add("xxxx");
        error.add("xxxxx");
        error.add("xxxxxxx");
        StringBuilder errorStr = new StringBuilder();
        int i = 1;
        for (String err:error){
            errorStr.append(i).append("、").append(err).append("\n");
            i++;
        }
        System.out.println(errorStr.toString());
    }

    @Test
    public void t3(){
        String a = "\\x01";
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

    @Test
    public void s1(){
        System.out.println("'"+null+"'");
    }
}
