package com.qitai.niuke;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 2abc  3def  4ghi 5jkl 6mno 7pqrs 8tuv 9wxyz
 */
public class PhoneNumberCombination {
    private List<String> combinations = new ArrayList<>();
    private String[][] ss = {{"2","abc"},{"3","def"},{"4","ghi"},{"5","jkl"},{"6","mno"},{"7","pqrs"},{"8","tuv"},{"9","wxyz"},{},{}};
    @Test
    public void test(){
        String s = "346";
        doCombination(s);
    }
    private void doCombination(String s){
        char[] chars = s.toCharArray();
        List<String> letters = new ArrayList<>();
        for (char aChar : chars) {
            for (String[] sss : ss) {
                String charss = aChar + "";
                if (sss[0].equals(charss)) {
                    letters.add(sss[1]);
                    break;
                }
            }
        }
        System.out.println(letters);
        List<char[]> characters = new ArrayList<>();
        for (String letter : letters) {
            characters.add(letter.toCharArray());
        }
        fun(characters,"");
        System.out.println(combinations);
    }
    private void fun(List<char[]> characters,String c){
        int size = characters.size();
        if (size==1){
            char[] chars = characters.get(0);
            for (char aChar : chars) {
                combinations.add(c + aChar);
            }
        }else {
            char[] chars = characters.get(0);
            List<char[]> characterss = new ArrayList<>(characters);
            characterss.remove(0);
            for (char aChar : chars) {
                String s = c + aChar;
                fun(characterss, s);
            }
        }
    }
}
