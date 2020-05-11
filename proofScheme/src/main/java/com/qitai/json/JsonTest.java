package com.qitai.json;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonTest {

    @Test
    public void String6(){
        String s = "8.png|%%jobfiles%/202003/2020033000014/5e4602af-27c7-4e7b-a534-dcb47898badc.png";
        List list = new ArrayList<>(Arrays.asList(s));
        System.out.println(list.size());
    }
    @Test
    public void String3(){
        String para = "{\"resultcode\":\"1\",\"resultmsg\":\"查询成功\",\"result\":{\"sqrxx\":[{\"XH\":\"1\",\"JID\":\"201802230057\",\"FSQRLX\":\"房地产权利人\",\"FSQRMC\":\"中海宏洋惠州城市建设开发有限公司\",\"FZJZL\":\"营业执照\",\"FZJHM\":\"9114413006633394357\",\"FDWXZ\":\"企业\",\"FHJSZSS\":\"广东\",\"FGJDQ\":\"中国\",\"FSSHY\":\"\",\"FGZDW\":\"\",\"FLXR\":\"\",\"FLXDH\":\"0752-2880701\",\"FTXDZ\":\"\",\"FYB\":\"\",\"FDZYJ\":\"\",\"FXB\":\"\",\"FGYQK\":\"单独所有\",\"FQLBL\":\"\",\"FFRMC\":\"\",\"FFRZJZL\":\"\",\"FFRZJHM\":\"\",\"FFRDH\":\"\",\"FDLRMC\":\"\",\"FDLRZJZL\":\"\",\"FDLRZJHM\":\"\",\"FDLRDH\":\"\",\"FDLJG\":\"\"}],\"cqxx\":[{\"RID\":\"947358\",\"JID\":\"201802230057\",\"FQSZT\":\"抵押\",\"FDJSJ\":\"2018-03-15 10:59:08\",\"FFWXZ\":\"商品房\",\"FJGSJ\":\"2017-09-30 00:00:00\",\"FGYFS\":\"单独所有\",\"FQLXZ\":\"出让\",\"FQLLX\":\"国有建设用地使用权/房屋所有权\",\"FTDSYQSSJ\":\"2007-11-21 00:00:00\",\"FTDSYJSSJ\":\"2077-11-21 00:00:00\",\"FCQZH\":\"粤（2018）惠州市不动产权第0025363号\",\"FQLR\":\"中海宏洋惠州城市建设开发有限公司\",\"FZJHM\":\"9114413006633394357\",\"FBDCDYH\":\"441302007014GB00078F00190006\",\"FFWBM\":\"HZ10420527\",\"FZL\":\"惠州市惠城区旭日二路3号中信水岸城花园六期第63栋17层03号房\",\"FJZMJ\":\"112.02\",\"FFTJZMJ\":\"23.28\",\"FZYJZMJ\":\"88.74\",\"FGHYT\":\"住宅\",\"FFWJG\":\"钢筋混凝土结构\",\"FSZC\":\"17层\",\"FZCS\":\"32\",\"FZDMJ\":\"128286.21\",\"FTDYT\":\"城镇住宅用地\",\"FTDSYQMJ\":\"128286.21\",\"FQXDM\":\"441302\"}]}}";
        String qxdm = "asd42301";
        System.out.println(qxdm.replaceAll(".*[^\\d](?=(\\d+))",""));
    }

    @Test
    public void String5(){
        String strJson = "{\"xzqhdm\":\"441302\",\"qlr\":\"中国农业银行股份有限公司阳江江城支行\",\"cqzh\":\"粤〔2016〕阳江市不动产证明第0005156号\",\"cqlx\":\"FW\",\"cqlxname\":\"国有建设用地使用权及房屋所有权抵押首次登记\",\"FDLRZJHM\":\"\",\"FDLRZJZL\":\"身份证\"}";
        String[] afters = strJson.split("\"123456\"");
        if (afters.length>1){
            System.out.println(afters[1]);
        }
    }

    private String getJidOrQxdm(String strJson,String field){
        String[] afters = strJson.split("\""+field+"\"").length>1?strJson.split("\""+field+"\""):strJson.split("\""+field.toUpperCase()+"\"");
        String value = "";
        if (afters.length>1){
            String after = afters[1];
            Pattern p=Pattern.compile("\"(\\w+)\"");
            Matcher m=p.matcher(after);
            while(m.find()){
                value = m.group(1);
                break;
            }
        }
        return value;
    }
    @Test
    public void String4(){
        String strJson = "{\"xzqhdm\":\"441302\",\"qlr\":\"中国农业银行股份有限公司阳江江城支行\",\"cqzh\":\"粤〔2016〕阳江市不动产证明第0005156号\",\"cqlx\":\"FW\",\"cqlxname\":\"国有建设用地使用权及房屋所有权抵押首次登记\",\"FDLRZJHM\":\"\",\"FDLRZJZL\":\"身份证\",\"SBLSH\":\"123456789\"}";
        String xzqhdm = getJidOrQxdm(strJson,"xzqhdm");
        String jid = getJidOrQxdm(strJson,"sblsh");

        System.out.println(jid+"    "+xzqhdm);

    }
    @Test
    public void String2(){
        String phoneNumber = "18366666666";
        int phoneLen = phoneNumber.length();
        String pre = phoneNumber.substring(0,3);
        String after = phoneNumber.substring(phoneLen-4);
        int afterLen = after.length();
        int preLen = pre.length();
        for (int i=0;i<phoneLen-preLen-afterLen;i++){
            pre += "*";
        }
        System.out.println(pre+after);
    }

    @Test
    public void jsonString(){
        String json = "{\"msg\":\"查询结果为空，请查询名下的楼盘是否满足条件！\",\"isVisible\":\"false\"}";
        System.out.println(json);
    }

    @Test
    public void testString1(){
        String s = "operateUserName:超级管理员:等于||methodName:getOperationLogListByLike:包含";
        String[] ss = s.split("\\|\\|");
        System.out.println(ss[0]);
    }
    @Test
    public void testString(){
        String name = "诸葛钢铁";
        name = "黄启太";
        int len = name.length();
        if (len>3){
            name = name.substring(0,2);
            for (int i=0;i<=len-name.length();i++){
                name += "*";
            }
        }else {
            name = name.substring(0,1);
            for (int i=0;i<=len-name.length();i++){
                name += "*";
            }
        }
        System.out.println(name);


        String cardId = "522427199409074010";
        int idLen = cardId.length();
        String start = cardId.substring(0,3);
        String end = cardId.substring(cardId.length()-4,cardId.length());
        String center = cardId.substring(3,cardId.length()-4);
        for (int i=0;i<center.length();i++){
            start += "*";
        }
        cardId = start+end;
        System.out.println(cardId);
    }
}
