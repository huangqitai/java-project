package com.qitai.file;

import org.junit.Test;

import java.io.File;

public class FileTest {

    @Test
    public void test(){
        String path ="D:/ibase/opt/sinfo/jobfiles/00000001-0000-0000-0010-000000000001/中心2020年制定修订并印发制度及附件表格";
        File sourceFile = new File(path);
        path = path.substring(0,path.lastIndexOf("/"));
        System.out.println(path);
        //sourceFile.renameTo(new File(serverPath));
    }
}
