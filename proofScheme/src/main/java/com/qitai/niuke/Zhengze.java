package com.qitai.niuke;

import org.junit.Test;

/**
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。
 * 1.模式中的字符'.'表示任意一个字符
 * 2.模式中的字符'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，
 * 但是与"aa.a"和"ab*a"均不匹配
 *
 * 数据范围:
 * 1.str 只包含从 a-z 的小写字母。
 * 2.pattern 只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
 * 3. 0≤str.length≤26
 * 4. 0≤pattern.length≤26
 */
public class Zhengze {
    String[] chars = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    boolean isTrue = false;
    @Test
    public void test(){
        //System.out.println(match("aaaaaaaaaaaaab","a*a*a*a*a*a*a*a*a*a*c"));
        System.out.println(match("bbabacccbcbbcaaab","a*b*a*a*c*aa*c*bc*"));
        //System.out.println(match("a","a*"));
        //System.out.println(match("a","ab*"));
    }

    /**
     * 别人写的，运行时间较短
     * @param str
     * @param pattern
     * @return
     */
    public boolean match (String str, String pattern) {
        // write code here
        int n = str.length();
        int m = pattern.length();
        boolean[][] f = new boolean[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                //分成空正则和非空正则两种
                if (j == 0) {
                    f[i][j] = i == 0;
                } else {
                    //非空正则分为两种情况 * 和 非*
                    if (pattern.charAt(j - 1) != '*') {
                        if (i > 0 && (str.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '.')) {
                            f[i][j] = f[i - 1][j - 1];
                        }
                    } else {
                        //碰到 * 了，分为看和不看两种情况
                        //不看
                        if (j >= 2) {
                            f[i][j] |= f[i][j - 2];
                        }
                        //看
                        if (i >= 1 && j >= 2 && (str.charAt(i - 1) == pattern.charAt(j - 2) || pattern.charAt(j - 2) == '.')) {
                            f[i][j] |= f[i - 1][j];
                        }
                    }
                }
            }
        }
        return f[n][m];
    }

    /**
     * 自己写的暴力递归，运行时间较久
     * @param str
     * @param pattern
     * @return
     */
    public boolean match1 (String str, String pattern) {
        char[] patterns = pattern.toCharArray();
        for (int i = 0; i < patterns.length; i++) {
            if (!"*".equals(String.valueOf(patterns[i])) &&!".".equals(String.valueOf(patterns[i])) &&!str.contains(String.valueOf(patterns[i]))&& (i+1>= patterns.length||!"*".equals(String.valueOf(patterns[i + 1])))){
                return false;
            }
        }
        fun(str,pattern,0);
        return isTrue;
    }
    public void fun(String str,String pattern,int index){
        if (isTrue){
            return;
        }
        if (pattern.equals(str)){
            isTrue = true;
            return;
        }
        if (index>=pattern.length()){
            return;
        }
        if ("".equals(pattern)){
            return;
        }
        if (index>=str.length()&&!str.equals(pattern.substring(0,str.length()))&& !"*".equals(String.valueOf(pattern.charAt(index)))){
            return;
        }
        if (!str.substring(0,Math.min(index,str.length())).equals(pattern.substring(0,index))&&!"*".equals(String.valueOf(pattern.charAt(index)))){
            return;
        }
        if (".".equals(String.valueOf(pattern.charAt(index)))){
            if (index+1<pattern.length()&&"*".equals(String.valueOf(pattern.charAt(index+1)))){
                String pre = ".";
                for (int i = 0; i <= str.length(); i++) {
                    StringBuilder replaceStr = new StringBuilder();
                    for (int j = 0; j < i; j++) {
                        replaceStr.append(pre);
                    }
                    String pattern1 = pattern.replaceFirst(pre+"\\*", replaceStr.toString());
                    fun(str, pattern1, index);
                }
            }else {
                for (String aChar : chars) {
                    if (!str.contains(aChar)){
                        continue;
                    }
                    String pattern1 = pattern.replaceFirst("\\.", aChar);
                    fun(str, pattern1, index + 1);
                }
            }
        }
        else if ("*".equals(String.valueOf(pattern.charAt(index)))){
            String pre = String.valueOf(pattern.charAt(index-1));
            for (int i = 0; i <= str.length(); i++) {
                StringBuilder replaceStr = new StringBuilder();
                for (int j = 0; j < i; j++) {
                    replaceStr.append(pre);
                }
                String pattern1 = pattern.replaceFirst(pre+"\\*", replaceStr.toString());
                fun(str, pattern1, index + i-1);
            }
        }
        else {
            fun(str, pattern, index+1);
        }
    }
}
