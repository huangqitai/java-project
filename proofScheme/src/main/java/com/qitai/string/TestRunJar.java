package com.qitai.string;

import com.southgis.ibase.utils.ManageJarUtil;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestRunJar {
    @Test
    public void execute(){
        try {
            String s = "b";
            //动态加载类
            //动态加载类
            Class<?> extendClass = ManageJarUtil.loadClass("file:C:\\Users\\86190\\Desktop\\test.jar", "com.qitai.string.testJar");
            Method method = null;
            method = extendClass.getDeclaredMethod("str", String.class);

            if (Modifier.isStatic(method.getModifiers()))
                method.invoke(null, s);
            else {
                Object inst = extendClass.newInstance();
                method.invoke(inst, s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
