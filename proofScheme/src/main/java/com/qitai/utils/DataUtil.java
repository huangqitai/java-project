package com.qitai.utils;

import com.qitai.utils.CheckUtil;
import com.qitai.utils.ConvertUtil;
import com.qitai.utils.json.JsonUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 数据处理工具
 * @author TEN
 *
 */
public class DataUtil {
	
	/**
	 * 
	 * @param list 需要移除元素的字符串集合
	 * @param target 需要移除的目标元素
	 */
	public static void removeStringFromList(List<String> list, String target){
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			if(StringUtils.equals(it.next(), target)){
				it.remove();
			}
		}
	}
	
	/**
   * 实体集合获取指定字段值集合，只获取字符串类型
   * @param objList 实体集合
   * @param key 指定要获取字段名
   * @return 指定字段值集合
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
	public static List<String> getValuesByObjListAndKey(Object objList,String key) {
  	List<String> values = new ArrayList<>();
  	if(objList != null && objList instanceof List){
  		List list = (List)objList;
  		if(list.size() > 0){
  			for(Object obj:list){
    			Map<String, Object> map = null;
    			if(!(obj instanceof Map)){
    				map = ConvertUtil.object2Map(obj);
    			}else{
    				map = (Map<String, Object>)obj;
    			}
    			if(map != null){
    				Object object = map.get(key);
      			if(object != null && object instanceof String){
    					values.add((String)object);
    				}
    			}
    		}
  		}
  	}
  	return values;
  }
	/**
	 * 截取JID路径
	 * @param jid 业务号
	 * 如业务号：202005010123
	 * 第一级目录：从业务号取前6位，202005
	 * 第二级目录：从业务号取前8位，20200501
	 * 第三级目录：从业务号取长度减2位，即截取最后两位，2020050101,
	 * @return 440900/44090020/440900200508001/44090020050800128
	 */
	public static String getDefaultPath(String jid){
		if(CheckUtil.isNullorEmpty(jid)){
			return (new SimpleDateFormat("YYYYMMDD").format(new Date()));
		}
		String partPath = "";
		int end = 0;
		if(jid.length()>2){
			end = 2;
		}
		int length = jid.length();
		partPath = jid.substring(0, getEndIndex(length, 6))+"/";
		partPath += jid.substring(0, getEndIndex(length, 8))+"/";
		partPath += jid.substring(0, jid.length()-end)+"/";
		partPath += jid;
		return partPath;
	}

	public static int getEndIndex(int length, int i) {
		return length >= i ? i : length;
	}

	/**
	 * 组装密码串
	 * @param  strJson 数据
	 * @param  userPassword 密码串
	 * @return String
	 */
	public static String getUserPasswordContent(String strJson, String userPassword) {
		Map<String, Object> jsonMap = JsonUtil.jsonStringToMap(strJson);
		if (jsonMap == null) {
			throw new RuntimeException("请求内容格式有误");
		}

		Map<String, Object> passwordMap = JsonUtil.jsonStringToMap(userPassword);
		if (passwordMap == null) {
			throw new RuntimeException("服务用户密码配置有误");
		}
		//添加密码对
		jsonMap.putAll(passwordMap);
		return JsonUtil.toJsonString(jsonMap);
	}

	/**
	 * 组装密码串
	 * @param  jsonMap 数据
	 * @param  userPassword 密码串
	 * @return String
	 */
	public static String getUserPasswordContent(Map<String, Object> jsonMap, String userPassword) {
		if (jsonMap == null) {
			throw new RuntimeException("请求内容格式有误");
		}

		Map<String, Object> passwordMap = JsonUtil.jsonStringToMap(userPassword);
		if (passwordMap == null) {
			throw new RuntimeException("服务用户密码配置有误");
		}
		//添加密码对
		jsonMap.putAll(passwordMap);
		return JsonUtil.toJsonString(jsonMap);
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
