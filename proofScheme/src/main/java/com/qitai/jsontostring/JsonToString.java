package com.qitai.jsontostring;

import com.southgis.ibase.utils.json.JsonUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonToString {
    @Test
    public void test(){
        ObjectA a = new ObjectA();
        a.setA("aaa");
        a.setB("bbb");
        a.setC(1);
        ObjectA b = new ObjectA();
        b.setA("aaa");
        b.setB("bbb");
        b.setC(2);
        List<ObjectA> ls = new ArrayList<>();
        ls.add(a);
        ls.add(b);
        System.out.println(JsonUtil.toJsonString(ls));
        String ss = JsonUtil.toJsonString(ls);
        ArrayList<HashMap<String, Object>> os = JsonUtil.jsonStringToList(ss);
        System.out.println(os);
    }
}
