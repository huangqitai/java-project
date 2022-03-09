package com.qitai.niuke;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 请设计一个函数，用来判断在一个n乘m的矩阵中是否存在一条包含某长度为len的字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
 * 示例1
 * 输入：[[a,b,c,e],[s,f,c,s],[a,d,e,e]],"abcced"
 * 返回值：true
 *
 * 示例2
 * 输入：[[a,b,c,e],[s,f,c,s],[a,d,e,e]],"abcb"
 * 返回值：false
 *
 * [[a,b,c,e],[s,f,c,s],[a,d,e,e]],"see"
 */
public class Juzheng {
    boolean isTrue = false;
    @Test
    public void test(){
        //char[][] matrix = new char[3][4];
        char[][] matrix = buildChars("[[a,b,c,e],[s,f,c,s],[a,d,e,e]]");
        String word = "abcced";
        System.out.println(hasPath(matrix,word));
    }
    public boolean hasPath (char[][] matrix, String word) {
        if (matrix.length==0||matrix[0].length==0){
            return false;
        }
        char startWord = word.charAt(0);
        int[][] flags = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                //找到第一个字符所在的位置
                if (matrix[i][j]==startWord){
                    //从首字符开始递归，k=1为查找第二个字符
                    fun(i,j,matrix,word,1,flags);
                }
            }
        }
        return isTrue;
    }
    public void fun(int i,int j,char[][] matrix,String word,int k,int[][] flags){
        //当前走过的位置置为1
        flags[i][j]=1;
        //有一条路走通则返回正确
        if(isTrue) {
            return;
        }
        //按照路径走下去，当字符标志位k等于字符串长度时则表明完全走完，为正确
        if (k>=word.length()){
            isTrue = true;
            return;
        }
        //向上
        if (i>0&&matrix[i-1][j]==word.charAt(k)&&flags[i-1][j]!=1) {

            fun(i-1, j, matrix, word, k+1, flags);
        }
        //向下
        if (i< matrix.length-1&&matrix[i+1][j]==word.charAt(k)&&flags[i+1][j]!=1){

            fun(i+1, j, matrix, word, k+1, flags);
        }
        //向右
        if (j< matrix[i].length-1&&matrix[i][j+1]==word.charAt(k)&&flags[i][j+1]!=1){

            fun(i, j+1, matrix, word, k+1, flags);
        }
        //向左
        if (j>0&&matrix[i][j-1]==word.charAt(k)&&flags[i][j-1]!=1){

            fun(i, j-1, matrix, word, k+1, flags);
        }
        //所有的方向都没有匹配上，此路不通，将当前位置置为0，以便其它路使用
        flags[i][j]=0;
    }
    public char[][] buildChars(String str){
        //[[a,b,c,e],[s,f,c,s],[a,d,e,e]]
        if (str.startsWith("[[")){
            str = str.substring(1);
        }
        if (str.endsWith("]]")){
            str = str.substring(0,str.length()-1);
        }
        Pattern p = Pattern.compile("\\[.*?\\]");
        Matcher matcher = p.matcher(str);
        List<String> strs = new ArrayList<>();
        while (matcher.find()){
            //匹配双字节字符
            strs.add(matcher.group());
        }
        String str2 = strs.get(0);
        str2 = str2.replace("[","");
        str2 = str2.replace("]","");
        int y = str2.split(",").length;
        char[][] chars = new char[strs.size()][y];
        int i = 0;
        for (String str1:strs){
            str1 = str1.replace("[","");
            str1 = str1.replace("]","");
            String[] strss = str1.split(",");
            for (int j = 0; j < strss.length; j++) {
                chars[i][j] = strss[j].charAt(0);
            }
            i++;
        }
        return chars;
    }
}
