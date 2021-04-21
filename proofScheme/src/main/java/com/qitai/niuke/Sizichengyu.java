package com.qitai.niuke;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 一、	题目：对一组词语进行离散值排列并求最大文本离散值。
 * 二、	释义：
 * 文本离散值= 所有词语距离值的平方和/N
 * N:文本中的词语总数
 * 词语距离值，这个词的尾字（向前或向后）与最近另一个首字相同的词之间的词数，取两者较小的数。
 * 如果这个词的尾字无相同首字的词，该词的距离值为离文本两端距离的小者。
 * 举例说明：以文本“舞枪弄棒 充耳不闻 闻鸡起舞 丧家之狗 关门打狗 舞文弄墨 舞刀弄枪” 为例，各词距离值如下：
 * 舞枪弄棒：0  （舞枪弄棒，棒字找不到首尾相连的词语，故距离值取距离文本首端词语数0）
 * 充耳不闻：0 （充耳不闻，和闻鸡起舞首尾相连，故距离值为0
 * 闻鸡起舞：1（找到两个舞字开头的词语，取距离值小的舞枪弄棒，词语间隔数为1）
 * 三、	具体要求：
 * 给定一个原始文本（见附件）
 *    要求：
 * 1、	计算原始文本的文本离散值；
 * 2、	重新排列该文本中的词语，生成离散值最大的文本（txt格式）；
 * 3、	输出结果文本与其文本离散值。
 */
public class Sizichengyu {
    @Test
    public void test(){
        String filePath = "D:\\工作文件\\工作资源文件\\test.txt";
        sizichengyu(filePath);
    }
    private void sizichengyu(String filePath){
        String files = readFile(filePath);
        List<String> cys = Arrays.asList(files.split(" "));
        int N = cys.size();
        List<Integer> distances = new ArrayList<>();
        for (int i = 0; i < cys.size(); i++) {
            distances.add(getDistance(cys,i));
        }
        int pows = 0;
        for (int distance :distances){
            pows += Math.pow(distance,2);
        }
        int discrete = pows/N;
        System.out.println(discrete);
    }

    // 保存生成的排列组合内容
    public List<String> Permutation = new ArrayList<String>();
    /**
     * 递归的方式计算排列组合
     * @param list  传入list.size()个集合
     * @param preStr 上一步递归中生成的排列组合
     * @return
     */
    public  void permutation( List<List<String>> list,String preStr) {
        int size = list.size();
        if(1==size){
            for(int i=0; i<list.get(0).size(); i++) {
                Permutation.add(preStr + list.get(0).get(i));
            }
        }else{
            List<String> permu = new ArrayList<String>(list.get(0));
            List<List<String>> now = new ArrayList<List<String>>(list);
            now.remove(0);
            for(int i=0; i<permu.size(); i++){
                permutation(now, preStr +permu.get(i));
            }
        }
    }

    private int getDistance(List<String> cys,int index){
        List<String> pres = cys.subList(0,index);
        List<String> sufs = cys.subList(index+1,cys.size());
        String selectStr = cys.get(index).substring(cys.get(index).length() - 1);
        int distance1 = -1;
        int distance2 = -1;
        for (int i = pres.size()-1 ;i >=0; i--) {
            String s = pres.get(i);
            if (s.contains(selectStr)){
                distance1 = index - i -1;
                break;
            }
        }
        for (int i = 0; i < sufs.size(); i++) {
            String s = sufs.get(i);
            //String ss = s.substring(s.length() - 1);
            if (s.contains(selectStr)){
                distance2 = i+ pres.size() - index;
                break;
            }
        }
        int result = 0;
        if (distance1==-1&&distance2==-1){
            result = 0;
        }
        else if (distance1==-1||distance2==-1){
            result = Math.max(distance1,distance2);
        }else {
            result = Math.min(distance1,distance2);
        }
        return result;
    }
    private String readFile(String filePath){
        File file = new File(filePath);
        try (
                BufferedReader reader = new BufferedReader(new FileReader(file));
        ) {
            StringBuilder stringBuilder = new StringBuilder();
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                stringBuilder.append(tempStr);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
