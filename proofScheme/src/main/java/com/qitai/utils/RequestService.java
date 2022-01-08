package com.qitai.utils;

import com.qitai.utils.CheckUtil;
import com.qitai.utils.Constant;
import com.qitai.utils.ConvertUtil;
import com.qitai.utils.UUIDHelper;
import com.qitai.utils.exception.ExceptionUtil;
import com.qitai.utils.exception.HttpStatusCodeException;
import com.qitai.utils.json.JsonUtil;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RequestService
{
	private static final String HTTPS_PREFIX = "https";
	
	/**
	 * 简单URL请求，如果返回4xx~5xx，抛出HttpStatusCodeException（其中statusCode为具体的http status
	 * code）
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String doRequest(String path) throws IOException
	{
		URL url = new URL(path); // 把字符串转换为URL请求地址
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();// 打开连接
		connection.setReadTimeout(Constant.HTTP_READ_TIMEOUT);
		connection.setConnectTimeout(Constant.HTTP_CONNTION_TIMEOUT);
		connection.setRequestProperty("connection", "Keep-Alive");
		connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) Chrome/75.0");
		connection.setRequestProperty("Accept-Charset", "utf-8");
		// setCookie(connection);
		connection.setDoInput(true);
		connection.connect();// 连接会话

		String result = readResponsContent(connection);

		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求（以x-www-form-urlencoded格式发送，内容为utf-8编码）；
	 * 如果返回4xx~5xx，抛出HttpStatusCodeException（其中statusCode为具体的http status
	 * @param url
	 *          发送请求的 URL
	 * @param param
	 *          请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 * @throws IOException 
	 */
	public static String sendPost(String url, String param)
	{
		return sendJsonPost(url, "application/x-www-form-urlencoded; charset=UTF-8", param);
	}

	/**
	 * 向指定 URL 发送POST方法的请求（以x-www-form-urlencoded格式发送，内容为utf-8编码）；
	 * 如果返回4xx~5xx，抛出HttpStatusCodeException（其中statusCode为具体的http status
	 * @param url 发送请求的 URL
	 * @param header 请求头部信息(Key为请求头名，Value为值）
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 * @throws IOException 
	 */
	public static String sendPost(String url, Map<String, String> header, String param)
	{
		return sendPost(url, "application/x-www-form-urlencoded; charset=UTF-8", header, param);
	}

	/**
	 * 向指定 URL 发送Json串的POST方法请求；
	 * 如果返回4xx~5xx，抛出HttpStatusCodeException（其中statusCode为具体的http status
	 * @param url 发送请求的 URL
	 * @param ContentType 内容格式，如果为空，默认为“application/json; charset=UTF-8”
	 * @param param 请求内容，json串。
	 * @return 所代表远程资源的响应结果
	 * @throws IOException 
	 */
	public static String sendJsonPost(String url, String ContentType, String param)
	{
		if(CheckUtil.isNullorEmpty(ContentType))
			ContentType="application/json; charset=UTF-8";
		return sendPost(url, ContentType, null, param);
	}

	/**
	 * 向指定 URL 发送POST方法的请求（以x-www-form-urlencoded格式发送，内容为utf-8编码）；
	 * 如果返回4xx~5xx，抛出HttpStatusCodeException（其中statusCode为具体的http status
	 * @param url 发送请求的 URL
	 * @param ContentType 内容格式，需要与param值的格式匹配。如果为空，默认为“text/plain; charset=UTF-8”
	 * @param header 请求头部信息(Key为请求头名，Value为值）
	 * @param param 请求参数，可以是json串，或以&分隔的名值对，需要与ContentType格式匹配。
	 * @return 所代表远程资源的响应结果
	 * @throws IOException
	 */
	public static String sendPost(String url, String ContentType, Map<String, String> header, String param)
	{
		try{
			String result = "";

			if(CheckUtil.isNullorEmpty(ContentType))
				ContentType="text/plain; charset=UTF-8";

			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) Chrome/75.0");
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("Content-Type", ContentType);
			if (header != null)
			{
				for(Map.Entry<String, String> entry : header.entrySet())
				{
					conn.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}

			// setCookie(conn);
			conn.setReadTimeout(Constant.HTTP_READ_TIMEOUT);
			conn.setConnectTimeout(Constant.HTTP_CONNTION_TIMEOUT);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);

			byte[] byData = param.getBytes("UTF-8");
			conn.getOutputStream().write(byData);
			conn.connect();

			result = readResponsContent(conn);

			return result;
		}catch(IOException ex){
			throw new HttpStatusCodeException(400, url+":"+ex.getMessage());
		}
	}

	/**
	 * 发送表单数据的请求（数据可以包含二进制内容），非二进制内容以utf-8编码（以multipart/form-data格式传送）；
	 * 如果返回4xx~5xx，抛出HttpStatusCodeException（其中statusCode为具体的http status
	 * @param url
	 *          请求地址
	 * @param header
	 *          请求头额外信息
	 * @param data
	 *          发送的数据，Key为属性名(如果是二进制内容，Key还要包含mime类型及文件名，格式：prop::mime::filename，
	 *          如：image::image/jpeg::abcd.jpg)，
	 *          Value为属性值（可以是基础类型，字符串，byte[]，InputStream对象），
	 *          如果要传递的是日期类型，由调用者转换成长整型的毫秒值或字符串值。
	 * @return
	 * @throws IOException
	 */
	public static String sendFormDataRequest(String url, Map<String, String> header, Map<String, Object> data)
			throws IOException
	{
		String result = "";
		String bound = "------" + UUIDHelper.getZipString();

		URL realUrl = new URL(url);
		// 打开和URL之间的连接
		HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
		// 设置通用的请求属性
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) Chrome/75.0");
		conn.setRequestProperty("Accept-Charset", "utf-8");
		conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + bound);

		if (header != null)
		{
			for(Map.Entry<String, String> item : header.entrySet())
			{
				conn.setRequestProperty(item.getKey(), item.getValue());
			}
		}

		// setCookie(conn);
		conn.setReadTimeout(Constant.HTTP_READ_TIMEOUT);
		conn.setConnectTimeout(Constant.HTTP_CONNTION_TIMEOUT);
		// 发送POST请求必须设置如下两行
		conn.setDoOutput(true);

		OutputStream os = conn.getOutputStream();
		if (data != null)
		{
			for(Map.Entry<String, Object> item : data.entrySet())
			{
				writeFormItem(item.getKey(), item.getValue(), bound, os);
			}
			writeFormItem(null, null, bound, os);
		}
		conn.connect();

		result = readResponsContent(conn);

		return result;
	}

	private static void writeFormItem(String key, Object value, String bound, OutputStream os) throws IOException
	{

		byte[] bySplit = {'-', '-'};
		byte[] byEnter = {'\r', '\n'};

		// --{bound}
		os.write(bySplit);
		byte[] byData = bound.getBytes("UTF-8");
		os.write(byData);

		if (key == null)
		{// 表示需要写入一个结束符
			os.write(bySplit);
			return;
		}

		os.write(byEnter);// 不是结束，则换行

		String[] sKeys = key.split("::");
		String propName = sKeys[0];
		String mime = null;
		if (sKeys.length > 1 && sKeys[1].length() > 0)
		{
			mime = sKeys[1];
		}
		String fileName = null;
		if (sKeys.length > 2 && sKeys[2].length() > 0)
		{
			fileName = sKeys[2];
		}

		// Content-Disposition: form-data; name="aa"; filename="bb.ext"
		byData = ("Content-Disposition: form-data; name=\"" + propName + "\"" + (fileName == null ? "" : ("; filename=\"" + fileName + "\""))).getBytes("UTF-8");
		os.write(byData);
		os.write(byEnter);

		if (value == null)
		{
			// 头与内容的分隔符
			os.write(byEnter);
			// 没有具体内容输出
		}
		else if (value instanceof byte[])
		{
			// Content-Type: mime
			if (mime != null)
			{
				mime = "Content-Type: " + mime;
			}
			else
			{
				mime = "Content-Type: application/octet-stream";
			}
			byData = mime.getBytes("UTF-8");
			os.write(byData);
			os.write(byEnter);

			// 头与内容的分隔符
			os.write(byEnter);
			// 输出内容
			os.write((byte[])value);
		}
		else if (value instanceof InputStream)
		{
			if (mime != null)
			{
				mime = "Content-Type: " + mime;
			}
			else
			{
				mime = "Content-Type: application/octet-stream";
			}
			byData = mime.getBytes("UTF-8");
			os.write(byData);
			os.write(byEnter);

			// 头与内容的分隔符
			os.write(byEnter);
			// 输出内容
			byData = new byte[2048];
			int iLen;
			while((iLen = ((InputStream)value).read(byData)) >= 0)
			{
				os.write(byData, 0, iLen);
			}
		}
		else
		{
			// 头与内容的分隔符
			os.write(byEnter);
			// 输出内容
			byData = (value.toString()).getBytes("UTF-8");
			os.write(byData);
		}
		os.write(byEnter);
	}

	// private static void setCookie(HttpURLConnection conn)
	// {
	// //将当前请求对象的cookie传递过去
	// try{
	// HttpServletRequest req=WebServiceUtils.getRequest();
	// if(req!=null){
	// Cookie[] cookies=req.getCookies();
	// if(cookies!=null && cookies.length>0){
	// String[] cookieStr=new String[cookies.length];
	// for(int iInx=0;iInx<cookies.length;++iInx){
	// cookieStr[iInx] = cookies[iInx].getName()+"="+cookies[iInx].getValue();
	// }
	// conn.setRequestProperty("Cookie", String.join("; ", cookieStr));
	// }
	// }
	// }catch (Exception e) {
	// }
	// }

	/**
	 * 实现与js的ajax请求；
	 * 如果返回4xx~5xx，抛出HttpStatusCodeException（其中statusCode为具体的http status
	 * @param mirror
	 *          请求参数 url:请求地址，type:GET | POST（默认为GET），timeout:超时时长（毫秒）， data:附加数据，
	 *          contentType:请求内容格式（application/x-www-form-urlencoded 或
	 *          application/json，默认为application/x-www-form-urlencoded），
	 *          dataType:返回数据格式（text | json，默认为json）
	 *
	 * @return
	 * @throws IOException
	 */
	public static Object doRequest(Map<String, Object> mirror) throws IOException
	{
		Object val = null;
		if (mirror != null)
		{
			int timeOut = -1;
			Object data = null;
			String path = "", type = "", contentType = "", dataType = "", cookie="";

			for(String key : mirror.keySet())
			{
				switch (key) {
					case "url":
						path = ConvertUtil.getValue(mirror.get("url"), "");
						break;
					case "type":
						type = ConvertUtil.getValue(mirror.get("type"), "GET").toUpperCase();
						break;
					case "timeout":
						timeOut = ConvertUtil.getValue(mirror.get("timeout"), -1);
						break;
					case "contentType":
						contentType = ConvertUtil.getValue(mirror.get("contentType"), "").toLowerCase();
						break;
					case "dataType":
						dataType = ConvertUtil.getValue(mirror.get("dataType"), "json").toLowerCase();
						break;
					case "data":
						data = mirror.get("data");
						break;
					case "cookie":
						cookie = ConvertUtil.getValue(mirror.get("cookie"), "");
						break;
				}
			}

			if (!path.isEmpty())
			{
				String dataStr = valueConversion(data);
				if ((type.isEmpty() || type.equals("GET")))
				{
					if (!dataStr.isEmpty())
					{
						int index = path.indexOf("?");
						if (index > 0)
						{
							path += "&" + dataStr;
						}
						else
						{
							path += "?" + dataStr;
						}
					}
				}

				URL url = new URL(path); // 把字符串转换为URL请求地址
				HttpURLConnection connection;
				if (path.startsWith(HTTPS_PREFIX)) {
					try {
						SSLContext sslContext = SSLContext.getInstance("SSL");
						sslContext.init(null, new TrustManager[]{new X509TrustManager() {
							@Override
							public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

							}

							@Override
							public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

							}

							@Override
							public X509Certificate[] getAcceptedIssuers() {
								return null;
							}
						}}, null);
						HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
						HttpsURLConnection.setDefaultHostnameVerifier((s, sslSession) -> true);
						connection = (HttpsURLConnection) url.openConnection();
					} catch (Exception e) {

						throw new RuntimeException(e);
					}
				} else {
					connection = (HttpURLConnection) url.openConnection();// 打开连接
				}
				if (timeOut >= 0)
				{
					connection.setReadTimeout(timeOut);
					connection.setConnectTimeout(timeOut);
				}
				else
				{
					connection.setReadTimeout(Constant.HTTP_READ_TIMEOUT);
					connection.setConnectTimeout(Constant.HTTP_CONNTION_TIMEOUT);
				}
				connection.setRequestProperty("Accept-Charset", "utf-8");
				// setCookie(connection);
				connection.setDoInput(true);

				String charset;
				if (type.equals("POST"))
				{
					charset = "UTF-8";
					connection.setDoOutput(true);
					connection.setRequestMethod(type);
					if (contentType.isEmpty())
					{
						connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
					}
					else
					{
						String sTemp = contentType.toLowerCase();
						if (sTemp.indexOf("gbk") > 0 || sTemp.indexOf("gb2312") > 0)
							charset = "GBK";
						connection.setRequestProperty("Content-Type", contentType);
						if (!CheckUtil.isNullorEmpty(cookie)){
							connection.setRequestProperty("Cookie",cookie);
						}
						if (contentType.contains("json"))
						{
							if (data != null)
							{
								if (data instanceof String)
								{
									dataStr = data.toString();
								}
								else
								{
									dataStr = JsonUtil.toJsonString(data);
								}
							}
						}
					}
					byte[] byData = dataStr.getBytes(charset);
					connection.getOutputStream().write(byData);
				}
				connection.connect();// 连接会话

				String result = readResponsContent(connection);
				val = result;

				if (dataType.equals("json"))
				{
					if (result.startsWith("["))
					{
						val = JsonUtil.jsonStringToObject(result, List.class);
					}
					else if (result.startsWith("{"))
					{
						val = JsonUtil.jsonStringToObject(result, Map.class);
					}
					else if (result.startsWith("\"") || result.startsWith("'"))
					{
						val = JsonUtil.jsonStringToObject(result, String.class);
					}
					else if (result.equals(Constant.TRUE))
					{
						val = true;
					}
					else if (result.equals(Constant.FALSE))
					{
						val = false;
					}
					else if (result.equals("null"))
					{
						val = null;
					}
					else if (!result.contains("."))
					{
						val = ConvertUtil.getValue(result, 0);
					}
					else
					{
						val = ConvertUtil.getValue(result, 0.0);
					}
				}
			}
		}
		return val;
	}

	/**
	 * 获取返回内容的编码方式（默认为utf-8）
	 * 
	 * @param conn
	 * @return
	 */
	private static String getResponseContentType(HttpURLConnection conn)
	{
		String charset = "UTF-8";
		//类似 text/html; charset=utf-8
		String sTemp = ConvertUtil.getValue(conn.getContentType(), "").toLowerCase();
		if (sTemp.indexOf("=gbk") > 0 || sTemp.indexOf("=gb2312") > 0)
		{
			charset = "GBK";
		}
		return charset;
	}

	private static String readResponsContent(HttpURLConnection conn)
			throws IOException
	{
		String result = "";

		BufferedReader in = null;
		String charset = getResponseContentType(conn);
		int statusCode=conn.getResponseCode();
		if(statusCode<300){//正确
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
			StringBuilder sb = new StringBuilder();
			char[] cBuf = new char[1024];
			int iLen;
			while((iLen = in.read(cBuf)) >= 0)
			{
				sb.append(cBuf, 0, iLen);
			}
			in.close();
			in = null;
			result = sb.toString();
		}else if(statusCode<400){//重定向
			
		}else{//错误
			BufferedReader err = new BufferedReader(new InputStreamReader(conn.getErrorStream(), charset));
			StringBuilder sb = new StringBuilder();
			char[] cBuf = new char[1024];
			int iLen;
			while ((iLen = err.read(cBuf)) >= 0){
				sb.append(cBuf, 0, iLen);
			}
			err.close();
			throw new HttpStatusCodeException(statusCode, sb.toString());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private static String valueConversion(Object data) throws UnsupportedEncodingException
	{
		String dataStr = "";
		if (data == null)
		{
			return dataStr;
		}
		if (data instanceof String)
		{
			dataStr = data.toString();
		}
		else
		{
			if (data instanceof Map<?, ?>)
			{
				Map<String, Object> dataMap = (Map<String, Object>)data;
				if (!dataMap.isEmpty())
				{
					List<String> lstKeyValue = new ArrayList<>();
					getKeyValue("", dataMap, lstKeyValue);
					dataStr = String.join("&", lstKeyValue);
				}
			}
			else
			{
				dataStr = data.toString();
			}
		}
		return dataStr;
	}

	@SuppressWarnings("unchecked")
	private static void getKeyValue(String preKey, Map<String, Object> dataMap, List<String> lstKeyValue) throws UnsupportedEncodingException
	{
		for(String key : dataMap.keySet())
		{
			Object obj = dataMap.get(key);
			String keys = preKey;
			if (CheckUtil.isNullorEmpty(keys))
			{
				keys += key;
			}
			else
			{
				keys += "[" + key + "]";
			}
			if (obj instanceof Map<?, ?>)
			{
				Map<String, Object> subDataMap = (Map<String, Object>)obj;
				if (subDataMap != null)
				{
					getKeyValue(keys, subDataMap, lstKeyValue);
				}
			}
			else
			{
				String codeKey = URLEncoder.encode(keys, "utf-8");
				Object oVal = dataMap.get(key);
				if (oVal == null)
				{
					lstKeyValue.add(codeKey + "=null");
				}
				else
				{
					String value = oVal.toString();
					if (value.isEmpty())
					{
						lstKeyValue.add(codeKey + "=");
					}
					else
					{
						lstKeyValue.add(codeKey + "=" + URLEncoder.encode(value, "utf-8"));
					}
				}
			}
		}
	}

	public static String getBodyJson(HttpServletRequest request) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
		StringBuilder sb = new StringBuilder("");
		char[] temp=new char[1024];
		int count=0;
		while ((count = br.read(temp))>=0){
			sb.append(temp,0,count);
		}
		br.close();
		return sb.toString();
	}

    /*
     * 从request里面参数的值,获取strMap
     * {"strJson":
      {"jid":"1235556433","sxbm":"9000101-01-2","mbmc":"不动产登记综合受理通知书"
      }
       }
     * */
    public static Map<String, Object> getRequestJsonContent(HttpServletRequest request) throws Exception {
        StringBuilder sb = new StringBuilder();
        //从流里读数据
        BufferedReader br = request.getReader();
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        br.close();
        Map<String, Object> requestMap = JsonUtil.jsonStringToMap(sb.toString());
        Map<String, Object> strJsonMap = (Map<String, Object>) requestMap.get("strJson");
        return strJsonMap;
    }

	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param url   发送请求的URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param,Map<String,String> header) {
		String result = "";
		InputStream inputStream = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setRequestProperty("Charsert", "UTF-8");
			if (header != null)
			{
				for(Map.Entry<String, String> entry : header.entrySet())
				{
					connection.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			connection.setRequestProperty("", "");
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			inputStream = connection.getInputStream();
			result = readAll(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 读取字节流
	 *
	 * @param inputStream 字节流
	 * @return String
	 */
	private static String readAll(InputStream inputStream) {
		StringBuilder builder = new StringBuilder();
		try {
			byte[] b = new byte[1024];
			int length = -1;
			while ((length = inputStream.read(b)) != -1) {
				builder.append(new String(b, 0, length, StandardCharsets.UTF_8));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return builder.toString();
	}
}
