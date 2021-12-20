package com.qitai.utils;

import com.qitai.utils.CheckUtil;
import com.qitai.utils.PageEntity;
import com.qitai.utils.json.JsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageQueryData<T> extends PageEntity<T>
{
	private String userId;

	//排序字段，如果多字段排序，以逗号分隔。如：pid,price,unit
	private String sort;
	//排序方式（asc升序，desc降序），如果多字段排序，以逗号分隔，且与sort顺序对应。如：asc,asc,desc
	private String order;
	
//	//指定多个字段排序（key指定排序字段，value指定排序方式asc或desc）
//	private Map<String, String> sortFields;

	//模糊搜索内容（或自定义搜索格式）
	private String searchTxt;

	//指定名值对的搜索内容（key指定目标，value指定搜索内容，可能根据key值的不同意义不同）
	private Map<String, String> searchTexts;
	
	//json格式的过滤规则，如：
	//[{"field":"itemid","op":"contains","value":"-1"},{"field":"listprice","op":"less","value":"10.0"}]
	//op默认有：contains，其它值，可以自己定义
	private String filterRules;

	/**
	 * 获取关联操作用户ID
	 * 
	 * @return
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * 设置关联操作用户ID
	 * 
	 * @param userId
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	/**
	 * @return 排序字段，如果多字段排序，以逗号分隔。如：pid,price,unit
	 */
	public String getSort()
	{
		return sort;
	}

	/**
	 * @param 排序字段，如果多字段排序，以逗号分隔。如：pid,price,unit
	 */
	public void setSort(String sort)
	{
		this.sort = sort;
	}

	/**
	 * @return 排序方式（asc升序，desc降序），如果多字段排序，以逗号分隔，且与sort顺序对应。
	 * 如：asc,asc,desc
	 */
	public String getOrder()
	{
		return order;
	}

	/**
	 * @param 排序方式（asc升序，desc降序），如果多字段排序，以逗号分隔，且与sort顺序对应。
	 * 如：asc,asc,desc
	 */
	public void setOrder(String order)
	{
		this.order = order;
	}

//	/**
//	 * 获取名值对的排序字段
//	 * @return
//	 */
//	public Map<String, String> getSortFields()
//	{
//		return sortFields;
//	}
//
//	/**
//	 * 设置名值对的排序字段，Key为排序字段，Value为排序方式
//	 * 前端页面可以按如下url请求传入：
//	 * sortFields[字段名]=desc&sortFields[字段名2]=asc
//	 * @param sortFields
//	 */
//	public void setSortFields(Map<String, String> sortFields)
//	{
//		this.sortFields = sortFields;
//	}

	
	/**
	 * 获取通用字段模糊查询内容，空表示没有通用查询
	 * 
	 * @return
	 */
	public String getSearchTxt()
	{
		return searchTxt;
	}

	/**
	 * 设置通用字段模糊查询内容，空表示不使用通用查询
	 * 
	 * @param searchTxt
	 */
	public void setSearchTxt(String searchTxt)
	{
		this.searchTxt = searchTxt.trim();
	}

	/**
	 * 获取名值对的搜索内容（key为搜索目标Key，value为搜索内容，可能根据Key的不同意义不同）。
	 * 前端页面可以按如下url请求传入：
	 * searchTexts[字段名]=查询值
	 * @return
	 */
	public Map<String, String> getSearchTexts()
	{
		return searchTexts;
	}

	/**
	 * 设置名值对的搜索内容（key为搜索目标Key，value为搜索内容，可能根据Key的不同意义不同）
	 * 前端页面可以按如下url请求传入：
	 * searchTexts[字段名]=查询值
	 * @param searchTexts
	 */
	public void setSearchTexts(Map<String, String> searchTexts)
	{
		this.searchTexts = searchTexts;
	}
	
	/**格式如：[{"field":"itemid","op":"contains","value":"-1"},{"field":"listprice","op":"less","value":"10.0"}]<br>
	 * op默认有：contains，其它值，可以自己定义
	 * @return json格式的过滤规则
	 */
	public String getFilterRules()
	{
		return filterRules;
	}

	/**格式如：[{"field":"itemid","op":"contains","value":"-1"},{"field":"listprice","op":"less","value":"10.0"}]<br>
	 * op默认有：contains，其它值，可以自己定义
	 * @param json格式的过滤规则
	 */
	public void setFilterRules(String filterRules)
	{
		this.filterRules = filterRules;
	}
	
	/**
	 * 将过滤规则由json串转换为对象
	 * @return
	 */
	public List<HashMap<String, Object>> ofFilterRules()
	{
		if(CheckUtil.isNullorEmpty(filterRules))
			return null;
		return JsonUtil.jsonStringToList(filterRules);
	}

}
