package com.qitai.string;

import org.junit.Test;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {
    @Test
    public void test(){
        String jsonStr = "{\n" +
                "        \"QXDM\": \"区县代码/行政区划\",\n" +
                "        \"YWH\": \"业务号\",\n" +
                "        \"SYSYWH\": \"上一手业务号\",\n" +
                "        \"QLLX\": \"权利类型\",\n" +
                "        \"DJLX\": \"登记类型\",\n" +
                "        \"CJBM\": \"场景编码\",\n" +
                "        \"CJMC\": \"场景名称\",\n" +
                "        \"SLRY\": \"受理人员\",\n" +
                "        \"SLSJ\": \"受理时间\",\n" +
                "        \"SLJG\": \"受理机构/登记机构\",\n" +
                "        \"YWRQD\": [{\n" +
                "                \"RYMC\": \"人员名称\",\n" +
                "                \"ZJZL\": \"证件种类\",\n" +
                "                \"ZJH\": \"证件号\",\n" +
                "                \"RYDH\": \"人员电话\",\n" +
                "                \"FRMC\": \"法人名称\",\n" +
                "                \"FRDH\": \"法人电话\",\n" +
                "                \"DLRMC\": \"代理人名称\",\n" +
                "                \"DLRDH\": \"代理人电话\",\n" +
                "                \"DLJG\": \"代理机构\"\n" +
                "        }],\n" +
                "        \"QLRQD\": [{\n" +
                "                \"RYMC\": \"人员名称\",\n" +
                "                \"ZJZL\": \"证件种类\",\n" +
                "                \"ZJH\": \"证件号\",\n" +
                "                \"RYDH\": \"人员电话\",\n" +
                "                \"FRMC\": \"法人名称\",\n" +
                "                \"FRDH\": \"法人电话\",\n" +
                "                \"DLRMC\": \"代理人名称\",\n" +
                "                \"DLRDH\": \"代理人电话\",\n" +
                "                \"DLJG\": \"代理机构\"\n" +
                "        }],\n" +
                "        \"BDCDYQD\": [{\n" +
                "                \"BDCDYH\": \"不动产单元号\",\n" +
                "                \"BDCLX\": \"不动产类型\",\n" +
                "                \"QLXZ\": \"权利性质\",\n" +
                "                \"ZL\": \"坐落\",\n" +
                "                \"MJ\": \"面积\",\n" +
                "                \"JZMJ\": \"建筑面积\",\n" +
                "                \"ZYJZMJ\": \"专有建筑面积\",\n" +
                "                \"FTJZMJ\": \"分摊建筑面积\",\n" +
                "                \"MJDW\": \"面积单位\",\n" +
                "                \"YT\": \"用途\",\n" +
                "                \"GHYT\": \"规划用途\",\n" +
                "                \"FWXZ\": \"房屋性质\"\n" +
                "         \n" +
                "        }],\n" +
                "        \"CLQD\": [{\n" +
                "                \"CLMC\": \"材料名称\",\n" +
                "                \"LX\": \"类型\",\n" +
                "                \"SL\": \"数量\",\n" +
                "                \"CLLJ\": \"材料路径\"\n" +
                "        }]\n" +
                "}\n";

        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(jsonStr);
        String argJsonStr = m.replaceAll("");
        System.out.println(argJsonStr);
    }


    @Test
    public void test1(){
        String kq = "1.50(有事)";
        if (kq.contains("(")){
            kq = kq.replace(kq.substring(kq.indexOf("("),kq.indexOf(")")+1),"");
        }

        System.out.println(Double.parseDouble(kq));
    }

    @Test
    public void test2(){
        String kq = "D:\\ibase\\opt\\sinfo\\jobfiles\\6d569eb1-0638-42c7-ad3b-b19405f2438e\\中心2020年修订并印发制度及附件表格";
        kq = kq.substring(0,kq.lastIndexOf(File.separator)+1);

        System.out.println(kq);
    }

    @Test
    public void test3(){
        String BLJD="发证中";
        String regtype;
        switch (BLJD){
            case "受理中":regtype = "已受理";break;
            case "审核中":regtype = "已受理";break;
            case "发证中":regtype = "已出证";break;
            case "已办结":regtype = "已办结";break;
            default:regtype = BLJD;break;
        }
        System.out.println(regtype);
    }
}
