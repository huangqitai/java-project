package com.qitai.htmlresolver;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlResolver {
    private Pattern p = Pattern.compile("\\t|\r|\n");
    public void htmlResolver(String html){
        Document doc = Jsoup.parse(html);
        Elements rows = doc.getElementsByTag("a");
        for (int i=0;i<rows.size();i++){
            Element row = rows.get(i);
            String href = row.attr("href");
            String prePath=href.substring(0, href.indexOf("="));
            String path=href.substring(prePath.length()+1, href.length());
            System.out.println(path);
            System.out.println(row.attr("title"));
        }
    }
    @Test
    public void testHtml(){
        String html = "<p>522</p><p style=\"line-height: 16px;\"><img src=\"http://192.168.10.61:8081/public/UEditor/dialogs/attachment/fileTypeImages/icon_doc.gif\"/><a style=\"font-size:12px; color:#0066cc;\" href=\"/electronGovernmentWebService/newsBulletin/downFile?file=%25%25jobfiles%25/201910/news/adba20c2-d858-4e2f-b478-783412640f3e/%E6%B2%B9%E8%B4%B9.docx\" title=\"油费.docx\">油费.docx</a></p><p style=\"line-height: 16px;\"><img src=\"http://192.168.10.61:8081/public/UEditor/dialogs/attachment/fileTypeImages/icon_txt.gif\"/><a style=\"font-size:12px; color:#0066cc;\" href=\"/electronGovernmentWebService/newsBulletin/downFile?file=%25%25jobfiles%25/201910/news/b2af3d9d-14a7-445a-87a1-d1f67ca11291/%E6%B3%A8%E9%87%8A%E6%8E%89%E7%9A%84%E4%BB%A3%E7%A0%81.txt\" title=\"注释掉的代码.txt\">注释掉的代码.txt</a></p><p><br/></p>";
        //System.out.println(html);
        htmlResolver(html);
    }

    @Test
    public void replaceAll(){
        //String s = "第八届中国绿色发展高层论坛2019年12月7日-9日在东莞举行,清远荣获十佳绿色城市殊荣。　　中国绿色发展高层论坛成立于2008年。自创办以来,已先后在北京、云南、广西、海南、甘肃、湖南等地成功举办了七届,已成为我国绿色发展颇具影响力的国际性高端论坛。第八届论坛在东莞举办,本届论坛上权威发布《2019中国城市绿色发展报告》,同时公布了2019年“中国十佳绿色责任企业”“中国十佳绿色新闻人物”“中国十佳绿色城市”三大奖项。清远荣获十佳绿色城市殊荣,副市长彭裕殿代表清远领取奖牌。　　自清远全面打响污染防治攻坚战以来,清远环境质量实现持续改善。2019年1月至11月底,清远市空气质量综合指数为3.83,改善率为4.5%,改善率排名全省第3；空气质量优良率为91.3%,同比提高1.5个百分点；空气质量全省排名为第17位,比去年同期提升2位。　　2019年1-10月,我市北江干流及连江、滨江等主要河流水质保持稳定,11个县级以上集中式饮用水水源水质100%达标,15个地表水国(省)考核断面水质优良比例为86.7%(13个)。根据《2019年1-9月全省城市水环境质量及变化排名情况》,我市水环境质量排名全省第7,名次同比上升4位；河流综合污染指数为4.33,较去年同期下降了17.73%,综合指数改善幅度全省排名第7；全市河流水质明显好转。2eclipse导入代码步骤.docx“迎新七件事”反馈表.doc";
        //System.out.println(s.replaceAll("\u3000+",""));
        /*Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                   *//*\n 回车(\u000a)
16             \t 水平制表符(\u0009)
17             \s 空格(\u0008)
18             \r 换行(\u000d)*//*
        Matcher m = p.matcher(s);
        s = m.replaceAll("");
        System.out.println(s);*/
        StringBuffer stringBuffer = new StringBuffer();
        System.out.println(stringBuffer.toString());
    }
    public String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
    @Test
    public void htmlResslveUtil(){
        String htmlStr = "<div id=\"conditionTreeBoxBMP1606\" class=\"easyui-window\" closed=\"true\" title=\"定义查询字段\" style=\"height:430px;display: none;\" \n" +
                "\t\tdata-options=\"iconCls:'icon-save',modal:true,footer:'#conditionTreeFooterBMP1606',resizable:false,collapsible:false,maximizable:false,minimizable:false\">\n" +
                "\t \t<table style=\"margin:5px 10px\">\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>\n" +
                "\t\t\t\t\t<select id=\"deptStatusSet1\" name=\"deptStatusSet1\" class=\"easyui-combotree\" editable=\"false\"\n" +
                "\t\t\t\t\t\tdata-options=\"onSelect:selectwork\"\n" +
                "\t\t\t\t\t\tstyle=\"width:400px\"></select>\n" +
                "\t\t\t\t</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>\n" +
                "\t\t\t\t\t<ul class=\"easyui-datalist ibase_datalist\" id=\"mappingListcondi\" style=\"width:400px;height:300px\"\n" +
                "\t\t\t\t\t\tdata-options=\"checkbox:true,singleSelect:false,checkOnSelect:true\" >\n" +
                "\t\t\t\t\t</ul>\n" +
                "\t\t\t\t</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t</table>\n" +
                " \t\n" +
                " \t\t<div id=\"conditionTreeFooterBMP1606\" style=\"padding:5px 10px;text-align:center;\">\n" +
                "\t\t\t<a href=\"#\" class=\"easyui-linkbutton\" icon=\"icon-ok\" onclick=\"getFieldTreeNode('condition')\">确定</a>\n" +
                "\t\t\t<a href=\"#\" class=\"easyui-linkbutton\" icon=\"icon-cancel\" onclick=\"$('#conditionTreeBoxBMP1606').window('close')\">关闭</a>\n" +
                "\t\t</div>\n" +
                "\t</div>";
        htmlStr = replaceBlank(htmlStr);
        System.out.println(htmlStr);

    }

    @Test
    public void date(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(simpleDateFormat.format(new Date()));

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.setLength(0);
        System.out.println(stringBuffer.length());
        if (stringBuffer.length()>0) System.out.println(stringBuffer.length());
    }
}
