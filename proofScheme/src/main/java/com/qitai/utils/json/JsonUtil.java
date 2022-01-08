package com.qitai.utils.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qitai.utils.CheckUtil;
import com.qitai.utils.exception.ExceptionUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author frj
 *
 */
public class JsonUtil
{
//	private static ObjectMapper getMapper()
//	{
//		return getMapper(false);
//	}
	private static ObjectMapper getMapper(boolean ignoreUnknown)
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, !ignoreUnknown);
		return mapper;
	}
	
	/**
	 * 将结果转成Json串输出
	 *
	 * @param value 转换对象
	 * @return json字符串
	 */
	public static String toJsonString(Object value)
	{
		if(value==null) return "null";
		
		String result;
		try
		{
			result = getMapper(false).writeValueAsString(value);
		}
		catch(Exception e)
		{

			e.printStackTrace();
			result = "";
		}
		return result;
	}
	/**
	 * 将json串反序列化成对象，json串需要标准格式（即属性名及字符串值要以双引号包含）
	 * @param json
	 * @param classType
	 * @return
	 */
	public static Object jsonStringToObject(String json,Class<?> classType)
	{
		return jsonStringToObject(json,classType,false);
	}
	
	/**
	 * 将json串反序列化成对象，json串需要标准格式（即属性名及字符串值要以双引号包含）
	 * @param json
	 * @param classType
	 * @param ignoreUnknown 忽略该目标对象不存在的属性
	 * @return
	 */
	public static Object jsonStringToObject(String json,Class<?> classType,boolean ignoreUnknown)
	{
		Object result;
		try
		{
			result = getMapper(ignoreUnknown).readValue(json, classType);
		}
		catch(Exception e)
		{

			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	/**
	 * 将字符串转换成泛型对象，比如List对象（可以用接口类型，对于List会使用ArrayList实例化，对于Map会使用LinkedHashMap实例化），
	 * json串需要标准格式（即属性名及字符串值要以双引号包含）
	 * 如：:
	 * jsonStringToObject("[{\"age\":1,\"name\":\"aa\"},{\"age\":2,\"name\":\"12\"}]",
	 *  new TypeReference＜List＜Person＞＞(){});
	 *  或
	 * jsonStringToObject("[{\"age\":1,\"name\":\"aa\"},{\"age\":2,\"name\":\"12\"}]",
	 *  new TypeReference＜List＜Map＜?,?＞＞＞(){});
	 * @param json
	 * @param type
	 * @return
	 */
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
	
	/**
	 * 将字符串转换成泛型对象，比如List对象（可以用接口类型，对于List会使用ArrayList实例化，对于Map会使用LinkedHashMap实例化），
	 * json串需要标准格式（即属性名及字符串值要以双引号包含）
	 * 如：:
	 * jsonStringToObject("[{\"age\":1,\"name\":\"aa\"},{\"age\":2,\"name\":\"12\"}]",
	 *  new TypeReference＜List＜Person＞＞(){});
	 *  或
	 * jsonStringToObject("[{\"age\":1,\"name\":\"aa\"},{\"age\":2,\"name\":\"12\"}]",
	 *  new TypeReference＜List＜Map＜?,?＞＞＞(){});
	 * @param json
	 * @param type
	 * @param ignoreUnknown 是否忽略不存在的属性
	 * @return
	 */
	public static <T> T jsonStringToObject(String json,TypeReference<T> type,boolean ignoreUnknown)
	{
		T result;
		try
		{
			result = getMapper(ignoreUnknown).readValue(json, type);
		}
		catch(Exception e)
		{

			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	/**
	 * 将json字符串转换为Map对象
	 * @param json
	 * @return
	 */
	public static HashMap<String, Object> jsonStringToMap(String json)
	{
		return jsonStringToObject(json,new TypeReference<HashMap<String, Object>>(){});
	}
	/**
	 * 将json字符串转换为List＜Map＞对象
	 * @param json
	 * @return
	 */
	public static ArrayList<HashMap<String, Object>> jsonStringToList(String json)
	{
		return jsonStringToObject(json,new TypeReference<ArrayList<HashMap<String, Object>>>(){});
	}

	/**
	 * 转换为通用JsonNode对象(可以进行对象属性获取，也可进行数组元素获取)，<br>
	 * 如果需要对获取的节点进行属性修改，可以判断是什么类型后，转换为
	 * com.fasterxml.jackson.databind.node 下的具体类型，再进一步处理。<br>
	 * 如果是Object，可以转换为com.fasterxml.jackson.databind.node.ObjectNode；<br>
	 * 如果是数组，可以转换为ArrayNode。如：<br>
	 * ((ObjectNode)jsonNode).put("age",123);<br>
	 * 另外，如果需要创建新节点，使用JsonNodeFactory.instance来创建指定类型的节点
	 * @param json
	 * @return
	 */
	public static JsonNode jsonStringToNode(String json)
	{
		JsonNode node=null;
		try{
			if(!CheckUtil.isNullorEmpty(json))
				node=getMapper(false).readTree(json);
		}
		catch(Exception e)
		{

			e.printStackTrace();
			node = null;
		}
		return node;
	}

	public static Map<String,Object> conversionMap(Object json){
		String strJson = toJsonString(json);
		return jsonStringToObject(strJson,new TypeReference<Map<String, Object>>(){});
	}

	/**
	 * 从流里面获取信息
	 *
	 * @param request 请求对象
	 * @return String
	 */
	public static String getRequestJsonContent(HttpServletRequest request) {
		String strJson = "";
		try {
			StringBuilder sb = new StringBuilder();
			//从流里读数据
			BufferedReader br = request.getReader();
			String str;
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			br.close();
			strJson = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strJson;
	}
}
