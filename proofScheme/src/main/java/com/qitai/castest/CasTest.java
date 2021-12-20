package com.qitai.castest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author 孙林伟
 * 用代码获取ticket
 *
 */
public class CasTest {



    /**
     * @param args
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static void main(String[] args) throws ClientProtocolException, IOException {

        //String[] ltAndEventId = getLtAndEventId("http://19.130.234.69:8080/api/initLogin");
        String url = "https://19.130.234.64/cas/login?username=nh_dsy&password=abc.123";
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("username", "nh_dsy");
        param.put("password", "abc.123");
        System.out.println(getHttpString(url, param));


    }

    public static String getHttpString(String url , Map<String,Object> param) throws IOException {
        //建立http链接
        HttpClient client = new HttpClient();
        //建立post请求
        GetMethod post = new GetMethod(url);
        //设置编码
        HttpMethodParams methodParams = post.getParams();
        methodParams.setContentCharset("UTF-8");
        //执行请求
        @SuppressWarnings("unused")
        int state = client.executeMethod(post);
        post.getResponseBody();
        post.getResponseHeaders();
        String responseStr = post.getResponseHeader("Location").getValue();
        String ticketId = responseStr.substring(responseStr.indexOf("ticket")+7);

        return ticketId;
    }

    public static String[] getLtAndEventId(String url) throws IOException{
        Document doc = Jsoup.connect(url).get();
        Elements inputs = doc.getElementsByTag("input");
        String lt = "",_eventId = "",action = "";
        for(Element input : inputs){
            if("lt".equals(input.attr("name"))){
                lt = input.val();
            }
            if("_eventId".equals(input.attr("name"))){
                _eventId = input.val();
            }
        }
        Element form = doc.getElementById("fm1");
        action = form.attr("action");
        return new String[]{lt,_eventId,action};
    }
}