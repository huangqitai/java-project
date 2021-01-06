package com.qitai.niuke;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SolutionA {
    /**
     * 题目描述
     * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
     * 输入描述:
     * 输入一个字符串,包括数字字母符号,可以为空
     * 返回值描述:
     * 如果是合法的数值表达则返回该数字，否则返回0
     * 示例1
     * 输入
     * 复制
     * "+2147483647"
     * 返回值
     * 复制
     * 2147483647
     */

    @Test
    public void test(){
        System.out.println(StrToInt("1a33"));
    }
    public int StrToInt(String str) {
        int i = 0;
        int n = 0;
        int flag = 1;
        if (str.equals("")){
            return 0;
        }
        char[] chars = str.toCharArray();
        if(chars[0]=='+')
        {
            i = 1;
        }else if(chars[0] == '-')
        {
            i = 1;
            flag = -1;
        }
        for(; i<chars.length ; i++) {

            if(chars[i] >= '0' && chars[i] <= '9'){
                //循环转换
                n = chars[i] - '0' + n*10;
            }
            else {
                return 0;
            }
        }
        return n*flag;
    }
    /**
     * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
     * 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
     * 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。
     * Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
     */

    @Test
    public void test1(){
        System.out.println(ReverseSentence("  "));
    }
    public String ReverseSentence(String str) {

        /*char[] chars = str.toCharArray();
        StringBuilder s = new StringBuilder();
        for (int i=chars.length-1 ;i>=0;i--){
            s.append(chars[i]);
        }
        return s.toString();*/

        String [] strings = str.split(" ");
        if(strings.length==0){
            return str;
        }
        StringBuilder ss = new StringBuilder();
        for (int i=strings.length-1;i>=0;i--){
            ss.append(strings[i]).append(" ");
        }
        return ss.substring(0,ss.lastIndexOf(" "));
    }
}
