package com.qitai.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限缓存数据
 * @author dennis
 *
 */
public class RightData implements Serializable
{
	private static final long serialVersionUID = -6359112698268419040L;
	
	//模块id
	private String moduleId;
	//模块权限
	private Integer moduleRight;
	//模块对应的功能权限
	private List<String> funcList;
	//模块对应的URL地址
	private String pageUrl;
	//在权限为0时，需要记录一下标题
	private String title;
	
	public RightData() {
		funcList=new ArrayList<>();
	}
	
	public RightData(String moduleId, Integer moduleRight, List<String> funcList, String pageUrl) {
		this();
		this.moduleId = moduleId;
		this.moduleRight = moduleRight;
		if(funcList!=null){
			this.funcList.addAll(funcList);
		}
		this.pageUrl = pageUrl;
	}
	
	public com.qitai.utils.RightData clone()
	{
		com.qitai.utils.RightData newObj=new com.qitai.utils.RightData();
		newObj.moduleId=this.moduleId;
		newObj.moduleRight=this.moduleRight;
		newObj.pageUrl=this.pageUrl;
		newObj.title=this.title;
		newObj.funcList.addAll(this.funcList);
		
		return newObj;
	}

	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	
	/**
	 * 权限值：0无权限，1只读，3修改，7创建，15删除，127管理，255授权
	 * -1 未定义
	 * @return
	 */
	public Integer getModuleRight() {
		return moduleRight;
	}
	public void setModuleRight(Integer moduleRight) {
		this.moduleRight = moduleRight==null?-1:moduleRight;
	}
	
	/**
	 * 提示用标题，为了节约空间，只有无权限的模块，才设置值
	 * @return
	 */
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String tt)
	{
		this.title=tt;
	}
	
	public List<String> getFuncList() {
		return funcList;
	}
	public void setFuncList(List<String> funcList) {
		if(funcList==null)
			this.funcList=new ArrayList<>();
		else
			this.funcList = funcList;
	}

}
