package com.qitai.maputil;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectToMapUtil {
    /**
     * object对象转map
     * @param o
     * @return
     * @throws Exception
     */
    public static Map<String,Object> objectToMap(Object o) throws Exception {
        Map<String,Object> objectMap = new HashMap<>();
        Class oClass = o.getClass();
        Field[] fields = oClass.getDeclaredFields();
        List<String> fieldNames = new ArrayList<>();
        for (Field field:fields){
            String fieldName = field.getName();
            fieldNames.add(fieldName);
            PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), oClass);
            Method method = descriptor.getReadMethod();
            if ("class java.lang.String".equals(field.getType().toString())||
                    "int".equals(field.getType().toString())||
                    "class java.lang.Integer".equals(field.getType().toString())||
                    "class java.util.Date".equals(field.getType().toString())||
                    "class java.lang.Double".equals(field.getType().toString())||
                    "class java.lang.Boolean".equals(field.getType().toString())||
                    "class java.lang.Short".equals(field.getType().toString())) {
                objectMap.put(fieldName,method.invoke(o));
            }else {
                objectMap.put(fieldName,objectToMap(method.invoke(o)));
            }
        }
        return objectMap;
    }

    /**
     * map转对象
     * @param tClass
     * @param map
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T mapToObject(Class<T> tClass,Map<String,Object> map) throws Exception {
        T t = tClass.newInstance();
        Field[] fields = tClass.getDeclaredFields();
        List<String> fieldNames = new ArrayList<>();
        for (Field field:fields){
            String fieldName = field.getName();
            fieldNames.add(fieldName);
        }
        for (String key:map.keySet()){
            if (fieldNames.contains(key)){
                for (Field field:fields){
                    String fieldName = field.getName();
                    fieldNames.add(fieldName);
                    if (fieldName.equals(key)){
                        PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), tClass);
                        Method method = descriptor.getWriteMethod();
                        if (map.get(key) instanceof Map){
                            Class c1 = Class.forName(field.getType().toString().replace("class ",""));
                            method.invoke(t,mapToObject(c1,(Map<String, Object>) map.get(key)));
                        } else {
                            method.invoke(t,map.get(key));
                        }
                    }
                }
            }
        }
        return t;
    }
}
