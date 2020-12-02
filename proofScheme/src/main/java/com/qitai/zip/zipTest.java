package com.qitai.zip;

import org.junit.Test;

import java.io.File;

public class zipTest {

    @Test
    public void ziptest(){
        try {
            ZipUtil.zip("E:\\tmp\\logs.zip",new File("E:\\tmp\\logs"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ziptest1(){
        try {
            File file = new File("D:\\ibase\\opt\\sinfo\\jobfiles\\6d569eb1-0638-42c7-ad3b-b19405f2438e\\中心2020年指定修订并印发制度及附件表格");
            if (file.exists()){
                System.out.println("cunzai");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void unZipTest(){
        ZipUtil.unZip("E:\\tmp\\logs.zip");
        /*String f = "E:\\tmp";
        File file = new File(f);
        String[] test=file.list();
        for(int i=0;i<test.length;i++){
            System.out.println(test[i]);
        }
        System.out.println("------------------");
        String fileName = "";
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                System.out.println("文   件："+tempList[i]);
                fileName = tempList[i].getName();
                System.out.println("文件名："+fileName);
            }
            if (tempList[i].isDirectory()) {
                System.out.println("文件夹："+tempList[i]);
            }
        }*/
    }
}
