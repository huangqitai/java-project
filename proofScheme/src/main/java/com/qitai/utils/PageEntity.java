package com.qitai.utils;

import com.qitai.utils.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author wujian
 *
 * @param 接收返回数据的实体类
 * 为了能够兼容easyUI 的 page ， rows 以及total参数
 */
public class PageEntity<T> {

	private int page = 1;//当前页面 ，基于1开始
	private int skip;//从数据集的第几条开始查询（如果大于0，优先于page）
	private int rows = Constant.DEFAULT_PAGE_SIZE;//每页显示的条数
	private int total;//总共的条数
	private List<T> queryList; //查询后返回的结果集

	/**
	 * 获取每页记录数
	 * @return
	 */
	public int getRows() {
		return rows;
	}

	/**设置每页记录数（-1表示查询所有记录）
	 * @param rows
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**获取总记录数
	 * @return
	 */
	public int getTotal() {
		if(total<=0)
		{
			if(queryList!=null)
				total=queryList.size();
		}
		return total;
	}

	/**
	 * 设置总记录数
	 * @param total
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * 获取当前页号（基于1开始）
	 * @return
	 */
	public int getPage() {
		return page;
	}

	/**
	 * 设置当前页号（基于1开始），如果同时也设置了skip(大于0)，则skip优先使用
	 * @param page
	 */
	public void setPage(int page)
	{
		if(page<=0) throw new IllegalArgumentException("页号从1开始计数");
		this.page = page;
	}

	/**
	 * 获取查询结果列表
	 * @return
	 */
	public List<T> getQueryList() {
		return queryList;
	}

	/**
	 * 设置查询结果列表
	 * @param queryList
	 */
	public void setQueryList(List<T> queryList) {
		this.queryList = queryList;
	}

	/**
	 * 从数据集的第几条开始查询（如果大于0，优先于page）
	 * @return
	 */
	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

	/**
	 * 返回组装的结果，兼容easyUI的分页功能, 可增加需要扩展返回值
	 * 
	 * @return
	 */
	public Map<String, Object> getResult()
	{
		return com.qitai.utils.PageEntity.getResult(this.getTotal(), this.getQueryList());
	}

	/**
	 * 返回组装的结果，兼容easyUI的分页功能
	 * @param iSize 返回结果总记录数，如果为负值，则从rows获取
	 * @param rows 当前页的内容
	 * @return
	 */
	public static Map<String, Object> getResult(int iSize,List<?> rows)
	{
		Map<String, Object> result = new HashMap<String, Object>(2);
		if(rows==null){
			rows=new ArrayList<Object>(1);
		}
		if(iSize<0)
		{
			iSize=rows.size();
		}
		result.put("total", iSize);
		result.put("rows", rows);
		return result;
	}
}
