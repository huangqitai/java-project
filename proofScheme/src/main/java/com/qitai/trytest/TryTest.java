package com.qitai.trytest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TryTest {
    public void test(){
        try(
                InputStream is = new FileInputStream("...");
                OutputStream os = new FileOutputStream("...");
        ){
            //...
        }catch (IOException e) {
            //...
        }
    }
}
