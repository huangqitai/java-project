package com.qitai.xmlparse;

import com.qitai.utils.CheckUtil;
import com.qitai.utils.json.JsonReturn;
import com.qitai.utils.json.JsonUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

public class XmlParseTest {

    @Test
    public void test1(){
        Object s = "{\"HH\":\"0029\",\"BDCQZH\":\"粤(2021)佛南不动产权第0083554号\",\"CH\":\"-1\",\"YWRQD\":[{\"YWRDH\":\"13533335489\",\"YWRMC\":\"邱一凡\",\"YWRZJH\":\"91440605081066385F\",\"YWRZJLX\":\"51\",\"YWRZJXZDZ\":\"http://19.130.234.64/proxyWebService/SocietyDownloadFile?filename=http%3A%2F%2F19.128.104.252%3A28888%2Fx5%2FUI%2FEstateSys_X%2FCommonFuncs%2FViewSfClDz.j%3Ffile%3Dpdf%26docIds%3DE69DF06C136A4AFE872FB36F65FAAA61\"}],\"YWH\":\"YC44060021052600002\",\"ZRZH\":\"440605007028GB06203F0001\",\"BDCQZXZDZ\":\"http://19.130.234.64/proxyWebService/SocietyDownloadFile?filename=http%3A%2F%2F19.128.104.252%3A28888%2Fx5%2FUI%2FEstateSys_X%2FCommonFuncs%2FViewSfClDz.j%3Ffile%3Djpg%26docIds%3D060389DFAAF143E696239DA4FA0D2CEF\",\"BDCDYH\":\"440605007028GB06203F00010029\",\"BLSX\":2,\"SLJG\":\"系统超级管理员组\",\"ZL\":\"广东省佛山市南海区大沥镇盐步蟾龙北路1号珑耀华府负一层376号汽车位\",\"QLRQD\":[{\"QLRDH\":\"13533335489\",\"QLRMC\":\"邱一凡\",\"QLRYHKH\":\"\",\"QLRYHKKHH\":\"\",\"QLRZJH\":\"441224197812013226\",\"QLRZJLX\":\"01\",\"QLRZJXZDZ\":\"http://19.130.234.64/proxyWebService/SocietyDownloadFile?filename=http%3A%2F%2F19.128.104.252%3A28888%2Fx5%2FUI%2FEstateSys_X%2FCommonFuncs%2FViewSfClDz.j%3Ffile%3Djpg%26docIds%3DCF447C484FA346D8A4F35BD5BDC588AD\"}],\"QXDM\":\"440605\",\"YWZL\":4,\"QXMC\":\"南海区\",\"GDHH\":\"\"}";
        System.out.println((String) s);
    }

    @Test
    public void test(){
        String s = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                " <soap:Body>\n" +
                "\t<ns2:getRightInfo_houseResponse xmlns:ns2=\"http://zzcx.selfQuery.FsPrgFolder.PrjCustomFolder.EstateSys/\">\n" +
                "<return>[{\"cfdj\":\"无\",\"dyqdj\":\"无\",\"gyqx\":\"无\",\"mj\":80.8,\"ygdj\":\"无\",\"yt\":\"住宅\",\"yydj\":\"无\",\"zl\":\"佛山市禅城区南庄镇东村东井村194号\"}]</return>\n" +
                "     </ns2:getRightInfo_houseResponse>\n" +
                "   </soap:Body>\n" +
                " </soap:Envelope>";
        String value = parseResponse(s,"getRightInfo_houseResponse");
        System.out.println(value);
        JsonReturn<Object> jsonReturn = new JsonReturn<>();
        jsonReturn.setResult(JsonUtil.jsonStringToList(value));
        jsonReturn.setMsg("获取数据成功");
        jsonReturn.setSuccess();
        System.out.println(jsonReturn);
    }

    @Test
    public void test2(){
        String s = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "    <soap:Body>\n" +
                "        <ns2:loginResponse xmlns:ns2=\"http://com.butone.bizservice\">\n" +
                "            <return>96891A23E9705C55E47FA244998EEA90</return>\n" +
                "        </ns2:loginResponse>\n" +
                "    </soap:Body>\n" +
                "</soap:Envelope>";
        System.out.println(parseResponse(s,"loginResponse"));
    }
    public String parseResponse(String soapXml,String returnServiceName) {
        try {
            String value = "";
            if (!CheckUtil.isNullorEmpty(soapXml)) {
                //解析xml格式内容
                Document document = DocumentHelper.parseText(soapXml);
                Element root = document.getRootElement();
                root = root.element("Body");
                if (root != null) {
                    root = root.element(returnServiceName);
                    if (root != null) {
                        root = root.element("return");
                        if (root != null) {
                            value = root.getText();
                        }
                    }
                }
            }
            return value;
        }catch (Exception e){
            return "";
        }
    }
}
