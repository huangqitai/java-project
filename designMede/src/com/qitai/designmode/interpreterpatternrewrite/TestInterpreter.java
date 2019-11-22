package com.qitai.designmode.interpreterpatternrewrite;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestInterpreter {
    public static void main(String[] args) {

        try {
            BufferedReader  reader = new BufferedReader(new FileReader("programRe.txt"));
            String line=null;
            while((line=reader.readLine())!=null){
                System.out.println("源程序为："+line);
                Node node=new ProgramNode();
                node.parse(new Context(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
