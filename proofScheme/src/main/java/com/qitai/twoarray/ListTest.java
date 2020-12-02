package com.qitai.twoarray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListTest {
        public static void main(String[] args) {

        List<Map<String,Object>> maps = new ArrayList<>();
        HashMap<String,Object> map = new HashMap<>();
        map.put("tagId",10001);
        map.put("tagCode","10001");
        map.put("tagName","今日招募");
        map.put("tagCategoryId",100);
        map.put("type",2);
        map.put("tagValueType",1);
        map.put("tagValueId",1000101);
        map.put("value","是");
        map.put("totalMemberCount",0);
        maps.add(map);

        map =  new HashMap<>();
        map.put("tagId",10001);
        map.put("tagCode","10001");
        map.put("tagName","今日招募");
        map.put("tagCategoryId",100);
        map.put("type",2);
        map.put("tagValueType",1);
        map.put("tagValueId",1000102);
        map.put("value","否");
        map.put("totalMemberCount",0);
        maps.add(map);

        map =  new HashMap<>();
        map.put("tagId",10002);
        map.put("tagCode","10002");
        map.put("tagName","本月招募");
        map.put("tagCategoryId",100);
        map.put("type",2);
        map.put("tagValueType",1);
        map.put("tagValueId",1000201);
        map.put("value","是");
        map.put("totalMemberCount",2);
        maps.add(map);

        map =  new HashMap<>();
        map.put("tagId",10002);
        map.put("tagCode","10002");
        map.put("tagName","本月招募");
        map.put("tagCategoryId",100);
        map.put("type",2);
        map.put("tagValueType",1);
        map.put("tagValueId",1000202);
        map.put("value","否");
        map.put("totalMemberCount",1);
        maps.add(map);

        map =  new HashMap<>();
        map.put("tagId",10004);
        map.put("tagCode","10004");
        map.put("tagName","妈妈类型");
        map.put("tagCategoryId",2);
        map.put("type",2);
        map.put("tagValueType",1);
        map.put("totalMemberCount",1);
        maps.add(map);

        List<Map<String,Object>> oldList = maps;


        List<Map<String,Object>> newList = new ArrayList<Map<String,Object>>();

        for(int i=0; i<oldList.size(); i++){

            Map<String,Object> oldMap = oldList.get(i);

            /* System.err.println(oldMap.get("value"));*/

            Map<String,Object> map1 = new HashMap<>();
            map1.put("tagValueType",oldMap.get("tagValueType"));
            map1.put("value",oldMap.get("value"));
            map1.put("totalMemberCount",oldMap.get("totalMemberCount"));


            List<Map<String ,Object>> list = new ArrayList<>();
            list.add(map1);
            oldMap.put("tagValueVoList",list);

            if(newList.size()>0){
                boolean isContain = false;
                for(int j=0; j<newList.size();j++){
                    Map<String,Object> newMap = newList.get(j);
                    if(newMap.get("tagId").equals(oldMap.get("tagId"))){
                        for (int k = 0; k < newList.size(); k++) {

                            if(newList.get(k).get("tagId").equals(oldMap.get("tagId"))){
                                Map<String,Object> map2 = new HashMap<>();
                                map2.put("tagValueType",oldMap.get("tagValueType"));
                                map2.put("value",oldMap.get("value"));
                                map2.put("totalMemberCount",oldMap.get("totalMemberCount"));
                                List<Map<String,Object>> list1= (List<Map<String, Object>>) newList.get(k).get("tagValueVoList");
                                list1.add(map2);
                                oldMap.put("tagValueVoList",list1);
                                newList.remove(k);

                                oldMap.remove("tagValueType");
                                oldMap.remove("value");
                                oldMap.remove("totalMemberCount");
                                newList.add(oldMap);




                            }
                        }
                        isContain = true;
                        break;
                    }
                }

                if(!isContain){
                    oldMap.remove("tagValueType");
                    oldMap.remove("value");
                    oldMap.remove("totalMemberCount");

                    newList.add(oldMap);
                }

            }else{
                oldMap.remove("tagValueType");
                oldMap.remove("value");
                oldMap.remove("totalMemberCount");
                newList.add(oldMap);
            }
        }


        for (int i = 0; i < newList.size(); i++) {
            List<Map<String,Object>> list = (List<Map<String, Object>>) newList.get(i).get("tagValueVoList");
            int count = 0;
            boolean bol = false;
            for (int j = 0; j < list.size(); j++) {
                bol = false;
                if(list.get(j).get("value")!=null){

                    if(list.get(j).get("value").toString().equals("是") || list.get(j).get("value").toString().equals("否")) {
                        bol = true;
                        count+=Integer.parseInt(list.get(j).get("totalMemberCount").toString());

                    }
                }
            }
            if(bol){
                bol = false;
                newList.get(i).put("totalMemberCount",count);
                newList.get(i).remove("tagValueVoList");
            }
        }

        for (Map<String, Object> stringObjectMap : newList) {
            System.err.println(stringObjectMap.toString());
        }
    }
}
