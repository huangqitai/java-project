package com.qitai.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ReturnResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2926068360477865389L;

	/**
	 * 返回结果MAP 对象
	 * @param result
	 * @param message
	 * @return
	 */
	public static Map<String, Object> returnMap(String result,Object message){
		 Map<String, Object> map = new  HashMap<String, Object>();
		 map.put("code", result);
		 map.put("message", message);
		 return map;
	}
	
	/**
	 * 
	 * @param code 结果码
	 * @param message 返回的主体信息
	 * @param total 分页接口记录的总条数
	 * @return
	 */
	public static Map<String, Object> returnMap(String code,Object message,int total){
		 Map<String, Object> map = new  HashMap<String, Object>();
		 map.put("code", code);
		 map.put("message", message);
		 map.put("total", total);
		 return map;
	}
}
