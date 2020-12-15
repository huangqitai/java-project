package com.qitai.htmlresolver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlResolveTo {
    private Pattern p = Pattern.compile("\\s*|\t|\r|\n");
    public String readerFile(){
        String path = "D:\\解析html.txt";
        File file = new File(path);
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String s = null;
            //使用readLine方法，一次读一行
            while((s = br.readLine())!=null){
                result.append(System.lineSeparator()).append(s);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
    @Test
    public void htmlResolve(){
        String htmlStr = readerFile();
        Map<String,Object> resultMap = jsonStringToObject(htmlStr,new TypeReference<HashMap<String, Object>>(){});
        resultMap = (Map<String, Object>) resultMap.get("data");
        htmlStr = (String) resultMap.get("template");
        //htmlStr = replaceBlank(htmlStr);
        htmlStr = htmlStr.replace(htmlStr.substring(htmlStr.indexOf("<script"),htmlStr.lastIndexOf("<\\/script>")+"<\\/script>".length()),"");
        Document doc = Jsoup.parse(htmlStr);
        Elements rows = doc.getElementsByTag("i-step");
        Elements childs = rows.get(0).children();
        Element parent = rows.get(0).parent();
        rows.get(0).remove();
        for (Node node:childs){
            parent.appendChild(node);
        }
        Elements rowss = doc.getElementsByTag("i-step");
        String resultStr = doc.body().children().toString();
    }
    public String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
    public static <T> T jsonStringToObject(String json,TypeReference<T> type)
    {
        T result;
        try
        {
            result = getMapper(false).readValue(json, type);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            result = null;
        }
        return result;
    }
    private static ObjectMapper getMapper(boolean ignoreUnknown)
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, !ignoreUnknown);
        return mapper;
    }
}
