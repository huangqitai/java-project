package com.qitai.designmode.decorativepattern;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FilterInputTest {
    @Test
    public void testFilterInput(){
        int c;
        try  {
            System.out.println(new File(".").getAbsolutePath());
            InputStream in = new LowerCaseInputStream(
                    new BufferedInputStream(
                            new FileInputStream("./src/com/qitai/designmode/decorativepattern/test.txt")));

            while ((c = in.read())>0){
                System.out.print((char) c);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
