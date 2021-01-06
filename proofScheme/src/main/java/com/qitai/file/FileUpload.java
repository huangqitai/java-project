package com.qitai.file;

import cn.hutool.http.ContentType;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

public class FileUpload {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\工作文件\\GoogleDownload\\cd68b32b-b87e-4ec1-956c-6ab7bee554b3.pdf");
        System.out.println(post("http://www.gdzwfw.gov.cn/hlwbdc_sw/testgaserver/file/upload","file",file));
    }
    public static String post(String serverUrl, String fileParamName, File file)
            throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost(serverUrl);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        // 上传的文件
        builder.addBinaryBody(fileParamName, file);
        // 设置其他参数

        HttpEntity httpEntity = builder.build();
        httpPost.setEntity(httpEntity);
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(httpPost);
        if (null == response || response.getStatusLine() == null) {
            System.out.println("Post Request For Url[{}] is not ok. Response is null"+serverUrl);
            return null;
        } else if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            System.out.println("Post Request For Url[{}] is not ok. Response Status Code is {}"+serverUrl+response.getStatusLine().getStatusCode());
            return null;
        }
        return EntityUtils.toString(response.getEntity());
    }
}
