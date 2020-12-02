package com.qitai.stream;

import com.southgis.ibase.utils.json.JsonUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    @Test
    public void demo01(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for (int i=1;i<9;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("id",i);
            map.put("name","张三丰"+i);
            list.add(map);
        }
        Stream<Map<String, Object>> s1 = list.stream();
        //list.stream().forEach(map-> System.out.println(map));
        List<Map<String,Object>> list2 = new ArrayList<Map<String,Object>>();
        for (int i=1;i<5;i++){
            Map<String,Object> map2 = new HashMap<>();
            map2.put("id",i);
            map2.put("grade",i+60);
            list2.add(map2);
        }
        //list2.stream().forEach(s-> System.out.println(s));
/**
 * List<Map<Object, Object>> resultList = oneList.stream().map(map -> twoList.stream()
 * .filter(m -> Objects.equals(m.get("id"), map.get("id")))
 * .findFirst().map(m -> {
 * map.putAll(m);
 * map.put("grade",90);
 * return map;
 * }).orElse(null))
 * .filter(Objects::nonNull).collect(Collectors.toList());
 */
/* List<Map<String, Object>> resultList2 = list.stream().map(m->{
m.put("grade",0);
for (int i=0;i<list2.size();i++){
if(m.get("id").equals(list2.get(i).get("id"))){
m.put("grade",list2.get(i).get("grade"));
break;
}
}
return m;
}).collect(Collectors.toList());*/
        List<Map<String, Object>> resultList2 = list.stream().map(m->{
            m.put("grade",0);
            list2.stream().filter(m2->Objects.equals(m.get("id"), m2.get("id"))).forEach(s-> m.put("grade",s.get("grade")));
            return m;
        }).collect(Collectors.toList());
        resultList2.stream().forEach(s-> System.out.println(s));
    }

    @Test
    public void test1(){
        String ss = "{\n" +
                "    \"itemId\": \"A0303\",\n" +
                "    \"itemSequence\": \"受理\",\n" +
                "    \"itemCode\": \"/\",\n" +
                "    \"listAndTokenParams\": [{\n" +
                "            \"identityType\": \"70\",\n" +
                "            \n" +
                "            \"serviceItemName\": \"预购商品房抵押权预告登记\",\n" +
                "            \"serviceItemCode\": \"A0303\",\n" +
                "            \"licenseItemCode\": \"100208101,100008501,100004201,100008901,100008701\"\n" +
                "        }, {\n" +
                "            \"identityType\": \"10\",\n" +
                "            \"identityNumber\": \"440726196810254514\",\n" +
                "            \"serviceItemName\": \"预购商品房抵押权预告登记\",\n" +
                "            \"serviceItemCode\": \"A0303\",\n" +
                "            \"licenseItemCode\": \"100208101,100008501,100004201,100008901,100008701\"\n" +
                "        }, {\n" +
                "            \"identityType\": \"10\",\n" +
                "            \"identityNumber\": \"441723198312262416\",\n" +
                "            \"serviceItemName\": \"预购商品房抵押权预告登记\",\n" +
                "            \"serviceItemCode\": \"A0303\",\n" +
                "            \"licenseItemCode\": \"100208101,100008501,100004201,100008901,100008701,100212701,100278301\"\n" +
                "        }, {\n" +
                "            \"identityType\": \"10\",\n" +
                "            \"identityNumber\": \"441723198707061344\",\n" +
                "            \"serviceItemName\": \"预购商品房抵押权预告登记\",\n" +
                "            \"serviceItemCode\": \"A0303\",\n" +
                "            \"licenseItemCode\": \"100208101,100008501,100004201,100008901,100008701,100212701,100278301\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"operatorName\": \"李孟龙\",\n" +
                "    \"operatorClerkNum\": \"441704李孟龙\"\n" +
                "}\n";
        String s1 = "{\n" +
                "    \"respdata\": {\n" +
                "        \"metadata\": {\n" +
                "            \"totalCount\": 0\n" +
                "        },\n" +
                "        \"records\": [{\n" +
                "                \"holderName\": \"李孟龙\",\n" +
                "                \"idCode\": \"440726196810254514\",\n" +
                "                \"issueOrgCode\": \"441700000000\",\n" +
                "                \"licenseCode\": \"4400002018021UYHWY\",\n" +
                "                \"issueOrgName\": \"广东省阳江市江城区公安局\",\n" +
                "                \"licenseItemCode\": \"100208101\",\n" +
                "                \"expiryDate\": \"2026-11-16 00:00:00\",\n" +
                "                \"beginDate\": \"\",\n" +
                "                \"holderIdentityNum\": \"440726196810254514\",\n" +
                "                \"holderIdentityType\": \"10\",\n" +
                "                \"name\": \"居民身份证\",\n" +
                "                \"issueDate\": \"2006-11-16 00:00:00\",\n" +
                "                \"businessType\": null\n" +
                "            }, {\n" +
                "                \"holderName\": \"林国辉\",\n" +
                "                \"idCode\": \"441723198312262416\",\n" +
                "                \"issueOrgCode\": \"441704510000\",\n" +
                "                \"licenseCode\": \"4400002018020U3XS1\",\n" +
                "                \"issueOrgName\": \"阳江市公安局北惯派出所\",\n" +
                "                \"licenseItemCode\": \"100208101\",\n" +
                "                \"expiryDate\": \"2038-07-24 00:00:00\",\n" +
                "                \"beginDate\": \"\",\n" +
                "                \"holderIdentityNum\": \"441723198312262416\",\n" +
                "                \"holderIdentityType\": \"10\",\n" +
                "                \"name\": \"居民身份证\",\n" +
                "                \"issueDate\": \"2018-07-24 00:00:00\",\n" +
                "                \"businessType\": null\n" +
                "            }, {\n" +
                "                \"holderName\": \"林国辉,程珠丝\",\n" +
                "                \"idCode\": \"粤（2020）阳江市（阳东）不动产证明第0012817号\",\n" +
                "                \"issueOrgCode\": \"114417230072878920\",\n" +
                "                \"licenseCode\": \"440000202000A55QXN\",\n" +
                "                \"issueOrgName\": \"阳江市自然资源局阳东分局\",\n" +
                "                \"licenseItemCode\": \"100212701\",\n" +
                "                \"expiryDate\": \"\",\n" +
                "                \"beginDate\": \"\",\n" +
                "                \"holderIdentityNum\": \"441723198312262416,441723198707061344\",\n" +
                "                \"holderIdentityType\": \"10\",\n" +
                "                \"name\": \"不动产登记电子证明\",\n" +
                "                \"issueDate\": \"2020-11-30 00:00:00\",\n" +
                "                \"businessType\": null\n" +
                "            }, {\n" +
                "                \"holderName\": \"程珠丝\",\n" +
                "                \"idCode\": \"441723198707061344\",\n" +
                "                \"issueOrgCode\": \"441700000000\",\n" +
                "                \"licenseCode\": \"4400002018020U8V25\",\n" +
                "                \"issueOrgName\": \"阳江市公安局\",\n" +
                "                \"licenseItemCode\": \"100208101\",\n" +
                "                \"expiryDate\": \"2022-06-26 00:00:00\",\n" +
                "                \"beginDate\": \"\",\n" +
                "                \"holderIdentityNum\": \"441723198707061344\",\n" +
                "                \"holderIdentityType\": \"10\",\n" +
                "                \"name\": \"居民身份证\",\n" +
                "                \"issueDate\": \"2012-06-29 00:00:00\",\n" +
                "                \"businessType\": null\n" +
                "            }, {\n" +
                "                \"holderName\": \"林国辉,程珠丝\",\n" +
                "                \"idCode\": \"粤（2020）阳江市（阳东）不动产证明第0012817号\",\n" +
                "                \"issueOrgCode\": \"114417230072878920\",\n" +
                "                \"licenseCode\": \"440000202000A55QXN\",\n" +
                "                \"issueOrgName\": \"阳江市自然资源局阳东分局\",\n" +
                "                \"licenseItemCode\": \"100212701\",\n" +
                "                \"expiryDate\": \"\",\n" +
                "                \"beginDate\": \"\",\n" +
                "                \"holderIdentityNum\": \"441723198312262416,441723198707061344\",\n" +
                "                \"holderIdentityType\": \"10\",\n" +
                "                \"name\": \"不动产登记电子证明\",\n" +
                "                \"issueDate\": \"2020-11-30 00:00:00\",\n" +
                "                \"businessType\": null\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"success\": true,\n" +
                "    \"res\": \"200\",\n" +
                "    \"msg\": \"证照摘要列表查询成功！\",\n" +
                "    \"uuid\": \"44170020120100563\"\n" +
                "}\n";

        Map<String,Object> argMap = JsonUtil.jsonStringToMap(ss);
        List<Map<String,String>> listAndTokenParams = (List<Map<String, String>>) argMap.get("listAndTokenParams");

        Map<String,Object> contentMap = JsonUtil.jsonStringToMap(s1);
        Map<String,Object> respdata = (Map<String, Object>) contentMap.get("respdata");
        List<Map<String,String>> records = (List<Map<String, String>>) respdata.get("records");

        records = records.stream().collect(Collectors
                .collectingAndThen(Collectors.toCollection(() ->
                        new TreeSet<>(new Comparator<Map<String, String>>() {
                            @Override    //重写比较器
                            public int compare(Map<String, String> o1, Map<String, String> o2) {
                                if (o1.get("licenseCode").equals(o2.get("licenseCode"))) {
                                    return 0;}return 1;}})), ArrayList::new));
        List<Map<String,String>> pdfAndBusTokenParams = new ArrayList<>();
        pdfAndBusTokenParams = records.stream().map(m-> {
            Map<String,String> pdfAndBusTokenParamMap = new HashMap<>();
            String holderIdentityNumss = m.get("holderIdentityNum");
            List<String> holderIdentityNums = Arrays.asList(holderIdentityNumss.split(","));
            m.put("holderIdentityNum",holderIdentityNums.get(0));
            /*Objects.equals(m.get("holderIdentityNum"), m2.get("identityNumber"))*/
            listAndTokenParams.stream()
                    .filter(m2->Objects.equals(m.get("holderIdentityNum"), m2.get("identityNumber"))).forEach(s->{
                /**
                 *  "identityType": "01",
                 *       "identityNumber": "420682199012101527",
                 *       "serviceItemName": "国有建设用地使用权及房屋所有权转移登记",
                 *       "serviceItemCode": "A0303",
                 *       "licenseItemCode": "100208101",
                 *       "licenseCode": "4400002020000HV1CA"
                 */
                pdfAndBusTokenParamMap.put("serviceItemName",s.get("serviceItemName"));
                pdfAndBusTokenParamMap.put("serviceItemCode",s.get("serviceItemCode"));
                pdfAndBusTokenParamMap.put("identityType",s.get("identityType"));
                pdfAndBusTokenParamMap.put("identityNumber",s.get("identityNumber"));
                pdfAndBusTokenParamMap.put("licenseItemCode",m.get("licenseItemCode"));
                pdfAndBusTokenParamMap.put("licenseCode",m.get("licenseCode"));
                pdfAndBusTokenParamMap.put("idCode",m.get("idCode"));
            });
            return pdfAndBusTokenParamMap;
            //pdfAndBusTokenParams.add(pdfAndBusTokenParamMap);
        }).collect(Collectors.toList());
        System.out.println("sssssssss");
    }
}
